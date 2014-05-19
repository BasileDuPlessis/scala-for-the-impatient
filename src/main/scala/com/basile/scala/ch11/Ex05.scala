package com.basile.scala.ch11

import scala.collection.mutable.ArrayBuffer

/**
 * HTML table. For example,
 * | "Java" | "Scala" || "Gosling" | "Odersky" || "JVM" | "JVM, .NET"
 * should produce <table><tr><td>Java</td><td>Scala</td></tr><tr><td>Gosling...
 */
object Ex05 extends App {

  class Table {
    private val items = ArrayBuffer[ArrayBuffer[String]](ArrayBuffer())

    def |(s: String): Table = {
      items(items.length-1).append(s)
      this
    }
    def ||(s: String): Table = {
      items += ArrayBuffer(s)
      this
    }
    override def toString = items.map(_.mkString("<td>", "<td></td>", "</td>")).mkString("<table><tr>", "</tr><tr>", "</tr></table>")
  }

  object Table {
    def apply() = new Table
  }

  val result = Table() | "Java" | "Scala" || "Gosling" | "Odersky" || "JVM" | "JVM, .NET"

  assert( result.toString == "<table><tr><td>Java<td></td>Scala</td></tr><tr><td>Gosling<td></td>Odersky</td></tr><tr><td>JVM<td></td>JVM, .NET</td></tr></table>" )

}
