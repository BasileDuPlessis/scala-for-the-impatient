package com.basile.scala.ch19

import scala.util.parsing.combinator.RegexParsers

/**
 * Add / and % operations to the arithmetic expression evaluator.
 */
object Ex01 extends App {

  class ExprParser extends RegexParsers {
    val number = "[0-9.]+".r

    def expr: Parser[Double] = opt("+" | "-") ~term ~ rep(("+" | "-") ~term^^{
      case "+" ~ t => t
      case "-" ~ t => -t}) ^^ {
      case (None|Some("+")) ~ t ~ r => t + r.sum
      case Some("-") ~ t ~ r => -t + r.sum
    }

    def term: Parser[Double] = factor ~ opt(("/" | "*" | "%") ~ term) ^^ {
      case f ~ None => f
      case f ~ Some("/" ~ e) => f / e
      case f ~ Some("*" ~ e) => f * e
      case f ~ Some("%" ~ e) => f % e
    }

    def factor: Parser[Double] = number ^^ {_.toDouble} | "(" ~> expr <~ ")"
  }


}
