package com.basile.scala.ch09

import scala.math.pow
import java.io.PrintWriter

/**
 * Write a Scala program that writes the powers of 2 and their reciprocals to a file, with the exponent ranging
 * from 0 to 20. Line up the columns: 1 1 2 0.5 4 0.25 ... ...
 */
object Ex05 extends App {

  val out = new PrintWriter("ch09-ex05.txt")

  (0 to 20).map(pow(2,_)).foreach(i => out.println("%7d   ".format(i.toInt) + 1/i))

  //Option
  //for(v <- 0 to 20; p=pow(2,v)) {out.println("%7d   ".format(p.toInt) + 1/p)}

  out.close

}
