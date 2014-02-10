package com.basile.scala.ch17

/**
 * Write a generic method middle that returns the middle element from any Iterable[T].
 * For example, middle("World") is 'r' .
 */
object Ex06 {
  def middle[T](it: Iterable[T]): T = {
    val l = it.toList
    l(l.size / 2)
  }
}
