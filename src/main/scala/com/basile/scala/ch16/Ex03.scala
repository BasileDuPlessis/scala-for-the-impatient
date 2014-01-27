package com.basile.scala.ch16

import scala.xml.{Atom, Text}


/**
 * Contrast
 * <li>Fred</li> match { case <li>{Text(t)}</li> => t }
 * and
 * <li>{"Fred"}</li> match { case <li>{Text(t)}</li> => t }
 * differently ?
 */
object Ex03 extends App {
  <li>Fred</li> match {
    case <li>{Text(t)}</li> => assert(assertion = true)
  }

  <li>{"Fred"}</li> match {
    case <li>{Text(t)}</li> => assert(assertion = false)
    case <li>{_:Atom[_]}</li> => assert(assertion = true)
  }

  //{"Fred"} is an embedded string and it's turned into an Atom[String]

}
