package com.basile.scala.ch15

import scala.annotation.tailrec

/**
 * Created by basile.duplessis on 10/01/14.
 */
object Ex07 extends App {

  class Sum {
    final def sumA(s: Seq[Int], partial: BigInt): BigInt = if (s.isEmpty) partial else sumA(s.tail, s.head + partial)

    def sumB(s: Seq[Int], partial: BigInt): BigInt = if (s.isEmpty) partial else sumB(s.tail, s.head + partial)
  }

  val s = 1 to 1000000

  val sum = new Sum()

  try {
    val resultA = sum.sumA(s, 0)
    println(resultA)

    val resultB = sum.sumB(s, 0)
    println(resultB)
  } catch {
    case exc: StackOverflowError => println("Stack Overflow")
  }

}
