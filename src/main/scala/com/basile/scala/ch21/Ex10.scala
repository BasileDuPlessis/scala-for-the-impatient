package com.basile.scala.ch21

/**
 * The result of "abc".map(_.toUpper) is a String ,
 * but the result of "abc".map(_.toInt) is a Vector . Find out why.
 */
object Ex10 extends {

  /*

  scalac -Xprint:typer Ex10.scala

  "abc".map(_.toUpper) use implicit scala.this.Predef.StringCanBuildFrom which is a CanBuildFrom[String, Char, String]

  scala.this.Predef.augmentString("abc").map[Char, String](((x$1: Char) => scala.this.Predef.charWrapper(x$1).toUpper))(scala.th
is.Predef.StringCanBuildFrom);


  "abc".map(_.toInt) use implicit  scala.this.Predef.fallbackStringCanBuildFrom[Int] which call canBuildFrom in IndexedSeq trait which return a Vector

  scala.this.Predef.augmentString("abc").map[Int, scala.collection.immutable.IndexedSeq[Int]](((x$2: Char) => x$2.toInt))(scala.
this.Predef.fallbackStringCanBuildFrom[Int])


http://stackoverflow.com/questions/5200505/how-are-scala-collections-able-to-return-the-correct-collection-type-from-a-map
   */

}
