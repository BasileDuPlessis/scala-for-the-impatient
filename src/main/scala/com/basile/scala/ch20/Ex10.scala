package com.basile.scala.ch20

import scala.util.Random
import scala.actors.{!, OutputChannel, Actor, Channel}

/**
 * Created by basile.duplessis on 16/04/14.
 */
object Ex10 extends App {

  case class Compute(a: Array[Int], c: Channel[Int])

  def rand(n: Int) = new Array[Int](n).map(_ => Random.nextInt(n))

  class SumActor extends Actor {
    def act {
      while (true) {
        receive {
          case Compute(a, o) => {
            o ! a.sum / partSize
            exit()
          }
        }
      }
    }
  }

  class AverageActor extends Actor {
    val now = System.nanoTime
    var total = 0
    var countParts = 0
    def act {
      react {
        case !(channel, i: Int) => {
          total += i
          countParts += 1
          if (countParts < nbParts) {act} else {
            println(total / nbParts)
            println("%d milliseconds".format((System.nanoTime - now) / 1000000))
          }
        }
      }
    }
  }

  val n = 20000000
  val nbParts = 2
  val partSize = n / nbParts
  val rands = rand(n)


  val myAverageActor = new AverageActor
  myAverageActor.start()

  val channel = new Channel[Int](myAverageActor)

  (0 until nbParts).foreach { i =>
    (new SumActor).start ! Compute(rands.slice(i*partSize, (i+1)*partSize), channel)
  }
}
