package com.basile.scala.ch08

import scala.collection.mutable.ArrayBuffer

/**
 * Define an abstract class Item with methods price and description . A SimpleItem is an item whose price
 * and description are specified in the constructor. Take advantage of the fact that a val can override a def .
 *
 * A Bundle is an item that contains other items. Its price is the sum of the prices in the bundle.
 * Also provide a mechanism for adding items to the bundle and a suitable description method.
 *
 */
object Ex04 extends App {

  abstract class Item {
    def price: Double
    def description: String
  }

  class SimpleItem(val price: Double, val description: String) extends Item

  class Bundle extends Item {

    private var items = ArrayBuffer[Item]()

    def price(): Double = items.foldLeft(0.0)(_+_.price)
    def description(): String = items.map(_.description).mkString(",")

    def addItem(i: Item) {
      items += i
    }
  }

  val si1 = new SimpleItem(4.5, "ring")
  val si2 = new SimpleItem(6.5, "necklace")

  val b = new Bundle()
  b.addItem(si1)
  b.addItem(si2)

  assert( b.price == 11.0 )
  assert( b.description == "ring,necklace" )


}
