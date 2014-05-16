package com.basile.scala.ch11

/**
 * 4. Implement a class Money with fields for dollars and cents. Supply + , - operators as well as
 * comparison operators == and < .
 *
 * For example, Money(1, 75) + Money(0, 50) == Money(2, 25) should be true .
 *
 * Should you also supply * and / operators? Why or why not?
 */
object Ex04 extends App {

  class Money(d: Int, c: Int) {

    def +(that: Money) = Money(toFloat + that.toFloat)
    def -(that: Money) = Money(toFloat - that.toFloat)
    def ==(that: Money) = toFloat==that.toFloat
    def <(that: Money) = toFloat<that.toFloat

    def toFloat = (d+"."+c).toFloat

  }

  object Money{
    def apply(d: Int, c: Int): Money = new Money(d, c)
    def apply(d: Float): Money = {
      val s = d.toString.split('.')
      new Money(s(0).toInt, s(1).toInt)
    }
  }


  assert( Money(1, 75) + Money(0, 50) == Money(2, 25) )


  //It's meaningless to multiply or divide two amount

}
