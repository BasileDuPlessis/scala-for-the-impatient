package com.basile.scala.ch17

/**
 * In Section 17.10 , “ Co- and Contravariant Positions ,” on page 238 , the replaceFirst method has a type bound.
 * Why can’t you define an equivalent method on a mutable Pair[T] ?
 * Click here to view code image def replaceFirst[R >: T](newFirst: R) { first = newFirst } // Error
 */
object Ex08 extends App {

  /*
  class Pair[+T](var first: T, var second: T) {

    def replaceFirst[R >: T](replacement: R) { first = replacement }
    def replaceFirst(replacement: T) { first = replacement }

  } => won't compile
  */


  //Because I can't add a super type of T in my Pair it will begin a Pair[Supertype]
  //Mutable class are invariant

}
