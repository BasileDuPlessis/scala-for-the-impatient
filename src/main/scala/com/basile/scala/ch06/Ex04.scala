package com.basile.scala.ch06

/**
 * Define a Point class with a companion object so that you can construct Point instances as Point(3, 4) ,
 * without using new .
 */
object Ex04 extends App {

  class Point(val x:Double, val y: Double)

  object Point {
    def apply(x:Double, y: Double) = new Point(x, y)
  }

  val p = Point(3,4)

  assert( p.x == 3.0)
}
