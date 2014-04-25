package com.basile.scala.ch21

/**
 * Use the implicitly command in the REPL to summon the implicit objects described in Section 21.5 ,
 * “ Implicit Parameters ,” on page 309 and Section 21.6 , “ Implicit Conversions with Implicit Parameters ,”
 * on page 310 . What objects do you get?
 */
object Ex08 extends App {

  //21.5
  case class Delimiters(left: String, right: String)

  def quote(what: String) = implicitly[Delimiters].left + what + implicitly[Delimiters].right

  implicit val quoteDelimiters = Delimiters("«", "»")

  implicitly[Delimiters] //returns Delimiters(«,»)


  //21.6

  def smaller[T <% Ordered[T]](a: T, b: T): T = {
    if (a < b) a else b
  }

  smaller("Hello", "World")

  //Can't get anything because there is no implicit value defined




}
