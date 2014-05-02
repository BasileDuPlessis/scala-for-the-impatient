package com.basile.scala.ch01

import scala.BigInt.probablePrime
import scala.util.Random

/**
 * What do you need to import so that you can get a random prime as probablePrime(100, Random) ,
 * without any qualifiers before probablePrime and Random ?
 */
object Ex07 extends App {

  println(probablePrime(100, Random))

}
