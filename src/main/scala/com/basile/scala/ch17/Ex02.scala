package com.basile.scala.ch17

/**
 * Define a mutable class Pair[T] with a method swap that swaps the components of the pair.
 */
object Ex02 {
  class Pair[T](val first: T, val second: T) {
    def swap:Pair[T] = new Pair(second, first)
  }
}
