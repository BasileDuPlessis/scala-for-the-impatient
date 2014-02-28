package com.basile.scala.ch19

import scala.util.parsing.combinator.RegexParsers

/**
 * Write a parser that parses a list of integers (such as (1, 23, -79) ) into a List[Int] .
 */
object Ex03 extends App {

  class ListParser extends RegexParsers {

    val number = "-?[0-9]+".r

    val elem: Parser[List[Int]] = "(" ~> rep(number <~ ("," | ")")) ^^ {
      case l: List[String] => l.map(_.toInt)
    }

  }


}
