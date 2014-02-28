package com.basile.scala.ch19

import scala.util.parsing.combinator.RegexParsers
import java.util.{GregorianCalendar, Date}

/**
 * Write a parser that can parse date and time expressions in ISO 8601.
 * Your parser should return a java.util.Date object.
 * "yyyy-MM-dd HH:mm:ss"
 */
object Ex04 extends App {

  class DateParser extends RegexParsers {

    val fd = """\d{4}""".r
    val td = """\d{2}""".r

    def parseDate: Parser[Date] =
      year ~ ("-" ~> other) ~ ("-" ~> other) ~ other ~ (":" ~> other) ~ (":" ~> other) ^^ {
      case y ~ mo ~ d ~ h ~ mi ~ s => (new GregorianCalendar(y, mo - 1, d, h, mi, s)).getTime
    }

    def year: Parser[Int] = fd ^^ {_.toInt}
    def other: Parser[Int] = td ^^ {_.toInt}

  }




}
