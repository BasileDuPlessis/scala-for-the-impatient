package com.basile.scala.ch03

import scala.util.Random

/**
 * Write a code snippet that sets a to an array of n random integers between 0 (inclusive) and n (exclusive).
 */
object Ex01 extends App {

  def rand(n: Int) = new Array[Int](n).map(_ => Random.nextInt(n))

  val l = 100
  val r = rand(l)

  assert(r.size == l)
  r.foreach( i => assert(i < l && i >= 0))

}
