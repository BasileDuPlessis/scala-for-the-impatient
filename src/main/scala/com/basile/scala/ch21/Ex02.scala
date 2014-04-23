package com.basile.scala.ch21

/**
 * Define an operator +% that adds a given percentage to a value.
 * For example, 120 +% 10 should be 132.
 * Hint: Since operators are methods, not functions, you will also need to provide an implicit .
 */
object Ex02 extends App {

  class RichInt(val value:Int) {
    def +%(percent: Double): Double = value * (1 + 1/percent)
  }

  implicit def int2Percent(value: Int): RichInt = new RichInt(value)

  assert(120 +% 10 == 132.0)

}
