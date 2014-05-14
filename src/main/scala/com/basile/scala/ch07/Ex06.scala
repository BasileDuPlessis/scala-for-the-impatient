package com.basile.scala.ch07

import java.util.{HashMap => JavaHashMap}
import scala.collection.mutable._

/**
 * Write a program that copies all elements from a Java hash map into a Scala hash map.
 *
 * Use imports to rename both classes.
 */
object Ex06 extends App {

  val hm = new JavaHashMap[String, String]()
  hm.put("1", "John")
  hm.put("2", "Paul")

  val shm = HashMap[String, String]()

  val it = hm.entrySet().iterator()
  while (it.hasNext()) {
    val pairs = it.next()
    shm += (pairs.getKey() -> pairs.getValue())
  }

  shm.foreach(println)

}
