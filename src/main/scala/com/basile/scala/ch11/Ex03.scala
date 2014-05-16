package com.basile.scala.ch11

/**
 * Implement the Fraction class with operations + - * / . Normalize fractions, for example turning 15/–6 into –5/2.
 * Divide by the greatest common divisor, like this:
 *
 * class Fraction(n: Int, d: Int) {
 *  private val num: Int = if (d == 0) 1 else n * sign(d) / gcd(n, d);
 *  private val den: Int = if (d == 0) 0 else d * sign(d) / gcd(n, d);
 *  override def toString = num + "/" + den
 *  def sign(a: Int) = if (a > 0) 1 else if (a < 0) -1 else 0
 *  def gcd(a: Int, b: Int): Int = if (b == 0) abs(a) else gcd(b, a % b)
 *  ..
 */
object Ex03 extends App {

  class Fraction(_n: Int, _d: Int) {

    val signum = _n.signum*_d.signum

    val g = gcd(_n.abs, _d.abs)
    val n = _n.abs*signum/g
    val d = _d.abs/g

    def /(that: Fraction) = new Fraction(n*that.d, d*that.n)
    def *(that: Fraction) = new Fraction(n*that.n, d*that.d)
    def +(that: Fraction) = new Fraction(n*that.d+that.n*d, d*that.d)
    def -(that: Fraction) = new Fraction(n*that.d-that.n*d, d*that.d)

    private def gcd(a: Int, b: Int): Int = {
      if (b==0) a else gcd(b, a%b)
    }

    override def toString = n + "/" + d
  }

  val f1 = new Fraction(4,-5)
  val f2 = new Fraction(-8,3)

  assert( (f1 / f2).toString == "3/10" )

}
