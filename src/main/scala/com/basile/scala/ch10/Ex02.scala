package com.basile.scala.ch10

import java.awt.Point

/**
 * Define a class OrderedPoint by mixing scala.math.Ordered[Point] into java.awt.Point .
 *
 * Use lexicographic ordering, i.e. ( x , y ) < ( x ’, y ’) if x < x’ or x = x’ and y < y ’.
 */
object Ex02 extends App {

  class OrderedPoint(x: Int, y: Int) extends Point(x, y) with scala.math.Ordered[Point] {
    def compare(that: Point): Int = {
      ((this.x - that.x).signum, (this.y - that.y).signum) match {
        case (-1,_) | (0,-1) => -1
        case (0, 0) => 0
        case _ => 1
      }
    }
  }


  val op1 = new OrderedPoint(10, 5)
  val op2 = new OrderedPoint(10, 6)

  assert( op1 < op2 )

}
