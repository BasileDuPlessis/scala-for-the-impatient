package com.basile.scala.ch17

import scala.runtime.RichInt

/**
 * Why does RichInt implement Comparable[Int] and not Comparable[RichInt] ?
 */
object Ex05 extends App {

  class Pair[T <% Comparable[T]](val first: T, val second: T)

  val pairA = new Pair(4, 5)

  //because implicit conversion only occurs if method asked is undefined
}
