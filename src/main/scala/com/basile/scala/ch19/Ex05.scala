package com.basile.scala.ch19

import scala.util.parsing.combinator.RegexParsers


/**
 * Write a parser that parses a subset of XML.
 * Handle tags of the form <ident> ... </ident> or <ident/> .
 * Tags can be nested.
 * Handle attributes inside tags.
 * Attribute values can be delimited by single or double quotes.
 * You don’t need to deal with character data (that is, text inside tags or CDATA sections).
 * Your parser should return a Scala XML Elem value.
 *
 * The challenge is to reject mismatched tags. Hint: into , accept .
 */
object Ex05 extends App {


  //In order to ype match List[Node]
  case class XmlListHolder(list: List[scala.xml.Node])

  class XmlParser extends RegexParsers {

    val startName = """:A-Z_a-z\u00C0-\u00D6\u00D8-\u00F6\u00F8-\u02FF\u0370-\u037d""" +
      """\u037f-\u1fff\u200c-\u200d\u2070-\u218f\u2c00-\u2fef\u3001-\ud7ff""" +
      """\uf900-\ufdcf\ufdf0-\ufffd"""
    val name = """-.0-9\u00B7\u0300-\u036F\u203F-\u2040"""

    val nameReg = ("[" + startName + "][" + startName + name + "]*").r

    val textReg = """[^><]+""".r

    val xml = text | node | minimizeNode

    def text: Parser[scala.xml.Node] = textReg ^^ {
      case s: String => new scala.xml.Text(s)
    }

    def minimizeNode: Parser[scala.xml.Node] = '<'  ~> (nameReg <~ opt(' ') ~ '/' ~'>') ^^ {
      case s: String => new scala.xml.Elem(
        null, s, scala.xml.Null, scala.xml.TopScope, true
      )
    }

    def startTag: Parser[String] = '<' ~> (nameReg <~ '>') ^^ {
      case s: String => s
    }

    def endTag(name: String): Parser[String] = '<' ~ '/' ~> (nameReg <~ '>') ^^ {
      case s: String if s == `name` => s
    }

    def node: Parser[scala.xml.Node] = startTag into {
      name => (rep(xml) ^^ {r => new XmlListHolder(r)}) <~ endTag(`name`) ^^ {
        case x: XmlListHolder =>
          new scala.xml.Elem(
            null, `name`, scala.xml.Null, scala.xml.TopScope, true, x.list:_*
          )
      }
    }

  }

  val parser = new XmlParser
  val result = parser.parseAll(parser.xml, "<p>Hello <strong>wor<i>l</i></strong><em>d</em><br />!</p>")

  if (result.successful) result.get(0).child.foreach(println)
}
