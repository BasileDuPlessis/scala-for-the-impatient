package com.basile.scala.ch12

/**
 * Implement corresponds without currying. Then try the call from the preceding exercise.
 * What problem do you encounter?
 */
object Ex09 extends App {

  val a = Array("Hello", "World")
  val b = Array(5, 5)

  def corresponds(a: Array[String], b: Array[Int], f: (String, Int) => Boolean): Boolean = {
    (a zip b).map(t => f(t._1, t._2)).reduce(_ & _)
  }

  assert( corresponds(a, b, (x, y) => x.length==y) == true )

}
