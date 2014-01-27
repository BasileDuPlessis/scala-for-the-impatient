package com.basile.scala.ch16

import scala.xml._
/**
 * What is <fred/>(0) ? <fred/>(0)(0) ? Why?
 */
object Ex01 extends App {
  val xml = <fred/>

  assert(xml(0) == xml)
  assert(xml(0)(0) == xml)

  // <fred/>(0) call apply method : selects an element by its index in the immutable sequence.

}
