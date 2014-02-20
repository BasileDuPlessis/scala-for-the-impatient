package com.basile.scala.ch18

import scala.collection.mutable.ArrayBuffer

/**
 * 4. Implement the equals method for the Member class that is nested inside the Network class in Section 18.2,
 * “ Type Projections ,” on page 247 .
 * For two members to be equal, they need to be in the same network.
 */
object Ex04 extends App {


  class Network {
    class Member(val name: String) {
      val contacts = new ArrayBuffer[Member]

      def equals(that: Member):Boolean = true
    }
    private val members = new ArrayBuffer[Member]

    def join(name: String) = {
      val m = new Member(name)
      members += m
      m
    }
  }

  val NetworkA = new Network
  val NetworkB = new Network

  val Basile = NetworkA.join("Basile")
  val John = NetworkA.join("John")
  val Alfred = NetworkB.join("Alfred")

  assert(Basile.equals(Alfred) == false)
  assert(Basile.equals(John) == true)

}
