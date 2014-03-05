package com.basile.scala.ch19

import scala.util.parsing.combinator.RegexParsers
import scala.{xml => sxml}


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
  case class XmlListHolder(list: List[sxml.Node])

  class XmlParser extends RegexParsers {

    val startName = """:A-Z_a-z\u00C0-\u00D6\u00D8-\u00F6\u00F8-\u02FF\u0370-\u037d""" +
      """\u037f-\u1fff\u200c-\u200d\u2070-\u218f\u2c00-\u2fef\u3001-\ud7ff""" +
      """\uf900-\ufdcf\ufdf0-\ufffd"""
    val name = """-.0-9\u00B7\u0300-\u036F\u203F-\u2040"""

    //match tag name
    val nameReg = ("[" + startName + "][" + startName + name + "]*").r

    //match attribute value surrounded by simple or double quote
    val valueRegDoubleQuote = """[^"]*""".r
    val valueRegSimpleQuote = """[^']*""".r

    //match single or double quote
    val quoteReg = """["']""".r

    //match text node
    val textReg = """[^><]+""".r

    //transform list of attributes to nested attributes
    def attrFromList(l: List[sxml.Attribute]): sxml.Attribute = {
      l match {
        case Nil => null
        case h :: Nil => h
        case h :: t => t.foldLeft(h)((r, a) => a.copy(r))
      }
    }

    val xml = text | node | emptyNode

    //match text node
    def text: Parser[sxml.Node] = textReg ^^ {
      case s: String => new sxml.Text(s)
    }

    //match attribute
    def attr: Parser[sxml.Attribute] = (nameReg <~ '=') ~ (quoteReg into {
      case "\"" => valueRegDoubleQuote <~ "\""
      case "'" => valueRegSimpleQuote <~ "'"
    }) ^^ {
      case n ~ v => sxml.Attribute(null, n, v, sxml.Null)
    }

    //empty node <br />
    def emptyNode: Parser[sxml.Node] = '<'  ~> (nameReg ~ opt(rep(attr)) <~ opt(' ') ~ '/' ~'>') ^^ {
      case s ~ attr => {
        val elem = sxml.Elem(null, s, sxml.Null, sxml.TopScope, true)
        attr match {
          case None => elem
          case Some(l) => elem % attrFromList(l)
        }
      }
    }

    //match start tag with attributes and return a Tuple2 with tag name and nested attributes
    def startTag: Parser[(String, sxml.Attribute)] = '<' ~> (nameReg ~ opt(rep(attr)) <~ '>') ^^ {
      case s ~ None => (s, null)
      case s ~ Some(l:List[_]) => (s, attrFromList(l))
    }

    //match an end tag with a specific name
    def endTag(name: String): Parser[String] = '<' ~ '/' ~> (nameReg <~ '>') ^^ {
      case s: String if s == `name` => s
    }

    //match from start tag to similar end tag containing nested node
    def node: Parser[sxml.Node] = startTag into {
      t => (rep(xml) ^^ {r => new XmlListHolder(r)}) <~ endTag(t._1) ^^ {
        case x: XmlListHolder =>
          val res = sxml.Elem(
            null, t._1, sxml.Null, sxml.TopScope, true, x.list:_*
          )
          t._2 match {
            case null => res
            case _:sxml.Attribute => res % t._2
          }
      }
    }

  }

  val parser = new XmlParser
  val result = parser.parseAll(parser.xml,
    """<p class="title" id="TheTitle'" style='margin-left:"20px"'>""" +
    """Hello <strong>wor<i>l</i></strong><em>d</em><br id="TheBr" />!</p>"""
  )

  println(result)
}
