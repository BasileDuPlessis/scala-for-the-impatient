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

  def getTextFromLi(elem: Elem): String = {
    elem match {
      case <li>{Text(t)}</li> => t
    }
  }

  def getAtomFromLi(elem: Elem): String = {
    elem match {
      case <li>{t:Atom[_]}</li> => t.toString()
    }
  }

}
