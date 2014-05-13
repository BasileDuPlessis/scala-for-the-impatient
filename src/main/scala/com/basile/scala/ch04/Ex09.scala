package com.basile.scala.ch04

/**
 * Write a function lteqgt(values: Array[Int], v: Int) that returns a triple containing the counts of values less
 * than v , equal to v , and greater than v .
 */
object Ex09 extends App {

  def lteqgt(values: Array[Int], v: Int) = (values.count(_<v), values.count(_==v), values.count(_>v))

  assert( lteqgt(Array(1,2,3,4,5,6), 3) == (2, 1, 3) )

}
