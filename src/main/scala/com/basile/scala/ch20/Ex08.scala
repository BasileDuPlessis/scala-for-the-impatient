package com.basile.scala.ch20

import scala.actors.{Exit, Actor}

/**
 * Show how an actor-based program can deadlock when one sends synchronous messages.
 */
object Ex08 extends App {

  case class Bow(p: Person)
  case class BowBack(p: Person)


  /*
  Scala implementation of http://docs.oracle.com/javase/tutorial/essential/concurrency/deadlock.html
   */
  class Person(val name: String) extends Actor {
    def act {
      while (true) {
        receive {
          case Bow(p) => {
            println(p.name + " has bowed to " + this.name)
            p !? BowBack(this)
          }
          case BowBack(p) => {
            println(p.name + " has bowed back to " + this.name)
            reply()
          }
        }
      }
    }
  }

  val Alphonse = new Person("Alphonse")
  val Alfred = new Person("Alfred")

  Alphonse.start()
  Alfred.start()

  Alphonse ! Bow(Alfred)
  //Must sleep between call to prevent deadlock
  //Thread.sleep(100)
  Alfred ! Bow(Alphonse)


}
