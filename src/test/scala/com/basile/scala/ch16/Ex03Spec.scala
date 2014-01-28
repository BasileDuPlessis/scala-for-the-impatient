package com.basile.scala.ch16

import org.scalatest.FlatSpec

class Ex03Spec extends FlatSpec {

  "getTextFromLi" should "return text from node" in {
    assert(Ex03.getTextFromLi(<li>Fred</li>) == "Fred")
  }

  "getTextFromLi" should "throw MatchError Exception" in {
    intercept[MatchError](Ex03.getTextFromLi(<li>{"Fred"}</li>))
  }

  "getAtomFromLi" should "return text from node" in {
    assert(Ex03.getAtomFromLi(<li>{"Fred"}</li>) == "Fred")
  }

}
