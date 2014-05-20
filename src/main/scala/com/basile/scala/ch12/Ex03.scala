package com.basile.scala.ch12

/**
 * Implement the factorial function using to and reduceLeft , without a loop or recursion.
 */
object Ex03 extends App {

  def fact(v: Int): Int = (1 to v).reduceLeft(_ * _)

  assert( fact(5) == 120 )

}
