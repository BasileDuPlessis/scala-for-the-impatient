package com.basile.scala.ch02

/**
 * Write a for loop for computing the product of the Unicode codes of all letters in a string.
 * For example, the product of the characters in "Hello" is 9415087488L .
 */
object Ex06 extends App {

  var result: Long = 1

  for (c <- "Hello") result *= c.toLong

  assert( result == 9415087488L )

}
