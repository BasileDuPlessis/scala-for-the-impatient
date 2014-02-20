package com.basile.scala.ch18

/**
 * Implement a method that receives an object of any class that has a method def close(): Unit
 * together with a function that processes that object.
 * Call the function and invoke the close method upon completion, or when any exception occurs.
 */
object Ex07 extends App {

  class Closable[T <: {def close(): Unit}] {

    def add(obj: T) {
      process(obj)
    }

    def process(obj: T) {
      try {
        println("Processing object...")
      } finally {
        obj.close
      }

    }

  }

  class Door {
    def close() {println("Door is closed")}
  }

  val myDoor = new Door

  new Closable().add(myDoor)


}
