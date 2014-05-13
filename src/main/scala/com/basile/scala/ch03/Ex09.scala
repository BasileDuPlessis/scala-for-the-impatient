package com.basile.scala.ch03

import java.util.TimeZone

/**
 * Make a collection of all time zones returned by java.util.TimeZone.getAvailableIDs that are in America.
 * Strip off the "America/" prefix and sort the result.
 */
object Ex09 extends App {

  val id = "America/"
  val a = TimeZone.getAvailableIDs.filter(_.startsWith(id)).map(_.stripPrefix(id)).sorted

  a.foreach(println)

}
