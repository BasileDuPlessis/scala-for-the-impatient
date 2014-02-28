package com.basile.scala.ch19

import org.scalatest.FlatSpec

/**
 * Created by basile.duplessis on 27/02/14.
 */
class Ex03Spec extends FlatSpec {

  "ListParser" should "return List[Int]" in {

    val parser = new Ex03.ListParser
    val result = parser.parseAll(parser.elem, "(1, 23, -79, 4)")

    assert(result.get == List(1, 23, -79, 4))
  }

}
