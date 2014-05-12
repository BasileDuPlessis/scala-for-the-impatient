package com.basile.scala.ch03

/**
 * Write a loop that swaps adjacent elements of an array of integers.
 * For example, Array(1, 2, 3, 4, 5) becomes Array(2, 1, 4, 3, 5) .
 */
object Ex02 extends App {

  def swapAdjacent(a: Array[Int]):Array[Int] = {
    for(i <- 0 until a.length if (i%2==0)) {
      val temp = a(i)
      try {
        a.update(i, a(i+1))
        a.update(i+1, temp)
      } catch {
        case _: ArrayIndexOutOfBoundsException => ()
      }
    }
    a
  }

  assert( swapAdjacent(Array(51,42,30,4,25)).deep == Array(42,51,4,30,25).deep )

  assert( swapAdjacent(Array(51,42,30,4)).deep == Array(42,51,4,30).deep )

}
