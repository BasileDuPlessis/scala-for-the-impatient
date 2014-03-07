package com.basile.scala.ch19

import scala.util.parsing.combinator.RegexParsers
/**
 * Extend the preceding exercise into a parser for a programming language that has variable assignments,
 * Boolean expressions, and if / else and while statements.
 */
object Ex09 extends App {

  class Expr
  case class Number(value: Int) extends Expr
  case class Variable(name: String, value: Int = 0) extends Expr
  case class Operator(op: String, left: Expr, right: Expr) extends Expr
  case class Condition(op: String, left: Expr, right: Expr) extends Expr

  class ExprParser extends RegexParsers {

    //A Map to store variables values
    val ValueMap = scala.collection.mutable.Map[String, Int]()

    val number = "[0-9]+".r

    //match variable name
    val name = "[a-z]+".r

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

    //a factor must be a number, variable or expression between ()
    def factor: Parser[Int] =
      number ^^ { _.toInt } | name ^^ { ValueMap.getOrElse(_, 0) } | "(" ~> expr <~ ")"

    //assign an expression to a variable
    def assign = (name <~ "=") ~ expr ^^ {
      case "out" ~ e => println(e)
      case n ~ e => ValueMap.update(n, e)
    }

    def condition: Parser[Boolean] = expr ~ (">" | "<") ~ expr ^^ {
      case a ~ "<" ~ b => a < b
      case a ~ ">" ~ b => a > b
    }

    def statement = ("while" <~ "(") ~ condition ~ (")" ~ "{" ~> language <~ "}") ^^ {
      case "while" ~ c ~ l => while(c())
    }

    //parse semicolon separated language
    def language = rep((assign <~ ';')

  }

  val parser = new ExprParser

  //should print 20 then 5
  val result = parser.parseAll(parser.language, "while(a<10) { out=a; a=a+1; };")

}
