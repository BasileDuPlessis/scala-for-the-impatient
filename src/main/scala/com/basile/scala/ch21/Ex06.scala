package com.basile.scala.ch21

import scala.language.implicitConversions
import java.awt.Point
import scala.util.Sorting.quickSort

/**
 * Compare objects of the class java.awt.Point by lexicographic comparison.
 */
object Ex06 extends App {

  class RichPoint(p: Point) extends Ordered[Point] {
    def compare(that: Point): Int = (p.x - that.x).signum match {
      case 0 => (p.y - that.y).signum
      case s => s
    }
  }

  implicit def point2RichPoint(pt: Point): RichPoint = new RichPoint(pt)

  val points = Array(new Point(4, 5), new Point(6, 5), new Point(1, 4), new Point(6, 4))
  quickSort(points)

  points.foreach(println)

}
