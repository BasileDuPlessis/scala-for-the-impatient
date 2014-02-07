package com.basile.scala.ch16

import scala.xml.parsing.XhtmlParser
import scala.io.Source

/**
 * Print the names of all images in an XHTML file.
 * That is, print all src attribute values inside img elements.
 */
object Ex05 extends App {
  val xhtml = """<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
  <html>
    <head>
      <title>Xhtml Valid Page</title>
    </head>
    <body>
      <p>
        <img name="image" src="image.jpg" />
      </p>
      <p>
        <img name="imageAlt" src="imageAlt.jpg" alt="diaporama" />
      </p>
    </body>
  </html>"""

  val parser = new XhtmlParser(Source.fromString(xhtml))
  val doc = parser.initialize.document()

  (doc \\ "img").foreach{
    n => {
      println(n \ "@name")
      println(n \ "@src")
    }
  }


}
