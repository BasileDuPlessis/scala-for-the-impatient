package com.basile.scala.ch12

/**
 * How do you get the largest element of an array with reduceLeft ?
 */
object Ex02 extends App {

  val a = Array(1, 25, 47, 2, 54, 0, 6)

  assert( a.reduceLeft[Int]((a,b) => if (a>b) a else b) == 54 )

}
