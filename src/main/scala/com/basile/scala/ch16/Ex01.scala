package com.basile.scala.ch16

import org.ccil.cowan.tagsoup.jaxp.SAXFactoryImpl
import scala.xml.parsing.NoBindingFactoryAdapter
import java.net.URL
import scala.xml._
/**
 * Created by basile.duplessis on 16/01/14.
 */
object Ex01 extends App {


  lazy val adapter = new NoBindingFactoryAdapter
  lazy val parser = (new SAXFactoryImpl).newSAXParser

  val doc = adapter.loadXML(
    new InputSource(
      new URL("" +
        "http://www.doctissimo.fr/html/dossiers/orthodontie/articles/16129-inclusion-dentaire.htm"
      ).openStream()),
    parser
  )

  println(doc \\ "p" filter { _ \ "@class" exists(_.text == "chapodocgras")})


}
