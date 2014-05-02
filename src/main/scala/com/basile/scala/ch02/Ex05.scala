package com.basile.scala.ch02

/**
 * Write a procedure countdown(n: Int) that prints the numbers from n to 0.
 */
object Ex05 extends App {

  def countdown(n: Int) {
    for(i <- n.to(0, -1)) println(i)
  }

  //should print 10 9 8 7 6 5 4 3 2 1 0
  countdown(10)

}
