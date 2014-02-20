package com.basile.scala.forum

import scala.io._
import play.api.libs.json._
import java.io.{BufferedWriter, OutputStreamWriter, FileOutputStream}

trait ForumCatIdsComponent {

  val forumCatIds:ForumCatIds

  class ForumCatIds(private val jsonUrl: (String) => String) {

    import scala.collection.mutable.Map

    private val forums = Map[String, Map[String, String]]()

    private def getCatIds(f: String) {
      val cat = Json.parse(jsonUrl(f))

      forums(f) = Map[String, String]()

      (cat \ "resource" \ "resources").as[Seq[JsObject]].foreach{
        r =>
          forums(f).put(
            (r \ "url_name").as[String].toLowerCase,
            (r \ "id").as[Int].toString
          )
      }

    }

    def get(f: String): Map[String, String] = {
      forums.get(f) match {
        case None => getCatIds(f); forums(f)
        case m => m.get
      }

    }

  }
}

trait PageDataComponent {
  abstract class PageData {
    def getTitle: String
    def load(args: String*)
  }
  val pageData:PageData

  class JsonData(val js: (Seq[String]) => String) extends PageData {

    var topic: JsValue = null

    def load(args: String*) {
      topic = Json.parse(js(args))
    }

    def getTitle: String = {
      (topic \ "resource" \ "title").asOpt[String] match {
        case None => ""
        case s:Some[String] => """[\t\r\n]+""".r.replaceAllIn(s.get, " ")
      }
    }

  }
}

trait CsvSourceComponent {

  val csvSource:CsvSource

  class CsvSource(val it: Iterator[String], val from:Int = 0) {

    def this(bs: BufferedSource, from: Int) {
      this(bs.getLines, from)
    }

    def getLines:Iterator[(String, Int)] = from match {
      case 0 => it.zipWithIndex
      case in:Int => it.zipWithIndex.drop(in)
    }

  }

 }

trait CsvOutputComponent {
  abstract class CsvOutput {
    def write(s: String*): Unit
    def format(s: Seq[String]) = s.mkString("", "\t", "\n")
  }

  val csvOutput:CsvOutput

  class CsvWriterOutput(val writer:java.io.Writer) extends CsvOutput {
    def write(s: String*) {writer.write(format(s))}
  }
}


class ForumTitleApp(val forumName: String) {
  this: PageDataComponent with ForumCatIdsComponent
    with CsvSourceComponent with CsvOutputComponent  =>
      def run {
        csvSource.getLines.foreach {
          t => t._1 match {
            case CsvRegExp.matchSubject1(url, `forumName`, categoryName, subjectId, visits) => {
              forumCatIds.get(`forumName`).getOrElse(categoryName.toLowerCase, null) match {
                case null => ()
                case categoryId:String => {

                  try {
                    pageData.load(`forumName`, categoryId, subjectId)
                    Thread.sleep(50)
                  } catch {
                    case e:java.net.ConnectException => throw new Exception(e.getMessage + " - " + t._2)
                  }

                  csvOutput.write(
                    url, `forumName`, categoryId, categoryName, subjectId, visits, pageData.getTitle
                  )

                }
              }


            }
            case _ => ()
          }
        }

      }
}


/**
 *
 */
object GetTitle extends App {

  val forumName = "viepratique"
  val fromLine = 1101648
  val sourceHandler = Source.fromFile("""C:\dev\doctissimo_all_visits.2.csv""")
  val outputHandler: BufferedWriter = new BufferedWriter(
    new OutputStreamWriter(
      new FileOutputStream("""C:\dev\topics\extract-forum-""" + forumName + """-title.csv""", true),
      "UTF-8"
    )
  )
  val jsonPageUrl = "http://forum.doctissimo.fr/api/forums/%s/categories/%s/topics/%s/"
  val jsonCatUrl = "http://forum.doctissimo.fr/api/forums/%s/categories/"

  try {

    val forumTitleApp = new ForumTitleApp(forumName) with PageDataComponent
      with ForumCatIdsComponent with  CsvSourceComponent with CsvOutputComponent {

      val forumCatIds = new ForumCatIds(
        (f: String) => Source.fromURL(jsonCatUrl.format(f)).getLines.mkString
      )

      val pageData = new JsonData(
        (s: Seq[String]) => Source.fromURL(jsonPageUrl.format(s:_*)).getLines.mkString
      )

      val csvSource = new CsvSource(sourceHandler.getLines, fromLine)

      val csvOutput = new CsvWriterOutput(outputHandler)

    }

    forumTitleApp.run

  } catch {
    case e:Exception => println(e.getMessage)
  } finally {
    sourceHandler.close
    outputHandler.close
  }



}
