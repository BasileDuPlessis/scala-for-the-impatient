package com.basile.scala.ch20

import scala.actors.Actor

/**
 * Write a program that constructs 100 actors that use a while(true) / receive loop,
 * calling println(Thread.currentThread) when they receive a 'Hello message,
 * and 100 actors that do the same with loop / react .
 *
 * Start them all, and send them all a message. How many threads are occupied by the first kind, and how many by the second kind?
 */
object Ex06 extends App {

  class ReceiveActor extends Actor {
    def act {
      while (true) {
        receive {
          case "Hello" => println("Receive: " + Thread.currentThread)
        }
      }
    }
  }

  class ReactActor extends Actor {
    def act {
      loop {
        react {
          case "Hello" => println("React: " + Thread.currentThread)
        }
      }
    }
  }


  /*
  100 thread are created
   */
  (for (i <- (1 to 100)) yield {
    (new ReceiveActor).start
  }).foreach { a =>
    a ! "Hello"
  }

  /*
  One thread is created
   */
  (for (i <- (1 to 100)) yield {
    (new ReactActor).start
  }).foreach { a =>
    a ! "Hello"
  }


}
