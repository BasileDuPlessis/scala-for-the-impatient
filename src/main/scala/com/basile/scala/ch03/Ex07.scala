package com.basile.scala.ch03

/**
 * Write a code snippet that produces all values from an array with duplicates removed. (Hint: Look at Scaladoc.)
 */
object Ex07 extends App {

  val a = Array(51,42,30,4,25,51,8,42)

  assert( a.distinct.deep == Array(51,42,30,4,25,8).deep )

}
