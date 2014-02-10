package com.basile.scala.ch17

/**
 * Why don’t we need a lower bound for the replaceFirst method in Section 17.3 ,
 * “ Bounds for Type Variables ,” on page 232 if we want to replace
 * the first component of a Pair[Person] with a Student ?
 */
object Ex04 extends App {

  class Person
  class Student extends Person

  class Pair[T](val first: T, val second: T) {
    def replaceFirst(r: T): Pair[T] = new Pair(r, second)
  }

  val persA = new Person
  val persB = new Person

  val pairA = new Pair(persA, persB)
  val studA = new Student

  val pairB = pairA.replaceFirst(studA)

  //Because Student is already a Person
}
