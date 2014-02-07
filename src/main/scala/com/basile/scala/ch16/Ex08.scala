package com.basile.scala.ch16

import scala.xml.{Elem, Text}
import scala.collection.mutable.Map

/**
 * Write a function that takes a dl element and turns it into a Map[String, String] .
 * This function should be the inverse of the function in the preceding exercise,
 * provided all dt children are distinct.
 */
object Ex08 {

  def dlToMap(dl: Elem): Map[String, String] = {
    val m = Map[String, String]()
    var k, v = ""

    dl.child.foreach{
      n => n match {
        case <dt>{Text(t)}</dt> => k = t
        case <dd>{Text(t)}</dd> => v = t
      }
      m.put(k, v)
    }

    m

  }


}
