package com.basile.scala.ch21

/**
 * Look up the =:= object in Predef.scala . Explain how it works.
 */
object Ex09 extends App {



  class Operator[A](value: A) {
    def concatenate(implicit ev: A=:=String): String  = value + " World"
    def multiply(implicit ev: A=:=Int): Int  = value * 2
  }

  assert(new Operator("Hello").concatenate == "Hello World") //return Hello World
  assert(new Operator(2).multiply == 4) //return 4

  //new Operator(2).concatenate => Compiler error


  /*
  Concatenate needs an implicit parameter of type A=:=String which is an instance of class =:=[A, String]

  One way to find it is to search in companion object =:=

  If companion object contains such an implicit value it is called at runtime

  Otherwise the compiler return : Cannot prove that A =:= String

  We can check the implicit value with implicitly[=:=[String, String]] or implicitly[=:=[Int, Int]]


*/



}
