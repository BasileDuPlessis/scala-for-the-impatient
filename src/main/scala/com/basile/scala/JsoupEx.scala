package com.basile.scala

import dispatch.classic._
import dispatch.classic.jsoup.JSoupHttp._
import scala.collection.JavaConversions._
import java.security.MessageDigest
import scala.io.Source
import java.io.PrintWriter


object DocLinks {
  val messageDigest = MessageDigest.getInstance("SHA-256")
  val http = new nio.Http

  /**
   * Encrypt String in SH-256
   */
  def encryptToSHA256(s: String) = {
    messageDigest.update(s.getBytes)
    val d = messageDigest.digest().map("%02X" format _).mkString
    messageDigest.reset()
    d
  }

  /**
   * Get HttpExecutor
   */
  def getHttp(u: String, f: (org.jsoup.nodes.Document) => Set[String]) = http(url(u) </> f)

  /**
   * Get function to select Doc Links in Document
   */
  def selectDocLinks(selector:String):(org.jsoup.nodes.Document) => Set[String] =
    d => d.select(selector).map(_.attr("href")).filter(_.startsWith("/")).toSet

  /**
   * Close HttpExecutor
   */
  def close() {
    http.shutdown()
  }

}

object JsoupEx extends App {

  import DocLinks._

  val in = getClass.getResource("/new-articles-2013.txt")
  val out = new PrintWriter("extract-hash-articles-2013.csv", "ISO-8859-1")

  val fctLinks = selectDocLinks("div.main_content a[href]")

  Source.fromURL(in).getLines().map(
    l => l.split(';') match {
      case Array(u, "Article") => (u, getHttp(u, fctLinks))
      case _ => null
    }
  ).foreach(
    l => l match {
      case (a,b) =>  out.write(a + ";" + encryptToSHA256(b().mkString) + "\n")
      case _ => ()
    }
  )

  close()

  out.close()

}