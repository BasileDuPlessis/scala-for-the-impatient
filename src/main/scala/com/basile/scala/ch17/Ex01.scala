package com.basile.scala.ch17

/**
 * Define an immutable class Pair[T, S] with a method swap that returns a new pair with the components swapped.
 */
object Ex01 {
  class Pair[T, S](val first: T, val second: S) {
    def swap:Pair[S, T] = new Pair(second, first)
  }
}
