package com.basile.scala.ch13

/**
 * Write a function that turns an array of Double values into a two-dimensional array.
 * Pass the number of columns as a parameter.
 *
 * For example, with Array(1, 2, 3, 4, 5, 6) and three columns, return Array(Array(1, 2, 3), Array(4, 5, 6)) .
 * Use the grouped method.
 *
 */
object Ex08 extends App {

  def array2Dim(a: Array[Int], dim: Int): Array[Array[Int]] = a.grouped(dim).toArray

  val a = Array(1,2,3,4,5,6)

  assert( array2Dim(a, 3).deep == Array(Array(1, 2, 3), Array(4, 5, 6)).deep)

}
