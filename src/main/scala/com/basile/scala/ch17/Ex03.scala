package com.basile.scala.ch17

/**
 * Given a class Pair[T, S] , write a generic method swap that takes a pair as its argument
 * and returns a new pair with the components swapped.
 */
object Ex03 {

  class Pair[T, S](val first: T, val second: S) {}

  def swap[T, S](p: Pair[T, S]): Pair[S, T] = {
    new Pair(p.second, p.first)
  }

}
