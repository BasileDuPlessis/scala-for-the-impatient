package com.basile.scala.ch12

/**
 * In Section 12.8 , “ Currying ,” on page 149 , you saw the corresponds method used with two arrays of strings.
 * Make a call to corresponds that checks whether the elements in an array
 * of strings have the lengths given in an array of integers.
 */
object Ex08 extends App {

  val a = Array("Hello", "World")
  val b = Array(5, 4)


  assert( a.corresponds(b)(_.length==_) == false )

}
