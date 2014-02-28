package com.basile.scala.ch19

import org.scalatest.FlatSpec

/**
 *
 */
class Ex04Spec extends FlatSpec {

  "DateParser.parseDate" should "return Date" in {

    val parser = new Ex04.DateParser
    val result = parser.parseAll(parser.parseDate, "2014-02-27 17:19:41")

    assert(result.get.toString == "Thu Feb 27 17:19:41 CET 2014")
  }

}
