package com.basile.scala.ch13

/**
 * Write a function that receives a collection of strings and a map from strings to integers.
 * Return a collection of integers that are values of the map corresponding to one of the strings in the collection.
 *
 * For example, given Array("Tom", "Fred", "Harry") and Map("Tom" -> 3, "Dick" -> 4, "Harry" -> 5) , return Array(3, 5)
 *
 * Hint: Use flatMap to combine the Option values returned by get .
 */
object Ex04 extends App {

  def indexes(a: Array[String], m: Map[String,Int]) = a.flatMap(m.get(_))

  val a = Array("Tom", "Fred", "Harry")
  val m = Map("Tom" -> 3, "Dick" -> 4, "Harry" -> 5)

  assert(indexes(a, m).deep == Array(3,5).deep)

}
