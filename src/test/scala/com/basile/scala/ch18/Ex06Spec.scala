package com.basile.scala.ch18

import org.scalatest.FlatSpec
import scala.collection.immutable._

/**
 *
 */
class Ex06Spec extends FlatSpec {

  val s = SortedSet(1,2,3,4,5,6)

  "getClosestValue(s, 10)" should "return Right(5)" in {
    assert(Ex06.getClosestValue(s, 10) == Right(5))
  }

  "getClosestValue(s, -5)" should "return Right(0)" in {
    assert(Ex06.getClosestValue(s, -5) == Right(0))
  }

  "getClosestValue(s, 3)" should "return Left(2)" in {
    assert(Ex06.getClosestValue(s, 3) == Left(2))
  }


}
