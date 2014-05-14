package com.basile.scala.ch05

/**
 * Consider the class
 * class Employee(val name: String, var salary: Double) {
 * def this() { this("John Q. Public", 0.0) }
 * }
 *
 * Rewrite it to use explicit fields and a default primary constructor. Which form do you prefer? Why?
 */
object Ex10 extends App {

  class Employee(val name: String, var salary: Double) {
    def this() { this("John Q. Public", 0.0) }
  }

  //A more syntactic form
  class EmployeeRewrite(val name: String = "John Q. Public", var salary: Double = 0.0) {}

}
