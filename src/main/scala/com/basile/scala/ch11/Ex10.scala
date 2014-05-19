package com.basile.scala.ch11

/**
 * Define an unapplySeq operation for the RichFile class that extracts all path segments.
 * For example, for the file /home/cay/readme.txt , you should produce a sequence of three segments: home , cay , and readme.txt .
 */
object Ex10 extends App {

  object RichFile {
    def unapplySeq(s: String): Option[Seq[String]] = Some(s.split("/").filter(_!=""))
  }

  val RichFile(a @ _*) = "/home/cay/readme.txt"

  println(a)
}
