package com.basile.scala.ch02

/**
 * Write a function product(s : String) that computes the product, as described in the preceding exercises.
 */
object Ex08 extends App {

  def product(s: String): Long = s.map(_.toLong).product

  assert( product("Hello") == 9415087488L )

}
