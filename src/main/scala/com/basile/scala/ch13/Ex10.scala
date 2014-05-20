package com.basile.scala.ch13

/**
 * Harry Hacker reads a file into a string and wants to use a parallel collection to update the letter frequencies
 * concurrently on portions of the string. He uses the following code:
 *
 * val frequencies = new scala.collection.mutable.HashMap[Char, Int] for (c <- str.par)
 * frequencies(c) = frequencies.getOrElse(c, 0) + 1
 *
 * Why is this a terrible idea? How can he really parallelize the computation? (Hint: Use aggregate .)
 */
object Ex10 extends App {

  //It's terrible because parallel collection mutate a common collection frequencies => unpredictable


  import scala.io.Source
  import scala.collection.immutable.HashMap


  val str = Source.fromURL(getClass.getResource("/a.csv"), "UTF-8").getLines.mkString

  val result = str.par.aggregate(HashMap[Char, Int]())(
    (m, c) =>  m + (c -> (m.getOrElse(c, 0) + 1)),
    (m1, m2) => m1 ++ m2.map{ case (k,v) => k -> (v + m1.getOrElse(k, 0)) }
  )

  assert( result('e') == 25195 )



}
