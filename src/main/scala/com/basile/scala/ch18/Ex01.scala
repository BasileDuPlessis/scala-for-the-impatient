package com.basile.scala.ch18

/**
 * Implement a Bug class modeling a bug that moves along a horizontal line.
 * The move method moves in the current direction, the turn method makes the bug turn around,
 * and the show method prints the current position. Make these methods chainable.
 * For example, Click here to view code image bugsy.move(4).show().move(6).show().turn().move(5).show()
 * should display 4 10 5 .
 */
object Ex01 extends App {

  class Bug {

    var position = 0
    var direction = 1

    def move(n: Int): this.type = {
      position += n * direction
      this
    }

    def turn(): this.type = {
      direction *= -1
      this
    }

    def show(): this.type = {
      println(position)
      this
    }
  }

  val bugsy = new Bug

  bugsy.move(4).show().move(6).show().turn().move(5).show()

}
