package com.basile.scala.ch21

import java.awt.Point
import scala.util.Sorting._
import scala.language.implicitConversions

/**
 * Continue the previous exercise, comparing two points according to their distance to the origin.
 * How can you switch between the two orderings?
 */
object Ex07 extends App {

  //Define lexical ordering method
  class LexicalOrdering(val p: Point) extends Ordered[Point] {
    def compare(that: Point): Int = (p.x - that.x).signum match {
      case 0 => (p.y - that.y).signum
      case s => s
    }
  }

  //Define origin ordering method
  class OriginOrdering(val p: Point) extends Ordered[Point] {
    private def distanceToOrigin(p: Point): Double = math.sqrt(math.pow(p.x, 2) + math.pow(p.y, 2))
    def compare(that: Point): Int = (distanceToOrigin(p) - distanceToOrigin(that)).signum
  }


  //Sort array of point with implicit parameter
  def sort(a: Array[Point])(implicit f: (Point) => Ordered[Point]): Array[Point] = {
    quickSort(a)
    a
  }

  //define implicit parameter
  implicit def point2LexicalOrdering(pt: Point): LexicalOrdering = new LexicalOrdering(pt)


  val points = Array(new Point(4, 5), new Point(6, 5), new Point(1, 4), new Point(6, 4))

  //sort array using implicit def point2LexicalOrdering
  sort(points).foreach(println)

  //sort array with an explicit function
  sort(points)((pt: Point) => new OriginOrdering(pt)).foreach(println)

}
