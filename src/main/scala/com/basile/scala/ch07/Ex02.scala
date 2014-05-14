package com.basile.scala.ch07 {

  object Ex02 {
    val a = 1
  }

  package com.basile.scala.ch07 {

    /**
     * Write a puzzler that baffles your Scala friends, using a package com that isn’t at the top level.
     */
    object Ex02 extends App {
      val a = 1

      assert( _root_.com.basile.scala.ch07.Ex02.a == com.basile.scala.ch07.Ex02.a)
    }

  }

}


