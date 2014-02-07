package com.basile.scala.ch16

import org.scalatest.FlatSpec
import scala.collection.mutable.Map

/**
 *
 */
class Ex08Spec extends FlatSpec {

  "dlToMap" should "takes a dl element and turns it into a Map[String, String]" in {
    val m = Ex08.dlToMap(<dl><dt>A</dt><dd>1</dd><dt>B</dt><dd>2</dd></dl>)

    assert(m == Map("A" -> "1", "B" -> "2"))
  }

}
