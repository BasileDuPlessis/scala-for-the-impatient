package com.basile.scala.ch09

import scala.io.Source

/**
 * Write a Scala program that reads a text file containing average, maximum, and minimum of the numbers in the file.
 */
object Ex04 extends App {

  val url = getClass.getResource("/ch09_ex04.txt")

  val floats = """[0-9\.]+""".r.findAllIn(Source.fromURL(url).mkString).map(_.toDouble).toArray

  List(
  "Max: " + floats.max,
  "Min: " + floats.min,
  "Average: " + floats.sum/floats.length
  ).foreach(println)


}
