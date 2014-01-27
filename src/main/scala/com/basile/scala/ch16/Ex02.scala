package com.basile.scala.ch16

import scala.xml.{Text, Elem}

/**
 * What is the result of
 * <ul>
 *   <li>Opening bracket: [</li>
 *   <li>Closing bracket: ]</li>
 *   <li>Opening brace: {</li>
 *   <li>Closing brace: }</li>
 * </ul> How do you fix it?
 */
object Ex02 extends App {

  val xml = <ul>
    <li>{Text("Opening bracket: [")}</li>
    <li>{Text("Closing bracket: ]")}</li>
    <li>{Text("Opening brace: {")}</li>
    <li>{Text("Closing brace: }")}</li>
   </ul>

  assert(xml.isInstanceOf[Elem])

  //The result is : error: in XML literal: in XML content, please use '}}' to express '}'
  //Use Text() to create the text node

}
