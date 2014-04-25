package com.basile.scala.ch21

import java.awt.Point
import scala.util.Sorting._
import scala.language.implicitConversions

/**
 * Continue the previous exercise, comparing two points according to their distance to the origin.
 * How can you switch between the two orderings?
 */
object Ex07 extends App {

  //Define lexical ordered method
  class LexicalOrdered(val p: Point) extends Ordered[Point] {
    def compare(that: Point): Int = (p.x - that.x).signum match {
      case 0 => (p.y - that.y).signum
      case s => s
    }
  }

  //Define origin ordered method
  class OriginOrdered(val p: Point) extends Ordered[Point] {
    private def distanceToOrigin(p: Point): Double = math.sqrt(math.pow(p.x, 2) + math.pow(p.y, 2))
    def compare(that: Point): Int = (distanceToOrigin(p) - distanceToOrigin(that)).signum
  }

  //Define ordering class
  class OrderPointBy(val f: (Point) => Ordered[Point]) extends Ordering[Point] {
    implicit def point2LexicalOrdered(pt: Point): Ordered[Point] = f(pt)
    def compare(a: Point, b: Point): Int = a compare b
  }


  val points = Array(new Point(4, 5), new Point(6, 5), new Point(1, 4), new Point(6, 4))

  //sort array using LexicalOrdered
  quickSort(points)(new OrderPointBy((pt:Point) => new LexicalOrdered(pt)))
  points.foreach(println)

  //sort array using OriginOrdered
  quickSort(points)(new OrderPointBy((pt:Point) => new OriginOrdered(pt)))
  points.foreach(println)

}
