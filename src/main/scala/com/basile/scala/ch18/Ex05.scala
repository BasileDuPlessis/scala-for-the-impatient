package com.basile.scala.ch18

import scala.collection.mutable.ArrayBuffer
import scala.language.existentials

/**
 * Consider the type alias
 * type NetworkMember = n.Member forSome {val n: Network]
 * def process(m1: NetworkMember, m2: NetworkMember) = (m1, m2)
 * How does this differ from the process function in Section 18.8 , “ Existential Types ,” on page 252 ?
 */
object Ex05 extends App {

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

  def processA[ T <: n.Member forSome {val n: Network} ](m1: T, m2: T) = (m1, m2)

  type NetworkMember = n.Member forSome {val n: Network}

  def processB(m1: NetworkMember, m2: NetworkMember) = (m1, m2)

  processA(Basile, John)
  //processA(Basile, Alfred) will not compile because Network is defined once for the two parameters

  //n.Member forSome {val n: Network}
  processB(Basile, John)
  processB(Basile, Alfred)

}
