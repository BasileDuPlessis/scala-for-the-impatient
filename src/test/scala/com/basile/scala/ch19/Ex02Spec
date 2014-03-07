package com.basile.scala.ch19

import org.scalatest.FlatSpec

/**
 *
 */
class Ex02Spec extends FlatSpec {

  val parser = new Ex02.ExprParser


  "4^2^3" should "return 65536" in {
    val result = parser.parseAll(parser.expr, "4^2^3")

    assert(result.get == 65536.0)
  }

}
