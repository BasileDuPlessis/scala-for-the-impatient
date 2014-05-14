package com.basile.scala.ch06

/**
 * Write a Scala application, using the App trait, that prints the command-line arguments in reverse order,
 * separated by spaces.
 *
 * For example, scala Reverse Hello World should print World Hello .
 */
object Ex05 extends App {
  if (args.length > 0) println(args.reverse.mkString(" "))
}
