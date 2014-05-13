package com.basile.scala.ch04

import scala.io.Source._

/**
 * Write a program that reads words from a file. Use a mutable map to count how often each word appears.
 * To read the words, simply use a java.util.Scanner :
 * val in = new java.util.Scanner(new java.io.File("myfile.txt")) while (in.hasNext()) process in.next()
 *
 * Or look at Chapter 9 for a Scalaesque way. At the end, print out all words and their counts.
 */
object Ex02 extends App {

  val url = getClass.getResource("/ch04_ex02.txt")

  val in = fromURL(url).mkString
  val uw = new scala.collection.mutable.HashMap[String, Int]

  for(w <- in.split("\\W+")) uw(w) = uw.getOrElse(w, 0) + 1
  //or
  in.split("\\W+").foreach(w => uw(w) = uw.getOrElse(w, 0) + 1)

  uw.foreach(println)

}
