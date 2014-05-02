package com.basile.scala.ch22

import scala.util.continuations._

/**
 * In the example of Section 22.1 , “ Capturing and Invoking a Continuation ,” on page 320 ,
 * suppose there is no file myfile.txt.
 * Now set filename to another nonexistent file and call cont. What happens?
 *
 * Set filename to a file that exists and call cont again. What happens?
 *
 * Call cont one more time. What happens?
 *
 * First, think through the control flow, then run a program to verify.
 */
object Ex01 extends App {


  var cont: (Unit => Unit) = null
  var filename = "myfile.txt"

  var contents = ""

  reset {
    while (contents == "") {
      try {
        contents = scala.io.Source.fromFile(filename, "UTF-8").mkString
      } catch {
        case _: Throwable => ()
      }
      shift {
        k: (Unit => Unit) => cont = k
      }
    }
  }

  /*
  Now set filename to another nonexistent file and call cont. What happens?
  => Go back to reset and try again, throw exception and capture continuation
   */
  filename = "myfile2.txt"
  cont()

  /*
  Set filename to a file that exists and call cont again. What happens?
  => Go back to reset and try again, read content and capture continuation
   */
  filename = """C:\dev\basile\build.sbt"""
  cont()
  println(contents)
  /*
  Call cont one more time. What happens?
  => Go back to reset and try again, throw exception and capture continuation
   */
  cont()



}



















