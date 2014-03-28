package com.basile.scala.ch20

import scala.actors.Actor
import scala.util.Random

/**
 * Write a program that generates an array of n random numbers (where n is a large value, such as 1,000,000),
 * and then computes the average of those numbers by distributing the work over multiple actors,
 * each of which computes the sum of a subrange of the values,
 * sending the result to an actor that combines the results.
 *
 * If you run your program on a dual-core or quad-core processor,
 * what is the speedup over a single-threaded solution?
 */
object Ex01 extends App {

  def rand(n: Int) = new Array[Int](n).map(_ => Random.nextInt(n))


  class SumActor(averageActor: AverageActor) extends Actor {
    def act {
      while (true) {
        receive {
          case (a: Array[Int], i: Int) => {
            averageActor ! a.sum / partSize
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
        case i:Int => {
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
  val nbParts = 1
  val partSize = n / nbParts
  val rands = rand(n)


  val myAverageActor = new AverageActor
  myAverageActor.start()



  (0 until nbParts).foreach { i =>
    (new SumActor(myAverageActor)).start ! (rands.slice(i*partSize, (i+1)*partSize), i)
  }

  /*
  On a core duo with 20 000 000 random numbers
  - With nbParts=1 execution time around 850ms
  - With nbParts=2 execution time around 750ms
   */


}
