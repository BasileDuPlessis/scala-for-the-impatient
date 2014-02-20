package com.basile.scala.ch17


/**
 * It may seem strange to restrict method parameters in an immutable class Pair[+T] . However, suppose you
 * could define def replaceFirst(newFirst: T) in a Pair[+T] .
 * The problem is that this method can be overridden in an unsound way. Construct an example of the problem.
 * Define a subclass NastyDoublePair of Pair[Double] that overrides replaceFirst
 * so that it makes a pair with the square root of newFirst .
 * Then construct the call replaceFirst("Hello") on a Pair[Any] that is actually a NastyDoublePair .
 */
object Ex09 extends App {
/*
  class Pair[+T](val first: T, val second: T) {
    def replaceFirst(newFirst:T) = new Pair[T](newFirst, second)
  }

  class NastyDoublePair(first: Double, second: Double) extends Pair[Double](first, second) {
    override def replaceFirst(newFirst: Double) = new NastyDoublePair(scala.math.sqrt(newFirst), second)
  }

  val myNasty: Pair[Any] = new NastyDoublePair(4.5 , 4.5)

  val myNewNasty = myNasty.replaceFirst("hello")

  println(myNewNasty.first)

  //If compiling this code will be incoherent
*/
}
