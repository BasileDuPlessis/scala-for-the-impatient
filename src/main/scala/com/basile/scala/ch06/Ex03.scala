package com.basile.scala.ch06

/**
 * Define an Origin object that extends java.awt.Point . Why is this not actually a good idea?
 * (Have a close look at the methods of the Point class.)
 */
object Ex03 extends App {

  object Origin extends java.awt.Point {}

  //Code below will look strange !
  Origin.move(2, 2)

}
