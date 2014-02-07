package com.basile.scala.ch16

import org.scalatest.FlatSpec


/**
 *
 */
class Ex07Spec extends FlatSpec {

  "mapToDl" should "returns a dl element with a dt for each key and dd for each value" in {
    val dl = Ex07.mapToDl(Map("A" -> "1", "B" -> "2"))
    assert(dl == <dl><dt>A</dt><dd>1</dd><dt>B</dt><dd>2</dd></dl>)
  }

}
