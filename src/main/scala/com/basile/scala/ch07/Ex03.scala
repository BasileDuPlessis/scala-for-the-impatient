package com.basile.scala.ch07

package object Random {

  private var previous: Int = 0
  val a: Int = 1664525
  val b: Int = 1013904223
  val n: Int = 32

  def setSeed(seed: Int) {
    previous = seed
  }

  def nextInt(): Int = {
    previous = (previous * a + b) % scala.math.pow(2,n).toInt
    previous
  }

  def nextDouble(): Double = nextInt.toDouble
}


/**
 * Write a package random with functions nextInt(): Int , nextDouble(): Double , and setSeed(seed: Int): Unit .
 * To generate random numbers, use the linear congruential generator next = (previous × a + b ) mod 2 n ,
 * where a = 1664525, b = 1013904223, n = 32, and the inital value of previous is seed.
 */
object Ex03 extends App {

  println(Random.nextDouble())

}
