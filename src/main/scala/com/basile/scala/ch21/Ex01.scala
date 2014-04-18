package com.basile.scala.ch21

import scala.language.implicitConversions


/**
 * How does -> work?
 * That is, how can "Hello" -> 42 and 42 -> "Hello" be pairs ("Hello", 42) and (42, "Hello") ?
 * Hint: Predef.any2ArrowAssoc .
 */
object Ex01 extends App {

  class ArrowAssoc[A](val a: A) {
    def ->[B](b: B): Tuple2[A, B] = (a, b)
  }

  implicit def any2ArrowAssoc(a: Any): ArrowAssoc[Any] = new ArrowAssoc(a)

  assert(1 -> "one" == (1, "one"))
  assert("one" -> 1 == ("one", 1))

}
