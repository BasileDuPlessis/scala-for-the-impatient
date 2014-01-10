package com.basile.scala.ch15

import org.scalatest.junit.AssertionsForJUnit
import scala.collection.mutable.ListBuffer
import org.junit.Assert._
import org.junit.Test
import org.junit.Before

class Ex01Junit extends AssertionsForJUnit {
  @Test(timeout=50) def verifyMultiplyTimeout() {
    assertEquals(Ex01.multiply(2, 4), 8)
  }
  
  @Test def verifyMultiply() {
    assertEquals(Ex01.multiply(2, 4), 8)
  }

  @Test(expected = classOf[IllegalArgumentException]) def verifyMultiplyException() {
    assertEquals(Ex01.multiply(-4, 4), 8)
  }

  @Test(
      timeout=50,
      expected = classOf[IllegalArgumentException]
  ) def verifyMultiplyExceptionTimeout() {
    assertEquals(Ex01.multiply(-4, 4), 8)
  }
  
}