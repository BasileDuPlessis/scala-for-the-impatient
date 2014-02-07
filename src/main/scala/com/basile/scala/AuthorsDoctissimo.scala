package com.basile.scala

import org.ccil.cowan.tagsoup.jaxp.SAXFactoryImpl
import scala.xml.parsing.NoBindingFactoryAdapter
import java.net.URL
import scala.xml._
import scala.io.Source
import java.io.{PrintWriter, InputStreamReader, FileInputStream}


/**
 * Extract Authors from Doctissimo
 */
object AuthorsDoctissimo extends App {

  /**
   * Return Author Name from <dl class="auteur"><dt>Author Name</dt></dl>
   * @param doc the doc
   * @return
   */
  def getAuthorFromArticle(doc: Node): String = {
    val dt = (doc \\ "dl" filter { _ \ "@class" exists(_.text == "auteur")}) \\ "dt"
    dt.flatMap(_.child).mkString(", ").replaceAll(" {2,}"," ").trim
  }

  def getCategoryFromArticle(doc: Node): String = {
    val body = doc \\ "body"
    body(0).attributes("class").text
  }

  /**
   * Return Author name from a gallery
   * @param doc the doc
   * @return
   */
  def getAuthorFromGallery(doc: String): String = {
    val rDescription = """<div class="description">.*<div class="bloc_diaporama_more">""".r
    val rAuthor = """>(\w+ \w+)<""".r

    rDescription.findFirstIn(doc) match {
      case Some(value) => (for (rAuthor(m) <- rAuthor.findAllIn(value)) yield m).mkString("; ")
      case None => ""
    }

  }

  object XmlFromFile {

    lazy val adapter = new NoBindingFactoryAdapter
    lazy val parser = (new SAXFactoryImpl).newSAXParser

    def get(streamReader: InputStreamReader): Node = {
      adapter.loadXML(new InputSource(streamReader), parser)
    }

    def get(url: URL): Node = {
      adapter.loadXML(new InputSource(url.openStream()), parser)
    }

  }


  val in = getClass.getResource("/new-articles-2013.txt")
  val out = new PrintWriter("extract-new-articles-2013.csv", "ISO-8859-1")

  Source.fromURL(in).getLines().foreach(
    l => {
      val doc = XmlFromFile.get(
        new InputStreamReader(new FileInputStream("Y:\\html\\" + l), "ISO-8859-1")
      )
      val author = getAuthorFromArticle(doc)

      val category = getCategoryFromArticle(doc)

      out.write("http://www.doctissimo.fr/html/" + l.replace('\\', '/') + ";" + author + ";" + category + "\n")
    }
  )

  out.close()


  val in2 = getClass.getResource("/new-galleries-2013.txt")
  val out2 = new PrintWriter("extract-new-galleries-2013.csv", "ISO-8859-1")

  Source.fromURL(in2).getLines().foreach(
    l => {
      val author = getAuthorFromGallery(Source.fromURL(l).getLines().mkString)
      out2.write(l + ";" + author + "\n")
    }
  )

  out2.close()

}
