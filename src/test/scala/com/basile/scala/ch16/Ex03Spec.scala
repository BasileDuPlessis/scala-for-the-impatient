package com.basile.scala.ch16

import org.scalatest.FlatSpec

class Ex03Spec extends FlatSpec {

  "getTextFromLi" should "return text from node" in {
    assert(Ex03.getTextFromElem(<li>Fred</li>) == "Fred")
  }

  "getTextFromLi" should "throw MatchError Exception" in {
    intercept[MatchError](Ex03.getTextFromElem(<li>{"Fred"}</li>))
  }

  "getAtomFromLi" should "return text from node" in {
    assert(Ex03.getAtomFromElem(<li>{"Fred"}</li>) == "Fred")
  }

}
