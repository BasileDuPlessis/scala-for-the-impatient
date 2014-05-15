package com.basile.scala.ch09

import scala.io.Source

/**
 * Write a Scala code snippet that reads a file and prints all words with more than 12 characters to the console.
 * Extra credit if you can do this in a single line.
 */
object Ex03 extends App {

  val url = getClass.getResource("/ch04_ex02.txt")

  for(m <- """\w{12,}""".r.findAllIn(Source.fromURL(url).mkString)) println(m)

  """\w{12,}""".r.findAllIn(Source.fromURL(url).mkString).foreach{println(_)}
}
