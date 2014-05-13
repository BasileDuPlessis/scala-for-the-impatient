package com.basile.scala.ch04

/**
 * Set up a map of prices for a number of gizmos that you covet.
 * Then produce a second map with the same keys and the prices at a 10 percent discount.
 */
object Ex01 extends App {

  val gizmos = Map("Nexus 5" -> 300, "Nexus 7" -> 200, "Chromecast" -> 35)

  val discount = for((k,v) <- gizmos) yield (k, v*0.9)

  assert(discount == Map("Nexus 5" -> 270, "Nexus 7" -> 180, "Chromecast" -> 31.5))

}
