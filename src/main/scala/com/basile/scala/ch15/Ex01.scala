package com.basile.scala.ch15

object Ex01 extends App {
  /**
   * Multiply two values
   * 
   * @param x first value    
   * @param y second value
   * 
   */
  def multiply(x: Int, y: Int) = if (x>0) x * y
		  else throw new IllegalArgumentException("x should be > 0")
}