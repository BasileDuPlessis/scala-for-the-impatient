package com.basile.scala.ch10

import java.io._

/**
 * In the java.io library, you add buffering to an input stream with a BufferedInputStream decorator.
 * Reimplement buffering as a trait. For simplicity, override the read method.
 */
object Ex08 extends App {

  trait Buffering {
    this: FileInputStream =>
      val b = new BufferedInputStream(this)
      override def read(ab: Array[Byte]): Int = {
        b.read(ab)
      }
  }

  val b = new FileInputStream("README.md") with Buffering
  val ab = new Array[Byte](1024)

  b.read(ab)

  ab.foreach(a => print(a.toChar))
}
