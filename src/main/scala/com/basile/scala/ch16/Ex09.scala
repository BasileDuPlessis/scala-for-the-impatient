package com.basile.scala.ch16

import scala.xml.parsing.XhtmlParser
import scala.io.Source
import scala.xml.transform.{RuleTransformer, RewriteRule}
import scala.xml.{Elem, Null, Attribute, Node}

/**
 * Transform an XHTML document by adding an alt="TODO" attribute to all img
 * elements without an alt attribute, preserving everything else.
 */
object Ex09 extends App {

  val xhtml = """<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
  <html>
    <head>
      <title>Xhtml Valid Page</title>
    </head>
    <body>
      <p>
        <img src="http://www.imageA.fr" />
      </p>
      <p>
        <img src="http://www.imageB.fr" alt="imageB" />
      </p>
    </body>
  </html>"""

  val rule = new RewriteRule {
    override def transform(n: Node) = n match {
      case img @ <img/> if img \ "@alt" isEmpty =>
        img.asInstanceOf[Elem] % Attribute(null, "alt", "TODO", Null)
      case _ => n
    }
  }

  val parser = new XhtmlParser(Source.fromString(xhtml))
  val doc = parser.initialize.document()

  val transformed = new RuleTransformer(rule).transform(doc)

  println(transformed)

}
