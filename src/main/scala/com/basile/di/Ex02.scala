package com.basile.di

/**
 *
 * http://blog.fakod.eu/2012/06/14/scalas-built-in-dependency-injection/
 */
object Ex02 extends App {

  case class Reader[-C, +A](g: C => A) {
    def apply(c: C) = g(c)
    def map[B](f: A => B): Reader[C, B] = Reader(c => f(g(c)))
    def flatMap[D <: C, B](f: A => Reader[D, B]): Reader[D, B] = Reader(c => f(g(c))(c))
  }

  def pure[C, A](a: A) = Reader[C, A](c => a)

  implicit def reader[A, B](f: A => B) = Reader(f)

  val multiply = (_:Int) * 2

  val add = (_:Int) + 45

  val result: Reader[Int, Int] = for {
    a <- multiply
    b <- if (a == 10) for {c <- add} yield c else pure[Int, Int](15)
  } yield a + b

  println(result(5))
  println(result(6))

}
