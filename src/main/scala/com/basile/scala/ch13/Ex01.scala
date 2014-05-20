package com.basile.scala.ch13

import scala.collection.mutable.Map
import scala.collection.mutable.SortedSet

/**
 * Write a function that, given a string, produces a map of the indexes of all characters.
 * For example, indexes("Mississippi") should return a map associating 'M' with the set {0} ,
 * 'i' with the set {1, 4, 7, 10} , and so on.
 *
 * Use a mutable map of characters to mutable sets. How can you ensure that the set is sorted?
 */
object Ex01 extends App {

  def indexes(s: String) = {
    s.indices.foldLeft( Map[Char, SortedSet[Int]]() ) {
      (m, i) =>
        m += ( s(i) -> (m.getOrElse(s(i), SortedSet[Int]()) += i)	)
    }
  }

  /*Should print
  M:{0}
  s:{2,3,5,6}
  p:{8,9}
  i:{1,4,7,10}
   */
  indexes("Mississippi").foreach(t => println(t._1 + ":" + t._2.mkString("{", ",", "}")) )

}
