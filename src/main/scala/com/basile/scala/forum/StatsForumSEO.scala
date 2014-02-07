package com.basile.scala.forum

import scala.io.Source
import scala.collection.mutable.{ArrayBuffer, Map}
import java.io.{FileOutputStream, OutputStreamWriter, BufferedWriter}


/**
 *
 */
object StatsForumSEO extends App {

  val qt = 1000

  val sourceHandler = Source.fromFile("""C:\dev\doctissimo_all_visits.2.csv""")
  val outputHandler: BufferedWriter = new BufferedWriter(
    new OutputStreamWriter(
      new FileOutputStream("""C:\dev\extract-stats-cat-forum-""" + qt + """.csv"""),
      "UTF-8"
    )
  )

  val m:Map[String, Map[String, ArrayBuffer[Int]]] = Map()

  var limit = 0


  sourceHandler.getLines.foreach {
    l => l match {
      case ForumRegExp.matchSubject1(url, forumName, categoryName, subjectId, visits) => {

        m.get(forumName) match {
          case None => m.put( forumName, Map( categoryName -> ArrayBuffer() ) )
          case Some(s: Map[String, _]) if !s.isDefinedAt(categoryName) => {
            m(forumName).put(categoryName, ArrayBuffer())
          }
          case _ => ()
        }

        m(forumName)(categoryName) += visits.toInt

      }
      case _ => ()
    }
  }

  m.foreach {
    f => f._2.foreach {
      c => {
        val lm = c._2.sortBy(-_).take(qt)
        outputHandler.write( List(f._1, c._1, lm.size, lm.sum).mkString("", ";", "\r\n") )
      }
    }
  }

  outputHandler.close

}
