package com.basile.scala.ch19

import scala.util.parsing.combinator.syntactical.StandardTokenParsers

/**
 * Add function definitions to the programming language of the preceding exercise.
 */
object Ex10 extends App {

  abstract class Expr
  case class Number(value: Int) extends Expr
  case class Operator(op: String, left: Expr, right: Expr) extends Expr
  case class Variable(name: String) extends Expr
  case class FunctionCall(name: String, params: List[Expr]) extends Expr

  abstract class BooleanExpr
  case class Condition(op: String, left: Expr, right: Expr) extends BooleanExpr

  abstract class Cmd
  case class Assignment(left: Variable, right: Expr) extends Cmd
  case class Statement(op: String, cond: BooleanExpr, cmd1: List[Cmd], cmd2: List[Cmd] = Nil) extends Cmd
  case class Function(name: String, params: List[String], cmd: List[Cmd]) extends Cmd
  case class FunctionReturn(expr: Expr) extends Cmd


  class LanguageParser extends StandardTokenParsers {

    lexical.reserved += ("while", "if", "else", "def", "return")
    lexical.delimiters += (",", ";", "=", "<", ">", "(", ")", "{", "}", "+", "-", "*", "/")

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

    def factor: Parser[Expr] =
      numericLit ^^ { n => Number(n.toInt) }  | functionCall | ident ^^ {Variable(_)} | "(" ~> expr <~ ")"

    def functionCall: Parser[Expr] = ident ~ "(" ~ repsep(expr, ",") ~ ")" ^^ {
      case n ~ "(" ~ l ~ ")" => FunctionCall(n, l)
    }

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
      case v ~ e => Assignment(Variable(v), e)
    }

    def function: Parser[Cmd] = "def" ~> ident ~ "(" ~ repsep(ident, ",") ~ ")" ~ "{" ~ cmdInFunction ~ "}" ^^ {
      case n ~ "(" ~ a ~ ")" ~ "{" ~ c ~ "}" => Function(n, a, c)
    }

    def functionReturn: Parser[Cmd] = "return" ~> expr ^^ {FunctionReturn(_)}


    def cmd: Parser[List[Cmd]] = rep( (statement | assignment | function) <~ ";" )

    def cmdInFunction: Parser[List[Cmd]] = rep( (statement | assignment | functionReturn) <~ ";" )

    def parseAll(p: Parser[List[Cmd]], in: String): ParseResult[List[Cmd]] = phrase(p)(new lexical.Scanner(in))

  }


  class Interpreter(val defaultVarMap: Map[String, Int] = Map[String, Int]()) {

    import scala.collection.mutable.{Map => MutableMap}

    val varMap = MutableMap[String, Int]() ++ defaultVarMap
    val fctMap = MutableMap[String, Function]()

    def valueOfExpr(expr: Expr): Int = {
      expr match {
        case Number(v) => v
        case Operator("+", l, r) => valueOfExpr(l) + valueOfExpr(r)
        case Operator("-", l, r) => valueOfExpr(l) - valueOfExpr(r)
        case Operator("*", l, r) => valueOfExpr(l) * valueOfExpr(r)
        case Operator("/", l, r) => valueOfExpr(l) / valueOfExpr(r)
        case Variable(n) => varMap.getOrElse(n, 0)
        case FunctionCall(n, p) => fctMap.get(n) match {
          case None => 0
          case Some(f) =>
            val m = Map(f.params.zip(p.map(valueOfExpr)).toArray:_*)
            (new Interpreter(m)).execute(f.cmd) match {
              case None => 0
              case Some(i) => i
            }
        }
      }
    }

    def valueOfCond(cond: BooleanExpr): Boolean = {
      cond match {
        case Condition("<", l, r) => valueOfExpr(l) < valueOfExpr(r)
      }
    }

    def execute(cmd: List[Cmd]): Option[Int] = {
      var out: Option[Int] = None
      cmd.foreach{
        c => c match {
          case Statement(op, cond, cmd1, cmd2) => op match {
            case "while" => while(valueOfCond(cond)) execute(cmd1)
            case "if" => if(valueOfCond(cond)) execute(cmd1) else execute(cmd2)
          }
          case Assignment(v, e) => v match {
            case Variable("out") => println(valueOfExpr(e))
            case Variable(n) => varMap.update(n, valueOfExpr(e))

          }
          case f:Function => fctMap.update(f.name, f)
          case FunctionReturn(e) => out = Some(valueOfExpr(e))
        }
      }
      out
    }
  }


  val parser = new LanguageParser

  val result = parser.parseAll(parser.cmd, "def multiply(a,b) {return a*b;}; out=multiply(3,4);")

  val myInterpreter = new Interpreter

  myInterpreter.execute(result.get)


}
