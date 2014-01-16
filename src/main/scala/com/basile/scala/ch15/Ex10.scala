package com.basile.scala.ch15

import scala.annotation.elidable, elidable._

/**
 * Created by basile.duplessis on 10/01/14.
 * -Xelide-below MAXIMUM =>   public int factorial(int);
    Code:
       0: iconst_0
       1: ireturn
 */
object Ex10 extends App {

  @elidable(ALL) def factorial(n: Int): Int = {
    assert(n >= 0)
    if (n<=0) 1 else n * factorial(n-1)
  }


  println(factorial(-1))

}
