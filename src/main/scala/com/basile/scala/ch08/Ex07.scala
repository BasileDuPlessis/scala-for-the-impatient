package com.basile.scala.ch08

import java.awt.Rectangle

/**
 * Provide a class Square that extends java.awt.Rectangle and has three constructors: one that constructs a square
 * with a that constructs a square with corner (0, 0) and width 0 .
 */
object Ex07 extends App {

  class Square(x: Int, y: Int, width: Int) extends Rectangle(x, y, width, width) {
    def this(width: Int) {
      this(0, 0, width)
    }
    def this() {
      this(0, 0, 0)
    }
  }

  val s1 = new Square(10, 10, 150)
  val s2 = new Square(150)
  val s3 = new Square()

}
