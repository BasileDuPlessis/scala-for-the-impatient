package com.basile.scala.ch19

import scala.util.parsing.combinator.RegexParsers

/**
 * Assume that the parser in Section 19.5 , “ Generating Parse Trees ,” on page 275 is completed with
 * class ExprParser extends RegexParsers {
 *  def expr: Parser[Expr] = (term ~ opt(("+" | "-") ~ expr)) ^^ {
 *    case a ~ None => a
 *    case a ~ Some(op ~ b) => Operator(op, a, b)
 *   }
 *   ...
 *  }
 *  Unfortunately, this parser computes an incorrect expression tree—operators with the same precedence
 *  are evaluated right-to-left.
 *
 *  Modify the parser so that the expression tree is correct.
 *
 *  For example, 3-4-5 should yield an Operator("-", Operator("-", 3, 4), 5) .
 */
object Ex06 extends App {

  class Expr
  case class Number(value: Int) extends Expr
  case class Operator(op: String, left: Expr, right: Expr) extends Expr

  class ExprParser extends RegexParsers {

    val number = "[0-9]+".r

    def expr: Parser[Expr] = term ~ rep( ("+" | "-") ~ term ) ^^ {
      case t ~ l => l.foldLeft(t)(
        (res, e) => e match {
          case "-" ~ n => Operator("-", res, n)
          case "+" ~ n => Operator("+", res, n)
        }
      )
    }

    def term: Parser[Expr] = factor ~ opt(("/" | "*" | "%") ~ term) ^^ {
      case f ~ None => f
      case f ~ Some("/" ~ e) => Operator("/", f, e)
      case f ~ Some("*" ~ e) => Operator("*", f, e)
      case f ~ Some("%" ~ e) => Operator("%", f, e)
    }

    def factor: Parser[Expr] = number ^^ { n => Number(n.toInt) } | "(" ~> expr <~ ")"

  }

  val parser = new ExprParser
  val result = parser.parseAll(parser.expr, "3-4-5")

  println(result)

}
