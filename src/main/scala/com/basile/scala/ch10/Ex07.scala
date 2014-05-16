package com.basile.scala.ch10

/**
 * There are dozens of Scala trait tutorials with silly examples of barking dogs or philosophizing frogs.
 * Reading through contrived hierarchies can be tedious and not very helpful, but designing your own is very illuminating.
 *
 * Make your own silly trait hierarchy example that demonstrates layered traits,
 * concrete and abstract methods, and concrete and abstract fields.
 */
object Ex07 extends App {

  trait Woody {
    val wood: String;
    def play {
      println("Groovy")
    }
  }

  trait Fretless {
    val inlined = false
    def plug
  }

  class Roscoe(val wood: String) extends Woody with Fretless {
    def plug {
      println("Bass is plugged")
    }
  }

  val r = new Roscoe("mapple")
}
