package com.basile.scala.ch17

/**
 * Given a mutable Pair[S, T] class,
 * use a type constraint to define a swap method that can be called if the type parameters are the same.
 */
object Ex10 extends App {

  class Pair[S, T](var first: S, var second: T) {
    def swap(implicit ev: S =:= T) {
      val tempSecond = first
      val tempFirst = second
      first = tempSecond
      second = tempFirst
    }
  }


  //(new Pair("hello", 28)).swap => won't compile
  (new Pair("hello", "world")).swap


}
