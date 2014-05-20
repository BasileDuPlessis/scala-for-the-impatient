package com.basile.scala.ch14

/**
 * Add a case class Multiple that is a subclass of the Item class.
 * For example, Multiple(10, Article("Blackwell Toaster", 29.95)) describes ten toasters.
 *
 * Of course, you should be able to handle any items, such as bundles or multiples, in the second argument.
 *
 * Extend the price function to handle this new case.
 */
object Ex04 extends App {

  abstract class Item
  case class Article(description: String, price: Double) extends Item
  case class Bundle(description: String, discount: Double, items: Item*) extends Item
  case class Multiple(quantity: Int, items: Item*) extends Item

  val m = Multiple(10,
    Bundle("DVD pack", 2.0,
      Article("Dr No", 4.00),
      Article("Goldfinger", 6.00)
    ),
    Multiple(2,
      Article("Thunderbolt", 7.00)
    )
  )

  def price(it: Item): Double = it match {
    case Multiple(q, its @ _*) => q * its.map(price _).sum
    case Bundle(_, d, its @ _*) => its.map(price _).sum - d
    case Article(_, p) => p
  }

  assert( price(m) == 220.0 )

}
