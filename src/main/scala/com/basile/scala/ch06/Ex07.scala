package com.basile.scala.ch06

import com.basile.scala.ch06.Ex06.PlayingCards

/**
 * Implement a function that checks whether a card suit value from the preceding exercise is red.
 */
object Ex07 extends App {

  def isRed(cards: PlayingCards.PlayingCards) = {
    if (cards == PlayingCards.hearts || cards == PlayingCards.diamonds)
      true
    else
      false
  }

  assert( isRed(PlayingCards.diamonds) == true )
  assert( isRed(PlayingCards.spades) == false )

}
