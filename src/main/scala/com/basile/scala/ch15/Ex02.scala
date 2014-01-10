package com.basile.scala.ch15


object Ex02 extends App {
  
  @deprecated(message = "Use * instead", since = "1.0")
  class Multiply(val x: Int, val y : Int) {
    
    @deprecated(message = "Use * instead", since = "1.0")
    val result = x * y
    
    @deprecated(message = "Use * instead", since = "1.0")
    def multiply() = x * y
  }
  
  val M = new Multiply(2, 4)
  
  println(M.result)  
  println(M.multiply)
 

}