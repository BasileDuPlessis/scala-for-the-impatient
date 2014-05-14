package com.basile.scala.ch06

/**
 * Write an enumeration describing the four playing card suits so that the toString method returns ♣, ♦, ♥, or ♠.
 */
object Ex06 extends App {

  object PlayingCards extends Enumeration {
    type PlayingCards = Value
    val spades = Value("\u2660")
    val diamonds = Value("\u2666")
    val hearts = Value("\u2665")
    val clubs = Value("\u2663")
  }

  for (c <- PlayingCards.values) println(c.id)

}
