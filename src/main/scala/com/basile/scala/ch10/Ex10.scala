package com.basile.scala.ch10

import java.io._

/**
 * Implement a class IterableInputStream that extends java.io.InputStream with the trait Iterable[Byte] .
 */
object Ex10 extends App {

  class IterableInputStream(is: InputStream) extends InputStream with Iterable[Byte] {
    def iterator = new Iterator[Byte] {
      def hasNext = is.available > 0
      def next() = is.read.toByte
    }
    def read = is.read
  }

  val bi =  new IterableInputStream(new FileInputStream("README.md")).iterator

  val r = bi.map(_.toChar).mkString

  println(r)

}
