package com.basile.scala.ch18

import scala.collection.immutable._

/**
 * CThe Either type in the Scala library can be used for algorithms that return either
 * a result or some failure information.
 * Write a function that takes two parameters: a sorted array of integers and an integer value.
 * Return either the index of the value in the array or the index of the element that is closest to the value.
 * Use an infix type as the return type.
 */
object Ex06 extends App {

  def getClosestValue(s: SortedSet[Int], value: Int): Int Either Int = {

    s.zipWithIndex.collectFirst{
      case (`value`, i) => Left(i)
      case (v, i) if v > value => Right(i)
    }.getOrElse(Right(s.size - 1))

  }

}
