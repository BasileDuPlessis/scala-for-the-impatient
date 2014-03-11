package com.basile.scala.ch19

import scala.util.parsing.combinator.syntactical.StandardTokenParsers

/**
 * Extend the preceding exercise into a parser for a programming language that has variable assignments,
 * Boolean expressions, and if / else and while statements.
 */
object Ex09 extends App {

  class Expr {
    def interpret {}
  }
  case class Number(value: Int) extends Expr
  case class Variable(name: String) extends Expr
  case class Operator(op: String, left: Expr, right: Expr) extends Expr
  case class Assignment(left: Variable, right: Expr) extends Expr
  case class Condition(op: String, left: Expr, right: Expr)
  case class Statement(op: String, cond: Condition, expr: List[Expr]) extends Expr

  class LanguageParser extends StandardTokenParsers {

    lexical.reserved += ("while", "if")
    lexical.delimiters += (";", "=", "<", ">", "(", ")", "{", "}", "+", "-", "*", "/")

    def expr: Parser[Expr] = term ~ rep( ("+" | "-") ~ term ) ^^ {
      case t ~ l => l.foldLeft(t)(
        (res, e) => e match {
          case "-" ~ n => Operator("-", res, n)
          case "+" ~ n => Operator("+", res, n)
        }
      )
    }

    def term: Parser[Expr] = factor ~ opt(("/" | "*") ~ term) ^^ {
      case f ~ None => f
      case f ~ Some("/" ~ e) => Operator("/", f, e)
      case f ~ Some("*" ~ e) => Operator("*", f, e)
      case f ~ Some("%" ~ e) => Operator("%", f, e)
    }

    def factor: Parser[Expr] = numericLit ^^ { n => Number(n.toInt) } | ident ^^ {
      n => Variable(n)
    } | "(" ~> expr <~ ")"

    def condition: Parser[Condition] = expr ~ "<" ~ expr ^^ {
      case left ~ op ~ right => Condition(op, left, right)
    }

    def statement: Parser[Expr] = keyword("while") ~ "(" ~> (condition ~ (")" ~ "{" ~> language) <~ "}") ^^ {
      case c ~ l => Statement("while", c, l)
    }

    def assign: Parser[Expr] = (ident <~ "=") ~ expr ^^ {
      case i ~ e => Assignment(Variable(i), e)
    }

    def language: Parser[List[Expr]] = rep((statement | assign) <~ ";") ^^ {
      case l:List[Expr] => l
    }

    def parseAll(p: Parser[List[Expr]], in: String): ParseResult[List[Expr]] = phrase(p)(new lexical.Scanner(in))

  }

  object Interpreter {
    def apply(l: List[Expr]) { l.foreach { e => e.interpret } }
  }

  val parser = new LanguageParser

  val result = parser.parseAll(parser.language, "while(a<10) { out=a; a=a+1; };")

  Interpreter(result.get)

}
