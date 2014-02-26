package com.basile.scala.ch19

import org.scalatest.FlatSpec

/**
 *
 */
class Ex01Spec extends FlatSpec {

  val parser = new Ex01.ExprParser


  "20/(2*(3+4))-5%2" should "return 0.42857146" in {
    val result = parser.parseAll(parser.expr, "20/(2*(3+4))-5%2")

    assert(result.get == 0.42857146.toFloat)
  }

}
