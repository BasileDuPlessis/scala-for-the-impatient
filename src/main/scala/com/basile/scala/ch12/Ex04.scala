package com.basile.scala.ch12

/**
 * The previous implementation needed a special case when n < 1. Show how you can avoid this with foldLeft .
 * (Look at the Scaladoc for foldLeft .
 * It’s like reduceLeft , except that the first value in the chain of combined values is supplied in the call.)
 */
object Ex04 extends App {

  def fact(v: Int): Int = (1 to v).foldLeft(1)(_ * _)

  assert( fact(0) == 1 )

  assert( fact(5) == 120 )

}
