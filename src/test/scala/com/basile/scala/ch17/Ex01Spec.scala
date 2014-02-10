package com.basile.scala.ch17

import org.scalatest.FlatSpec

/**
 * Created by basile.duplessis on 07/02/14.
 */
class Ex01Spec extends FlatSpec {

  "Pair.swap" should "returns a new pair with the components swapped" in {
    val p1 = new Ex01.Pair(4, "Four")
    val p2 = p1.swap
    assert(p2.first == p1.second)
    assert(p2.second == p1.first)
  }

}
