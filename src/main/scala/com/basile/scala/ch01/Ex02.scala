package com.basile.scala.ch01

import scala.math._

/**
 * In the Scala REPL, compute the square root of 3, and then square that value.
 * By how much does the result differ from 3? (Hint: The res variables are your friend.)
 */
object Ex02 extends App {

  //print 4.440892098500626E-16
  println(3 - pow(sqrt(3), 2))

}
