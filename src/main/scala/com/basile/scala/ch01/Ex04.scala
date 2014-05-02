package com.basile.scala.ch01

/**
 * Scala lets you multiply a string with a number—try out "crazy" * 3 in the REPL.
 * What does this operation do?
 * Where can you find it in Scaladoc?
 *
 */
object Ex04 extends App {
  assert( ("crazy" * 3) == "crazycrazycrazy")

  /*
  http://www.scala-lang.org/api/current/#scala.collection.immutable.StringOps

  def *(n: Int): String
    Return the current string concatenated n times.

   */
}
