package com.basile.scala.ch11

/**
 * Provide a class Matrix —you can choose whether you want to implement 2 × 2 matrices,
 * square matrices of any size, or m × n matrices.
 *
 * Supply operations + and * . The latter should also work with scalars, for example mat * 2 .
 *
 * A single element should be accessible as mat(row, col) .
 */
object Ex08 extends App {

  class Matrix(val m: Array[Array[Double]]) {

    val rows = 	m.length
    val cols = m(0).length

    def apply(r: Int, c: Int) = m(r)(c)
    def update(r: Int, c: Int, v: Double) { m(r)(c) = v}

    private def compute(that: Matrix, f:(Double,Double) => Double): Array[Array[Double]] = {
      if (that.dim != dim) throw new Exception("Can only add matrix with same size")
      (for (i <- m.indices) yield m(i).zip(that.m(i)).map(v => f(v._1, v._2))).toArray
    }

    def +(that: Matrix) = new Matrix(compute(that, (a,b) => a+b))

    def *(that: Matrix) = new Matrix(compute(that, (a,b) => a*b))

    def *(s: Double) = {
      new Matrix(
        ( for (i <- m.indices)
        yield m(i).map(_*s) ).toArray
      )
    }

    def dim = (rows, cols)

    override def toString = m.map(_.mkString(" ")).mkString("\n")
  }

  val m = new Matrix(Array(Array(1.0,2.0,3.0), Array(4.0,5.0,6.0)))

  val n = new Matrix(Array(Array(1.0,2.0,3.0), Array(4.0,5.0,6.0)))

  println( (m*2 + n) * m )


}
