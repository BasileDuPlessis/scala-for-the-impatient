package com.basile.scala.ch16

import scala.xml.parsing.XhtmlParser
import scala.io.Source

/**
 * Read an XHTML file and print a table of all hyperlinks in the file, together with their URLs.
 * That is, print the child text and the href attribute of each a element.
 */
object Ex06 extends App {
  val xhtml = """<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
  <html>
    <head>
      <title>Xhtml Valid Page</title>
    </head>
    <body>
      <p>
        <a href="http://www.siteA.fr">Le site des A</a>
      </p>
      <p>
        <a href="http://www.siteB.fr">Le site des B</a>
      </p>
    </body>
  </html>"""

  val parser = new XhtmlParser(Source.fromString(xhtml))
  val doc = parser.initialize.document()

  var tr = <tr></tr>

  (doc \\ "a").foreach{
    n => {
      tr = tr.copy(child = tr.child ++ <td>{n}</td>)
      println(n \\ "@href")
    }
  }

  println(<table>{tr}</table>)


}
