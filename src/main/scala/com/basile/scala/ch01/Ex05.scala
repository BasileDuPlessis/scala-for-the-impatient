package com.basile.scala.ch01

/**
 * What does 10 max 2 mean? In which class is the max method defined?
 */
object Ex05 extends App {

  //A max B return A if A > B else return B

  /*
  http://www.scala-lang.org/api/current/#scala.runtime.RichInt
  def max(that: Int): Int
    Returns this if this > that or that otherwise.
  */


  assert( (10 max 2) == 10)

}
