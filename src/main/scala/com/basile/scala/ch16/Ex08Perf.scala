package com.basile.scala.ch16

/**
 * Compare performance for immutable var / mutable val
 * varImmutable: 1641ms
 * valMutable: 110ms
 * valArrayFixedLength: 93ms
 * scalac Hello.scala
 * scala -Dscala.time Hello
 */
object Ex08Perf extends App {

  val count = 100000

  def varImmutable {
    var l = List[Int]()
    (1 to count).foreach{
      i => l :+= i
    }
  }

  def valMutable {
    val a = scala.collection.mutable.ArrayBuffer[Int]()
    (1 to count).foreach{
      i => a.append(i)
    }
  }

  def valArrayFixedLength {
    val a = new Array[Int](count)
    (1 to count).foreach{
      i => a(i) = i
    }
  }


}
