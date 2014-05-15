package com.basile.scala.ch08

/**
 * Consult your favorite Java or C++ textbook that is sure to have an example of a toy inheritance hierarchy,
 * perhaps involving employees, pets, graphical shapes, or the like.
 *
 * Implement the example in Scala.
 */
object Ex03 extends App {

  class Employee(val name: String, private var _salary: Double, year: Int, month: Int, day: Int) {

    val hireDay = new java.util.GregorianCalendar(year, month - 1, day).getTime()

    def salary = _salary

    def raiseSalary(byPercent: Double) {
      _salary = _salary*(1+byPercent/100)
    }
  }

  class Manager(n: String, _s: Double, y: Int, m: Int, d: Int) extends Employee(n, _s, y, m, d) {

    var bonus:Double = 0

    def getSalary = salary + bonus
  }

  val John = new Manager("John", 50000, 2014, 2, 28)

  John.bonus = 5000

  assert( John.getSalary == 55000 )

}
