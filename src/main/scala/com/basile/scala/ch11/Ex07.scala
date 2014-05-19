package com.basile.scala.ch11

/**
 * Implement a class BitSequence that stores a sequence of 64 bits packed in a Long value.
 * Supply apply and update operators to get and set an individual bit.
 */
object Ex07 extends App {

  class BitSequence(s: String) {
    var l = java.lang.Long.parseLong(s, 2)

    def apply(i: Int) = l & (1<<i)
    def update(i: Int, v: Boolean) {
      l = v match {
        case true => l | (1<<i)
        case false => l ^ (1<<i)
      }
    }
    override def toString = l.toString
  }

  val b = new BitSequence("010101010101010101010101010101010101010101010101010101010101110")

  b(0) = true

  assert( b.toString == "3074457345618258607" )

}
