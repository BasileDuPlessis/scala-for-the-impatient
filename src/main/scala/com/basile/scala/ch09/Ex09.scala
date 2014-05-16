package com.basile.scala.ch09

import java.io.File

/**
 * Write a Scala program that counts how many files with .class extension are in a given directory and its
 * subdirectories.
 */
object Ex09 extends App {

  def countClass(dir: File): Int = {
    val dirList = dir.listFiles
    dirList.filter(_.toString.endsWith(".class")).length + dirList.filter(_.isDirectory).map(countClass(_)).sum
  }

  val dir = new File("""C:\dev""")

  println(countClass(dir))
}
