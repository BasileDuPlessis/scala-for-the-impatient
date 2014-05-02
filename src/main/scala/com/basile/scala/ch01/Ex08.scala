package com.basile.scala.ch01

import scala.math.BigInt._
import scala.util.Random

/**
 * One way to create random file or directory names is to produce a random BigInt and convert it to base 36,
 * yielding a string such as "qsnvbevtomcj38o06kul" .
 * Poke around Scaladoc to find a way of doing this in Scala.
 */
object Ex08 extends App {

  println(
    probablePrime(100, Random).toString(36)
  )

}
