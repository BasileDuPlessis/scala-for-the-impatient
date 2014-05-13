package com.basile.scala.ch04

/**
 * What happens when you zip together two strings, such as "Hello".zip("World") ? Come up with a plausible use case.
 */
object Ex10 extends App {

  val r = "Hello".zip("World")

  /*
  It returns a Sequence of Tuple with characters from same position in each string
  (H,W)
  (e,o)
  (l,r)
  (l,l)
  (o,d)
   */

  //use case => find difference between two strings
  val diff = "Hello world !".zip("hello world!").filter(t => t._1!=t._2)

  assert( diff == IndexedSeq(('H','h'), (' ','!')) )


}
