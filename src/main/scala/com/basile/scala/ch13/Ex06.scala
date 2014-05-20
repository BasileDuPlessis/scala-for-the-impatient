package com.basile.scala.ch13

/**
 * Given a list of integers lst , what is (lst :\ List[Int]())(_ :: _) ? (List[Int]() /: lst)(_ :+ _) ?
 *
 * How can you modify one of them to reverse the list?
 */
object Ex06 extends App {

  val lst = List(1,2,3,4,5,6,7,8,9)

  /*
  (lst :\ List[Int]())(_ :: _) => foldRight, create a new list with all integeres

  (List[Int]() /: lst)(_ :+ _) => foldLeft, create a new list with all integeres
  */

  assert( (List[Int]() /: lst)((i, l) => l +: i) == lst.foldLeft( List[Int]() )( (i, l) => l +: i ) )

}
