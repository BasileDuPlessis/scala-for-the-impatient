package com.basile.scala.ch13

/**
 * Implement a function that works just like mkString , using reduceLeft .
 */
object Ex05 extends App {

  val a = Array("Hello", ", ", "world !")

  println( a.reduceLeft(_ + _) )

}
