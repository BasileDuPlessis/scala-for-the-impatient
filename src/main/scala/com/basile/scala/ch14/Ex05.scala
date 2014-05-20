package com.basile.scala.ch14

/**
 * One can use lists to model trees that store values only in the leaves.
 * For example, the list ((3 8) 2 (5)) describes the tree However, some of the list elements are numbers
 * and others are lists. In Scala, you cannot have heterogeneous lists, so you have to use a List[Any] .
 *
 * Write a leafSum function to compute the sum of all elements in the leaves, using pattern matching to
 * differentiate between numbers and lists.
 *
 */
object Ex05 extends App {

  val l = List(List(3, 8), 2, List(5))

  def leafSum(l: List[Any]): Int = l.map(_ match {
    case l: List[Any] => leafSum(l)
    case x: Int => x
    case _ => 0
  }).sum

  assert( leafSum(l) == 18)

}
