package com.basile.scala.ch19

import scala.util.parsing.combinator.syntactical.StandardTokenParsers
import scala.collection.mutable.{Map => MutableMap}


/**
 * Add function definitions to the programming language of the preceding exercise.
 */
object Ex10 extends App {

  /*
  Expression should be evaluated as an integer value
   */
  abstract class Expr
  case class Number(value: Int) extends Expr
  case class Operator(op: String, left: Expr, right: Expr) extends Expr
  case class Variable(name: String) extends Expr
  case class FunctionCall(name: String, params: List[Expr]) extends Expr

  /*
  BooleanExpression should be evaluated as boolean
   */
  abstract class BooleanExpr
  case class Condition(op: String, left: Expr, right: Expr) extends BooleanExpr

  /*
  Commands are Assignment, Statement, Function definition and Function return
   */
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

    //Parse function call like fact(2)
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
        case "if" ~ c ~ l ~ None => Statement("if", c, l)
        case "if" ~ c ~ l ~ Some(e) => Statement("if", c, l, e)
      }

    def assignment: Parser[Cmd] = (ident <~ "=") ~ expr ^^ {
      case v ~ e => Assignment(Variable(v), e)
    }

    def function: Parser[Cmd] = "def" ~> ident ~ "(" ~ repsep(ident, ",") ~ ")" ~ "{" ~ cmd ~ "}" ^^ {
      case n ~ "(" ~ a ~ ")" ~ "{" ~ c ~ "}" => Function(n, a, c)
    }

    def functionReturn: Parser[Cmd] = "return" ~> expr ^^ {FunctionReturn(_)}


    def cmd: Parser[List[Cmd]] = rep( statement | (assignment <~ ";") | function | (functionReturn <~ ";") )

    //Define missing parseAll function
    def parseAll(p: Parser[List[Cmd]], in: String): ParseResult[List[Cmd]] = phrase(p)(new lexical.Scanner(in))

  }

  /** Interpret parsing script
   * Each instance define a scope for variable
   */
  class Interpreter {

    //varMap contain variable defined in the scope
    val varMap = MutableMap[String, Int]()
    //fctMap contain function defined in the scope
    val fctMap = MutableMap[String, Function]()

    //Auxiliary constructor receive parameters for function call and functions list
    def this(defaultVarMap: Map[String, Int], defaultFctMap: Map[String, Function]) {
      this()
      varMap ++= defaultVarMap
      fctMap ++= defaultFctMap
    }

    /** handle return statement
     *
     * @param value value to return to caller
     */
    class ReturnException(val value: Int) extends Exception

    //Evaluate an Expression
    def valueOfExpr(expr: Expr): Int = {
      expr match {
        case Number(v) => v
        case Operator("+", l, r) => valueOfExpr(l) + valueOfExpr(r)
        case Operator("-", l, r) => valueOfExpr(l) - valueOfExpr(r)
        case Operator("*", l, r) => valueOfExpr(l) * valueOfExpr(r)
        case Operator("/", l, r) => valueOfExpr(l) / valueOfExpr(r)
        case Variable(n) => varMap.getOrElse(n, 0)
        case FunctionCall(n, p) => fctMap.get(n) match {
          //undefined function return 0
          case None => 0
          case Some(f) =>
            try {
              /*
              Function are called in a new instance of interpreter (new scope)
              Parameters and defined function are sent to this new instance
               */
              (new Interpreter(Map(f.params.zip(p.map(valueOfExpr)).toArray: _*), fctMap.toMap)).execute(f.cmd)
              //function without return statement return 0
              0
            } catch {
              case e:ReturnException => e.value
            }
        }
      }
    }

    //Evaluate a Boolean Expression
    def valueOfCond(cond: BooleanExpr): Boolean = {
      cond match {
        case Condition("<", l, r) => valueOfExpr(l) < valueOfExpr(r)
        case Condition(">", l, r) => valueOfExpr(l) > valueOfExpr(r)
        case Condition(">=", l, r) => valueOfExpr(l) >= valueOfExpr(r)
        case Condition("<=", l, r) => valueOfExpr(l) <= valueOfExpr(r)
      }
    }

    //Execute a list of commands
    def execute(cmd: List[Cmd]) {
      cmd.foreach {
        c => c match {
          case Statement(op, cond, cmd1, cmd2) => op match {
            case "while" => while (valueOfCond(cond)) execute(cmd1)
            case "if" => if (valueOfCond(cond)) execute(cmd1) else execute(cmd2)
          }
          case Assignment(v, e) => v match {
            case Variable("out") => println(valueOfExpr(e))
            case Variable(n) => varMap.update(n, valueOfExpr(e))
          }
          case f: Function => fctMap.update(f.name, f)
          case FunctionReturn(e) => throw new ReturnException(valueOfExpr(e))
        }
      }
    }

  }


  val parser = new LanguageParser

  //Return factorial for number from 1 to 9
  val result = parser.parseAll(parser.cmd,
    """
    def fact(n) {
      if (n>0) {
        return n*fact(n-1);
      } else {
        return 1;
      }
    }
    n=1;
    while(n<10) {
      out=fact(n);
      n=n+1;
    }
    """
  )

  val myInterpreter = new Interpreter()

  /*
  Should print :
  1
  2
  6
  24
  120
  720
  5040
  40320
  362880
   */
  if (result.successful) myInterpreter.execute(result.get)


}
