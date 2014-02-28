package com.basile.scala.ch19

import scala.util.parsing.combinator.RegexParsers

/**
 * Add a ^ operator to the arithmetic expression evaluator.
 * As in mathematics, ^ should have a higher precedence than multiplication,
 * and it should be right-associative.
 * That is, 4^2^3 should be 4^(2^3) , or 65536.
 */
object Ex02 extends App {

  class ExprParser extends RegexParsers {
    val number = "[0-9.]+".r

    def expr: Parser[Double] = term ~ opt(("+" | "-") ~ expr) ^^ {
      case t ~ None => t
      case t ~ Some("+" ~ e) => t + e
      case t ~ Some("-" ~ e) => t - e
    }

    def term: Parser[Double] = pow ~ opt(("/" | "*" | "%") ~ term) ^^ {
      case f ~ None => f
      case f ~ Some("/" ~ e) => f / e
      case f ~ Some("*" ~ e) => f * e
      case f ~ Some("%" ~ e) => f % e
    }

    def pow: Parser[Double] = factor ~ opt("^" ~ pow) ^^ {
      case f ~ None => f
      case f ~ Some("^" ~ p) => Math.pow(f, p)
    }

    def factor: Parser[Double] = number ^^ {_.toDouble} | "(" ~> expr <~ ")"
  }


}
