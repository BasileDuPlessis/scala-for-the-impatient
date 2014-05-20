package com.basile.scala.ch14

/**
 * Write a function that computes the sum of the non- None values in a List[Option[Int]] . Don’t use a match statement.
 */
object Ex09 extends App {

  val l:List[Option[Int]] = List(Some(1), Some(2), None, Some(4))

  def sum(l: List[Option[Int]]): Int = (for(Some(v) <- l) yield v).sum

  assert( sum(l) == 7 )

}
