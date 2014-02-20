package com.basile.scala.ch18

import scala.language.reflectiveCalls

/**
 * Write a function printValues with three parameters f , from , to that prints all values of f with inputs
 * from the given range.
 *
 * Here, f should be any object with an apply method that consumes and yields an Int .
 *
 * For example
 *  printValues((x: Int) => x * x, 3, 6) // Prints 9 16 25 36
 *  printValues(Array(1, 1, 2, 3, 5, 8, 13, 21, 34, 55), 3, 6) // Prints 3 5 8 13
 */
object Ex08 extends App {

  def printValues(f: {def apply(n: Int):Int}, from: Int, to: Int) {
    println(
      (for (n <- (from to `to`)) yield f(n)).mkString(" ")
    )
  }

  printValues((x: Int) => x * x, 3, 6)
  printValues(Array(1, 1, 2, 3, 5, 8, 13, 21, 34, 55), 3, 6)
}
