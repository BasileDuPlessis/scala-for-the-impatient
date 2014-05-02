package com.basile.scala.ch22

import scala.util.continuations._
import java.io._

/**
 * Make the example in Section 22.7 , “ Turning a Recursive Visit into an Iteration ,” * on page 326 into an iterator.
 * The constructor of the iterator should contain the reset , and the next method should invoke the continuation.
 */
object Ex03 extends App {

  class DirectoryIterator(val dir: String) extends Iterator[File] {

    var cont: (Unit => File) = null
    var currentFile: File = null

    reset[File, File] {
      processDirectory(new File(dir)) // This is what cont() return after loop
    }

    //Recursive method to crawl directory
    private def processDirectory(_dir: File): File @cps[File] = {
      val files = _dir.listFiles

      var i = 0

      while (i < files.length) {
        val f = files(i)
        i += 1
        if (f.isDirectory) {
          processDirectory(f)
        } else {
          shift[Unit, File, File] {
            k: (Unit => File) => cont = k //save continuation
            currentFile //This is what cont() return while looping
          }
          //Starting point after cont() call
          cont = null //fix continuation to null in order to hasNext works
          currentFile = f //save current file
        }
      }
      currentFile //return last saved file when recursion ended
    }


    def next(): File = cont()

    def hasNext(): Boolean = cont != null //While cont() is defined next can be called

  }

  val d = new DirectoryIterator( """C:\dev\html\""" )

  d.drop(100).take(5).foreach(println)

}