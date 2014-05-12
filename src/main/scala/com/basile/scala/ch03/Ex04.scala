package com.basile.scala.ch03

/**
 * Given an array of integers, produce a new array that contains all positive values of the original array,
 * in their original order, followed by all values that are zero or negative, in their original order.
 */
object Ex04 extends App {

  def sortPositive(a: Array[Int]) = a.filter( _ > 0 ) ++ a.filter( _ <=0 )

  assert( sortPositive(Array(-2, 8, 0, 4, -8, -1, 0, 9)).deep == Array(8, 4, 9, -2, 0, -8, -1, 0).deep )
}
