package com.basile.scala.ch12

/**
 * Modify the previous function to return the input at which the output is largest.
 * For example, largestAt(fun: (Int) => Int, inputs: Seq[Int]) should return 5 . Don’t use a loop or recursion.
 */
object Ex06 extends App {

  def largest(fun: (Int) => Int, inputs: Seq[Int]) = {
    inputs.map(a => (fun(a),a)).reduceLeft( (a,b) => if (a._1>b._1) a else b )._2
  }

  assert( largest(x => 10 * x - x * x, 1 to 10) == 5)

}
