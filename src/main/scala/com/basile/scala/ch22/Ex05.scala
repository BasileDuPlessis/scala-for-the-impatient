package com.basile.scala.ch22

import scala.util.continuations._

/**
 * Consider this sample program that uses a continuation to turn an iteration into an iterator:
 * object Main extends App {
 *  var cont: Unit => String = null
 *  val a = "Mary was a little lamb".split(" ")
 *
 *  reset {
 *    var i = 0
 *    while (i < a.length) {
 *      shift {
 *        k: (Unit => String) => {
 *          cont = k
 *          a(i)
 *        }
 *      }
 *      i += 1
 *    }
 *    ""
 *  }
 *
 *   println(cont())
 *  println(cont())
 * }
 *
 * Compile with the -Xprint:selectivecps flag and look at the generated code.
 *
 * How is the while statement treated when transformed to CPS?
 */
object Ex05 extends App {

  var cont: Unit => String = null
  val a = "Mary was a little lamb".split(" ")

  reset {
    var i = 0
    while (i < a.length) {
      shift {
        k: (Unit => String) => {
          cont = k
          a(i)
        }
      }
      i += 1
    }
    ""
  }

  println(cont())
  println(cont())

  //While statement is treated as a recursive method

}


