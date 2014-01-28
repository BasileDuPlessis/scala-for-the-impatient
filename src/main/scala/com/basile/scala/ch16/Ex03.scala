package com.basile.scala.ch16

import scala.xml.{Atom, Text, Elem}

/**
 * Contrast
 * <li>Fred</li> match { case <li>{Text(t)}</li> => t }
 * and
 * <li>{"Fred"}</li> match { case <li>{Text(t)}</li> => t }
 * differently ?
 */
object Ex03 extends App {

  def getTextFromElem(elem: Elem): String = {
    elem match {
      case Elem(_, _, _, _, Text(t)) => t
    }
  }

  def getAtomFromElem(elem: Elem): String = {
    elem match {
      case Elem(_, _, _, _, t:Atom[_]) => t.toString()
    }
  }

}
