package com.basile.scala.ch02

/**
 * What is the value of an empty block expression {} ? What is its type?
 */
object Ex02 extends App {

  //Unit has only one value which is ()
  assert( {} == () )

  //His type is Unit
  assert( {}.isInstanceOf[Unit] )

}
