package com.basile.scala.ch21

/**
 * Define a ! operator that computes the factorial of an integer.
 * For example, 5! is 120 . You will need an enrichment class and an implicit conversion.
 */
object Ex03 extends App {

  class RichInt(val value: Int) {
    def !():Int = (1 to value).foldLeft(1)( (res, v) => res * v )
  }

  implicit def int2RichInt(value: Int): RichInt = new RichInt(value)

  assert((5!) == 120)

}
