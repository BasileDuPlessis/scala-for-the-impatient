package com.basile.scala.ch10


import java.io._

/**
 * Using the logger traits from this chapter, add logging to the solution of the preceding problem
 * that demonstrates buffering.
 */
object Ex09 extends App {


  trait Logger {
    def log(msg: String) {}
  }

  trait Buffering extends Logger {
    this: FileInputStream =>
    val b = new BufferedInputStream(this)
    override def read(): Int = {
      log("Diff Available : " + (b.available - this.available))
      b.read()
    }
    override def log(msg: String) {
      println(msg)
    }
  }

  val b = new FileInputStream("README.md") with Buffering

  Iterator.continually(b.read()).takeWhile(_ != -1).map(_.toChar).mkString

}
