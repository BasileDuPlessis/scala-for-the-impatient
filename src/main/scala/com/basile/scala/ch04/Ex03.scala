package com.basile.scala.ch04

import scala.io.Source._

/**
 * Repeat the preceding exercise with an immutable map.
 */
object Ex03 extends App {


  val url = getClass.getResource("/ch04_ex02.txt")

  val words = fromURL(url).mkString.split("\\W+")
  val wordCounts = (for(w <- words.distinct) yield (w, words.count(_==w))).toMap

  wordCounts.foreach(println)

}
