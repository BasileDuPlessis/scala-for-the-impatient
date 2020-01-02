package com.basile.scala.ch03

/**
 * Write a loop that swaps adjacent elements of an array of integers.
 * For example, Array(1, 2, 3, 4, 5) becomes Array(2, 1, 4, 3, 5) .
 */
object Ex02 extends App {

  def swapAdjacent(a: Array[Int]):Array[Int] = {
    for(i <- 1.until(a.length).by(2)) {
      val t = a(i)
      a(i) = a(i-1)
      a(i-1) = t
    }
    a
  }

  assert( swapAdjacent(Array(51,42,30,4,25)).deep == Array(42,51,4,30,25).deep )

  assert( swapAdjacent(Array(51,42,30,4)).deep == Array(42,51,4,30).deep )

}
