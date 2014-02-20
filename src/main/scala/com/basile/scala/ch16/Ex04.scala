package com.basile.scala.ch16

import scala.xml.{NodeSeq, Document}
import scala.language.postfixOps

/**
 *
 */
object Ex04 extends App {

  def getImgWithoutAlt(doc: Document): NodeSeq = doc \\ "img" filter { _ \ "@alt" isEmpty }

}
