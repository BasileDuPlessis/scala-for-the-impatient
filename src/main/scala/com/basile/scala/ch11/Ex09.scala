package com.basile.scala.ch11

/**
 * Define an unapply operation for the RichFile class that extracts the file path, name, and extension.
 * For example, the file /home/cay/readme.txt has path /home/cay , name readme , and extension txt .
 */
object Ex09 extends App {

  object RichFile {

    def unapply(s: String) = {
      val r = """(.+?)/([^/]+)\.([^\.]+)$""".r
      val r(p, n, e) = s
      Some(p, n , e)
    }

  }

  val RichFile(path, name, extension) = "/home/cay/readme.txt"

  assert( path == "/home/cay" )
  assert( name == "readme" )
  assert( extension == "txt" )

}