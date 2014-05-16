package com.basile.scala.ch10

/**
 * Provide a CryptoLogger trait that encrypts the log messages with the Caesar cipher.
 * The key should be 3 by default, but it should be overridable by the user.
 *
 * Provide usage examples with the default key and a key of –3.
 */
object Ex04 extends App {

  trait Logger {
    def log(msg: String) {}
  }

  trait PrinterLogger extends Logger {
    override def log(msg: String) {
      println(msg)
    }
  }

  trait CryptoLogger extends Logger {
    val key=3
    override def log(msg: String) {
      val ceasarMsg = for(c <- msg.toUpperCase) yield c match {
        case a if 'A'<=a && a<='Z' => applyKey(a, 'A', 26)
        case a if '0'<=a && a<='9' => applyKey(a, '0', 10)
        case _ => c
      }
      super.log(ceasarMsg)
    }
    private def applyKey(c: Char, r: Char, l: Int): Char = {
      ((c - r + key)%l + r).toChar
    }
  }

  class Person(private val _name: String) extends {override val key=9} with PrinterLogger with CryptoLogger {
    def name = {log(_name); _name}
  }

  val JohnSmith = new Person("John Smith")

  JohnSmith.name

}
