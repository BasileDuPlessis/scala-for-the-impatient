package com.basile.scala.ch16

import scala.xml.{NodeSeq, Document}
import scala.xml.parsing._
import scala.io.Source

/**
 *
 */
object Ex04 extends App {

  def getImgWithoutAlt(doc: Document): NodeSeq = doc \\ "img" filter { _ \ "@alt" isEmpty }

}
