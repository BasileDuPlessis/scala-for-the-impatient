package com.basile.scala.ch19

import scala.xml.XML
import scala.util.parsing.combinator.lexical.StdLexical
import scala.util.matching.Regex
import scala.util.parsing.combinator.syntactical.StdTokenParsers
import scala.util.parsing.combinator.token.StdTokens
import scala.util.parsing.input.CharArrayReader.EofCh



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

  class XmlLexical extends StdLexical   {

    val startName = """:A-Z_a-z\u00C0-\u00D6\u00D8-\u00F6\u00F8-\u02FF\u0370-\u037d""" +
      """\u037f-\u1fff\u200c-\u200d\u2070-\u218f\u2c00-\u2fef\u3001-\ud7ff""" +
      """\uf900-\ufdcf\ufdf0-\ufffd"""
    val name = """-.0-9\u00B7\u0300-\u036F\u203F-\u2040"""

    val nameReg = ("[" + startName + "][" + startName + name + "]*").r


      def regex(r: Regex): Parser[String] = new Parser[String] {
        def apply(in: Input) =
          r.findPrefixMatchOf( in.source.subSequence(in.offset, in.source.length)) match {
            case Some(matched) => Success(
                in.source.subSequence(in.offset, in.offset + matched.end).toString,
                in.drop(matched.end)
              )
            case None => Failure("string matching regex `" + r + "' expected but " + in.first + " found", in)
          }
      }

      override def token: Parser[Token] =
        (
          ('<' <~ opt('/')) ~ regex(nameReg) ~'>' ^^ {
            case '<' ~ n ~ '>' => processIdent(n)
          }
          | rep(chrExcept('<', '>', EofCh) ) ^^ { case chars => StringLit(chars mkString "") }
          | EofCh ^^^ EOF
          | delim
          | failure("illegal character")
        )

  }

  class XmlParser extends StdTokenParsers {
    type Tokens = StdTokens
    val lexical = new XmlLexical

    def xml: Parser[Any] = ident into {
      name => (stringLit | xml) <~ accept(
        "closing tag", {case lexical.Identifier(`name`) => `name` }
      )
    }

    def parseAll(p: Parser[Any], in: String): ParseResult[Any] = phrase(p)(new lexical.Scanner(in))
  }

  val parser = new XmlParser
  val result = parser.parseAll(parser.xml, "<title>Hello world!</title>")

  if (result.successful) println(result.get) else println(result)

}
