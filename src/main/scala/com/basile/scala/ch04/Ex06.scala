package com.basile.scala.ch04

/**
 * Define a linked hash map that maps "Monday" to java.util.Calendar.MONDAY , and similarly for the other weekdays.
 * Demonstrate that the elements are visited in insertion order.
 */
object Ex06 extends App {

  val days = scala.collection.mutable.LinkedHashMap(
    "MONDAY" -> java.util.Calendar.MONDAY,
    "TUESDAY" -> java.util.Calendar.TUESDAY,
    "WEDNESDAY" -> java.util.Calendar.WEDNESDAY,
    "THURSDAY" -> java.util.Calendar.THURSDAY,
    "FRIDAY" -> java.util.Calendar.FRIDAY,
    "SATURDAY" -> java.util.Calendar.SATURDAY,
    "SUNDAY" -> java.util.Calendar.SUNDAY
  )

  assert(days == (for(d <- days) yield d))

}
