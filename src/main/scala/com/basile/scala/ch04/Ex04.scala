package com.basile.scala.ch04

import scala.io.Source._
import scala.collection.immutable.SortedMap

/**
 * Repeat the preceding exercise with a sorted map, so that the words are
 * printed in sorted order.
 */
object Ex04 extends App {

  val url = getClass.getResource("/ch04_ex02.txt")

  val words = fromURL(url).mkString.split("\\W+")

  //count each unique word in words and yield a tuple2
  val wordCounts = SortedMap[String, Int]() ++ ( for( w <- words.distinct ) yield (w, words.count( _==w )) )

  wordCounts.foreach(println)
}
