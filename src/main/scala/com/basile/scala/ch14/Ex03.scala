package com.basile.scala.ch14

/**
 * Using pattern matching, write a function swap that swaps the first two elements of an array provided
 * its length is at least two.
 */
object Ex03 extends App {

  def swap(a: Array[Int]) = a match { case Array(a, b, end @ _*) => Array(b, a) ++ end }

  assert( swap(Array(1,2,3,4)).deep == Array(2,1,3,4).deep )

}
