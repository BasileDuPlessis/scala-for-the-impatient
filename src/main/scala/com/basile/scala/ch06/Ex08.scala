package com.basile.scala.ch06

/**
 * Write an enumeration describing the eight corners of the RGB color cube.
 * As IDs, use the color values (for example, 0xff0000 for Red ).
 */
object Ex08 extends App {

  object RGBColorCube extends Enumeration {
    val r = Value(0xff0000, "red")
    val g = Value(0x00ff00, "green")
    val b = Value(0x0000ff, "blue")
    val rg = Value(0xffff00, "red-green")
    val rb = Value(0xff00ff, "red-blue")
    val gb = Value(0x00ffff, "green-blue")
    val bl = Value(0x000000, "black")
    val wh = Value(0xffffff, "white")
  }

  for (c <- RGBColorCube.values) println(c.id + ":" + c)

}
