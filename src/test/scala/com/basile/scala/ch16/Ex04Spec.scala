package com.basile.scala.ch16

import org.scalatest.FlatSpec
import scala.xml.parsing.XhtmlParser
import scala.io.Source

class Ex04Spec extends FlatSpec {

  val xhtml = """<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
  <html>
    <head>
      <title>Xhtml Valid Page</title>
    </head>
    <body>
      <p>
        <img src="image.jpg" />
      </p>
      <p>
        <img src="imageAlt.jpg" alt="diaporama" />
      </p>
    </body>
  </html>"""

  val parser = new XhtmlParser(Source.fromString(xhtml))
  val doc = parser.initialize.document()

  "getImgWithoutAlt" should "Return Images without alt attribute" in {
    val img = Ex04.getImgWithoutAlt(doc)
    assert(img(0) == <img src="image.jpg" />)
  }

}
