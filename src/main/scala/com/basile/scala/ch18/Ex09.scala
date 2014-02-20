package com.basile.scala.ch18

/**
 * Consider this class that models a physical dimension:
 * abstract class Dim[T](val value: Double, val name: String) {
 *
 *  protected def create(v: Double): T def +(other: Dim[T]) =
 *    create(value + other.value)
 *
 *  override def toString() = value + " " + name
 *
 * }
 *
 * Here is a concrete subclass:
 *
 * class Seconds(v: Double) extends Dim[Seconds](v, "s") {
 *  override def create(v: Double) = new Seconds(v)
 * }
 *
 * But now a knucklehead could define
 *
 * class Meters(v: Double) extends Dim[Seconds](v, "m") {
 *  override def create(v: Double) = new Seconds(v)
 * }
 *
 * allowing meters and seconds to be added.
 *
 * Use a self type to prevent that.
 */
object Ex09 extends App {

  abstract class Dim[T](val value: Double, val name: String) {
    this: T =>
      protected def create(v: Double): T

      def +(other: Dim[T]) = create(value + other.value)

      override def toString() = value + " " + name

  }

  class Seconds(v: Double) extends Dim[Seconds](v, "s") {
    override def create(v: Double) = new Seconds(v)
  }

  class Meters(v: Double) extends Dim[Meters](v, "m") {
    override def create(v: Double) = new Meters(v)
  }

  val sec1 = new Seconds(4.56)
  val sec2 = new Seconds(14.12)

  println(sec1 + sec2)

  val met1 = new Meters(2.87)
  val met2 = new Meters(18.42)

  println(met1 + met2)


}
