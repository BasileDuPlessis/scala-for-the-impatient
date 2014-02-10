package com.basile.scala.ch17

import org.scalatest.FlatSpec

/**
 *
 */
class Ex03Spec extends FlatSpec {

  "swap" should "returns a new pair with the components swapped" in {
    val p1 = new Ex03.Pair(4, "four")
    val p2 = Ex03.swap(p1)

    assert(p1.first == p2.second)
    assert(p1.second == p2.first)
  }

}
