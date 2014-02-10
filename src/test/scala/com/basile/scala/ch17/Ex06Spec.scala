package com.basile.scala.ch17

import org.scalatest.FlatSpec

/**
 *
 */
class Ex06Spec extends FlatSpec {
  "Ex06.middle" should "returns the middle element from any Iterable[T]" in {
    assert(Ex06.middle("World") == 'r')
    assert(Ex06.middle(List(1,2,3,4)) == 3)
  }
}
