package com.basile.scala.ch19

import scala.util.parsing.combinator.RegexParsers

/**
 * Suppose in Section 19.6 , “ Avoiding Left Recursion ,” on page 276 ,
 * we first parse an expr into a list of ~ with operations and values:
 *
 * def expr: Parser[Int] = term ~ rep(("+" | "-") ~ term) ^^ {...} To evaluate the result,
 * we need to compute (( t 0 ± t 1 ) ± t 2 ) ± . . .
 *
 * Implement this computation as a fold (see Chapter 13 ).
 */
object Ex07 extends App {


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
  val result = parser.parseAll(parser.expr, "3-4-5")

  println(result)

}