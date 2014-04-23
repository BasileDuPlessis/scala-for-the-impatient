package com.basile.scala.ch21

import scala.language.implicitConversions

/**
 * Provide the machinery that is needed to compute smaller(Fraction(1, 7), Fraction(2, 9))
 * Supply a class RichFraction that extends Ordered[Fraction] .
 */
object Ex05 extends App {

  case class Fraction(val n: Int, val d: Int)

  def smaller[T](a: T, b: T)(implicit order: T => Ordered[T]): T = if (a < b) a else b

  class RichFraction(val f: Fraction) extends Ordered[Fraction] {
    //this minus that
    def compare(that: Fraction):Int = (f.n * that.d) - (that.n * f.d)
  }

  implicit def fraction2RichFraction(f: Fraction): RichFraction = new RichFraction(f)

  assert(smaller(Fraction(1, 7), Fraction(2, 9)) == Fraction(1, 7))

}
