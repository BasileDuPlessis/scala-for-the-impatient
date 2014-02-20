package com.basile.scala.ch17

/**
 * Look through the methods of the Iterable[+A] trait.
 * Which methods use the type parameter A ?
 * Why is it in a covariant position in these methods?
 */
object Ex07 extends App {
  /*
  class A
  class B extends A
  class C extends B

  val printC: C => Unit = { c => println("Blah") }
  val printB: B => Unit = { b => println("Blah blah") }
  val printA: A => Unit = { a => println("Blah blah blah") }

  def needsB(f: B => Unit, b: B) = f(b)

  needsB(printB, new B)
  needsB(printA, new B) // sub function of needsB(printB, new B)

  //needsB(printC, new B) => won't compile

  */

  class Person(val name: String)
  class Student(name: String) extends Person(name)

  val paul = new Person("paul")
  val arnaud = new Person("arnaud")
  val josé = new Student("josé")

  val itP = Iterable[Person](paul, arnaud)
  val itS = Iterable[Student](josé)

  val testPerson: (Person) => Boolean = (p: Person) => p.name.contains('l')
  val testStudent: (Student) => Boolean = s => s.name.contains('l')

  println(itS.dropWhile(testPerson))

  //No methods received the type A and returns A

  class Variance[+A] {
    def compile[B >: A](elem: B) = List[B](elem)
    //def notCompile(elem: A):List[A] = List[A](elem)
  }


  //This is because you cannot use a contravariant type parameter as a result type,
  // you can only use it as a parameter type of a function.
  // http://stackoverflow.com/questions/14678518/scala-contravariant-type-a-occurs-in-covariant-position-in-type-a-any-of

}
