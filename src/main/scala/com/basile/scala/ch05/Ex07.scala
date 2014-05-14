package com.basile.scala.ch05

/**
 * Write a class Person with a primary constructor that accepts a string containing a first name,
 * a space, and a last name, such as new Person("Fred Smith") . Supply read-only properties firstName and lastName .
 *
 * Should the primary constructor parameter be a var , a val , or a plain parameter? Why?
 */
object Ex07 extends App {

  /*
  Primary constructor parameter should be a plain parameter
   */
  class Person(_fullName:String) {
    val (firstName, lastName) =  _fullName.split(" ") match {
      case Array(x:String, y:String, _*) => (x,y)
      case _ => (null,null)
    }
  }

  val FredSmith = new Person("Fred Smith")

  assert( FredSmith.firstName == "Fred" )
  assert( FredSmith.lastName == "Smith" )

}
