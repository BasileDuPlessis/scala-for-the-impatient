package com.basile.scala.ch19

import scala.util.parsing.combinator.RegexParsers

/**
 * Add variables and assignment to the calculator program.
 * Variables are created when they are first used.
 * Uninitialized variables are zero.
 *
 * To print a value, assign it to the special variable out .
 */
object Ex08 extends App {

  class ExprParser extends RegexParsers {

    val number = "[0-9]+".r

    def expr: Parser[Int] = term ~ rep( ("+" | "-") ~ term ) ^^ {
      case t ~ l => l.foldLeft(t)(
        (res, e) => e match {
          case "-" ~ n => res - n
          case "+" ~ n => res + n
        }
      )
    }

    def term: Parser[Int] = factor ~ opt(("/" | "*" | "%") ~ term) ^^ {
      case f ~ None => f
      case f ~ Some("/" ~ e) => f / e
      case f ~ Some("*" ~ e) => f * e
      case f ~ Some("%" ~ e) => f % e
    }

    def factor: Parser[Int] = number ^^ { _.toInt } | "(" ~> expr <~ ")"

  }

  val parser = new ExprParser
  val result = parser.parseAll(parser.expr, "a=2 b=a+2 out=b")

  println(result)

}
