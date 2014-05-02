package com.basile.scala.ch22

import scala.util.continuations._

/**
 * Improve the example of Section 22.1 , “ Capturing and Invoking a Continuation ,”
 * on page 320 so that the continuation function passes the name of the next file to try as a parameter.
 */
object Ex02 extends App {

  var cont: (String => Unit) = null
  var filename = "myfile.txt"

  var contents = ""

  reset {
    while (contents == "") {
      try {
        contents = scala.io.Source.fromFile(filename, "UTF-8").mkString
      } catch {
        case _: Throwable => ()
      }
      filename = shift {
        k: (String => Unit) => cont = k
      }
    }
  }

  cont("""C:\dev\basile\build.sbt""")
  println(contents)

}
