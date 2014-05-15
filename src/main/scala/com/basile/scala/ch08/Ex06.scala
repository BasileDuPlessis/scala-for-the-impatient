package com.basile.scala.ch08

/**
 * Define an abstract class Shape with an abstract method centerPoint and subclasses Rectangle and Circle .
 *
 * Provide appropriate constructors for the subclasses and override the centerPoint method in each subclass.
 */
object Ex06 extends App {

  abstract class Shape {
    def centerPoint:(Double, Double)
  }

  class Rectangle(val upperLeft: (Double, Double), val width: Double, val height: Double) extends Shape {
    def centerPoint:(Double, Double) = (upperLeft._1 + width/2, upperLeft._2 + height / 2)
  }
  class Circle(val centerPoint: (Double, Double), val rayon: Double) extends Shape {
  }

  val C = new Circle((0,0), 45)

}
