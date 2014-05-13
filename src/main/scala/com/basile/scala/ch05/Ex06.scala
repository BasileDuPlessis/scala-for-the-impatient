package com.basile.scala.ch05

/**
 * In the Person class of Section 5.1 , “Simple Classes and Parameterless Methods,” on page 49 ,
 * provide a primary constructor that turns negative ages to 0.
 */
object Ex06 extends App {

  class Person(private var _age:Int) {

    if (_age <0) _age=0

    def age = _age
    def age_=(newValue: Int) {
      if (newValue > _age) _age = newValue;
    }
  }

  val Paul = new Person(-2)

  assert(Paul.age == 0)

}
