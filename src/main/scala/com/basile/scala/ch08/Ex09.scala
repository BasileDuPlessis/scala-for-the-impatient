package com.basile.scala.ch08

/**
 * In the Creature class of Section 8.10 , “ Construction Order and Early Definitions ,” on page 92 ,
 * replace val range with a def .
 *
 * What happens when you also use a def in the Ant subclass? What happens when you use a val in the subclass? Why?
 */
object Ex09 extends App {

  class Creature {
    def range: Int = 10
    val env: Array[Int] = new Array[Int](range)
  }

  class AntA extends Creature {
    override def range = 2
  }

  class AntB extends Creature {
    override val range = 5
  }

  //Def range is defined before primary constructor call =>
  assert( new AntA().env.size == 2)

  //Val range is defined after primary constructor call =>
  assert( new AntB().env.size == 0)


}
