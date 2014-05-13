package com.basile.scala.ch04

/**
 * Write a function minmax(values: Array[Int]) that returns a pair containing
 * the smallest and largest values in the array.
 */
object Ex08 extends App {

  def minmax(a: Array[Int]) = (a.min, a.max)

  assert(  minmax(Array(1,2,3,4,5,6)) == (1, 6))

}
