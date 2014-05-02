package com.basile.scala.ch01

/**
 * What do the take , drop , takeRight , and dropRight string functions do?
 * What advantage or disadvantage do they have over using substring ?
 */
object Ex10 extends App {

  val s = "Scala"

  assert( s.take(2) == "Sc" )
  assert( s.drop(2) == "ala" )
  assert( s.takeRight(2) == "la" )
  assert( s.dropRight(2) == "Sca" )

  //We can chain all these methods easily
  assert( s.drop(1).take(3) == "cal" )

}
