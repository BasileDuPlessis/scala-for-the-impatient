package com.basile.scala.ch19

import scala.util.parsing.combinator.syntactical.StandardTokenParsers

/**
 * Extend the preceding exercise into a parser for a programming language that has variable assignments,
 * Boolean expressions, and if / else and while statements.
 */
object Ex09 extends App {

  /*
  Expression should provide a value
   */
  abstract class Expr {
    def value: Int
  }

  case class Variable(name: String) extends Expr {
    var value: Int = 0
  }
  /*
  Object to use in pattern matching in order to set and get instance of Variable
   */
  object NameOfVariable {
    val varMap = scala.collection.mutable.Map[String, Variable]()
    def unapply(s: String): Option[Variable] = Some(varMap.getOrElseUpdate(s, new Variable(s)))
  }

  case class Number(value: Int) extends Expr
  case class Operator(op: String, left: Expr, right: Expr) extends Expr {
    def value: Int = op match {
      case "+" => left.value + right.value
      case "-" => left.value - right.value
      case "*" => left.value * right.value
      case "/" => left.value / right.value
    }
  }

  /*
  Boolean Expressions should provide a boolean to get used in Statement
   */
  abstract class BooleanExpr {
    def value: Boolean
  }
  case class Condition(op: String, left: Expr, right: Expr) extends BooleanExpr {
    def value: Boolean = op match {
      case "<" => left.value < right.value
      case ">" => left.value > right.value
      case ">=" => left.value >= right.value
      case "<=" => left.value <= right.value
    }
  }

  /*
  Command should be interpreted
   */
  abstract class Cmd {
    def interpret: Unit
  }
  case class Assignment(left: Variable, right: Expr) extends Cmd {
    def interpret {
      left match {
        case Variable("out") => println(right.value)
        case _ => left.value = right.value
      }
    }
  }
  case class Statement(op: String, cond: BooleanExpr, expr1: List[Cmd], expr2: List[Cmd] = Nil) extends Cmd {
    def interpret {
      op match {
        case "while" => while(cond.value) expr1.foreach(_.interpret)
        case "if" => if(cond.value) expr1.foreach(_.interpret) else expr2.foreach(_.interpret)
      }
    }
  }

  class LanguageParser extends StandardTokenParsers {

    lexical.reserved += ("while", "if", "else")
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
      case NameOfVariable(v) => v
    } | "(" ~> expr <~ ")"

    def condition: Parser[BooleanExpr] = expr ~ ("<" | ">") ~ expr ^^ {
      case left ~ op ~ right => Condition(op, left, right)
    }

    def statement: Parser[Cmd] =
      ((("while" | "if") <~ "(") ~ (condition <~ ")") ~ ("{" ~> cmd <~ "}")
        ~ opt( "else" ~> "{" ~> cmd <~ "}" )) ^^ {
          case "while" ~ c ~ l ~ None => Statement("while", c, l)
          case "if" ~ c ~ l ~ Some(e) => Statement("if", c, l, e)
        }

    def assignment: Parser[Cmd] = (ident <~ "=") ~ expr ^^ {
      case NameOfVariable(v) ~ e => Assignment(v, e)
    }

    def cmd: Parser[List[Cmd]] = rep((statement | assignment) <~ ";")

    def parseAll(p: Parser[List[Cmd]], in: String): ParseResult[List[Cmd]] = phrase(p)(new lexical.Scanner(in))

  }

  val parser = new LanguageParser

  val result = parser.parseAll(parser.cmd, "while(a<100) { if (a>9) {a=a+10;} else {a=a+1;}; out=a; };")

  //should print 1 2 3 4 5 6 7 8 9 10 20 30 40 50 60 70 80 90 100
  result.get.foreach(_.interpret)

}
