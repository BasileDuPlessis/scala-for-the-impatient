package com.basile.scala.ch16

import scala.xml.{Elem, Text}

/**
 * Write a function that takes a dl element and turns it into a Map[String, String] .
 * This function should be the inverse of the function in the preceding exercise,
 * provided all dt children are distinct.
 */
object Ex08 {

  def dlToMap(dl: Elem): Map[String, String] = {

    var keys, values = List[String]()

    dl.child.foreach{
      n => n match {
        case <dt>{Text(t)}</dt> => keys :+= t
        case <dd>{Text(t)}</dd> => values :+= t
      }
    }

    keys.zip(values).toMap

  }


}
