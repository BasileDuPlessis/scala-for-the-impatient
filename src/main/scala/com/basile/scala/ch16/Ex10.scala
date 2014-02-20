package com.basile.scala.ch16

import scala.xml.transform.{RuleTransformer, RewriteRule}
import scala.xml._
import scala.xml.parsing.XhtmlParser
import scala.io.Source
import scala.language.postfixOps

/**
 * Write a function that reads an XHTML document, carries out the transformation of the preceding exercise,
 * and saves the result. Be sure to preserve the DTD and any CDATA sections.
 */
object Ex10 extends App {

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
      <p><![CDATA[Cdata <section>]]></p>
    </body>
  </html>"""

  def imgTodo(doc: Document): Document = {
    val s = new RuleTransformer(
      new RewriteRule {
        override def transform(n: Node) = n match {
          case img @ <img/> if img \ "@alt" isEmpty =>
            img.asInstanceOf[Elem] % Attribute(null, "alt", "TODO", Null)
          case _ => n
        }
      }
    ).transform(doc)(0)
    val transformed = new Document()
    transformed.dtd = doc.dtd
    transformed.docElem = s
    transformed

  }

  val parser = new XhtmlParser(Source.fromString(xhtml))

  val doc = imgTodo(parser.initialize.document())

  println(doc)

}
