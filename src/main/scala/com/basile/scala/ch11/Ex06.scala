package com.basile.scala.ch11

/**
 * Provide a class ASCIIArt whose objects contain figures such as /\_/\ ( ' ' ) ( - ) | | | precedence.
 */
object Ex06 extends App {

  class ASCIIArt(private val _items: Array[String]) {

    def this(s: String) {
      this(s.split("\r\n"))
    }

    def +(that: ASCIIArt) = {
      new ASCIIArt(
        (for(k <- _items.indices) yield that.items.isDefinedAt(k) match {
          case true => _items(k) + that.items(k)
          case _ => _items(k)
        }).toArray
      )
    }

    def /(that: ASCIIArt) = {
      new ASCIIArt(
        _items ++ that.items
      )
    }

    def items = _items

    override def toString = _items.mkString("\n")
  }

  val a = new ASCIIArt(""" /\_/\
( ' ' )
(  -  )
 | | |
(__|__)""")

  val b = new ASCIIArt("""   -----
 / Hello \
<  Scala |
 \ Coder /
   -----""")

  val c = new ASCIIArt("""   -----
 / Hello \
<  Scala |
 \ Coder /
   -----""")


  println(a/c + b)

}
