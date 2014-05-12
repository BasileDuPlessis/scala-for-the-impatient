package com.basile.scala.ch02

import scala.math.pow

/**
 * Write a function that computes x^n , where n is an integer.
 * Use the following recursive definition:
 * • x^n = y^2 if n is even and positive, where y = x^n / 2 .
 * • x^n = x· x^n – 1 if n is odd and positive.
 * • x^0 = 1.
 * • x^n = 1 / x – n if n is negative.
 *
 * Don’t use a return statement.
 */
object Ex10 extends App {

  def compute(x: Double, n: Int): Double = {
    if (n > 0 && n % 2 == 0) pow( pow( x, n / 2 ), 2 )
    else if (n > 0 && n % 2 != 0) x * pow( x, n - 1 )
    else if (n < 0) 1 / pow( x, -n )
    else 1
  }

  assert(compute(5, 0) == 1)



}
