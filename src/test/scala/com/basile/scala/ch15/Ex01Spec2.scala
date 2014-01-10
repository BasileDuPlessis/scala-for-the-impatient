package com.basile.scala.ch15

import org.scalatest.FlatSpec

class Ex01Spec2 extends FlatSpec {
	"Multiply" should "multiply x by y" in {
	  assert(Ex01.multiply(2,4) == 8)
	}
}