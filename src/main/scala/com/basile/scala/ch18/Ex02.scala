package com.basile.scala.ch18

/**
 * Provide a fluent interface for the Bug class of the preceding exercise, so that one can write
 * move 4 and show and then move 6 and show turn around move 5 and show
 */
object Ex02 extends App {

  object Show
  object Then
  object Around

  val show = Show
  val then = Then
  val around = Around

  trait FluentBug {
    this: Ex01.Bug =>
      def and(obj: Show.type): this.type = { this.show() }
      def and(obj: Then.type): this.type = { this }
      def turn(obj: Around.type): this.type = { this.turn() }
  }

  val bugsy = new Ex01.Bug() with FluentBug

  bugsy move 4 and show and then move 6 and show turn around move 5 and show

}
