package com.basile.scala.ch13

/**
 * Harry Hacker writes a program that accepts a sequence of file names on the command line.
 * For each, he starts a new thread that reads the file and updates a letter frequency map,
 * declared as val frequencies = new scala.collection.mutable.HashMap[Char, Int]
 * with scala.collection.mutable.SynchronizedMap[Char, Int]
 *
 * When reading a letter c , he calls = frequencies.getOrElse(c, 0) + 1
 *
 * Why won’t this work?
 * Will it work if he used instead
 *
 * import scala.collection.JavaConversions.asScalaConcurrentMap
 * val frequencies: scala.collection.mutable.ConcurrentMap[Char, Int] = new java.util.concurrent.ConcurrentHashMap[Char, Int]
 */

object AtomicMap {
  import scala.io.Source
  import scala.collection.JavaConversions.mapAsScalaConcurrentMap
  import java.util.concurrent.atomic.AtomicInteger

  var done = 0

  val frequencies: scala.collection.concurrent.Map[Char, AtomicInteger] = new java.util.concurrent.ConcurrentHashMap[Char, AtomicInteger]

  val files = Array("a.csv", "b.csv")

  files.foreach {	f =>
    new Thread {
      override def run() {
        Source.fromURL(getClass.getResource("/" + f), "UTF-8").getLines.foreach( l =>
          l.foreach( c => {
            val zero = new AtomicInteger(0)
            val value = frequencies.putIfAbsent(c, zero).getOrElse(zero)
            value.incrementAndGet
          }
          )
        )
        done += 1
        if (done == files.length) println( frequencies('e') )
      }
    }.start()
  }
}

object Ex09 extends App {

  import scala.io.Source

  //val files = Array("a.csv", "b.csv") 25195 17836 43031
  val files = Array("a.csv", "b.csv")

  val frequencies = new scala.collection.mutable.HashMap[Char, Int] with scala.collection.mutable.SynchronizedMap[Char, Int]

  var done = 0

  files.foreach { f =>
    new Thread {
      override def run() {
        Source.fromURL(getClass.getResource("/" + f), "UTF-8").getLines.foreach(l =>
          l.foreach(c => frequencies(c) = frequencies.getOrElse(c, 0) + 1) // => Transaction
        )
        done += 1
        if (done == files.length) println( frequencies('e') )
      }
    }.start()
  }

  //Both files contains 43031 'e' and this script return 42228 because of transaction issue

  //AtomicMap return the good value
  AtomicMap
}

