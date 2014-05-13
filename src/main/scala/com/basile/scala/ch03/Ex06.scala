package com.basile.scala.ch03

import scala.collection.mutable.ArrayBuffer

/**
 * How do you rearrange the elements of an Array[Int] so that they appear in reverse sorted order?
 * How do you do the same with an ArrayBuffer[Int] ?
 */
object Ex06 extends App {

  var a = Array(30,42,51,4,25)
  a = a.sortWith(_>_)

  var b = ArrayBuffer(30,42,51,4,25)
  b = b.sortWith(_>_)

  assert( a.deep == Array(51,42,30,25,4).deep )

  assert( b == ArrayBuffer(51,42,30,25,4) )

}
