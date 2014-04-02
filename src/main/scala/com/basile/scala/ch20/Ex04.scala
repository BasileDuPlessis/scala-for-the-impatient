package com.basile.scala.ch20

import scala.actors.Actor
import java.io.File
import scala.io.Source
import scala.util.matching.Regex

/**
 * Modify the program of the preceding exercise to display all matching words.
 */
object Ex04 extends App {

  val now = System.nanoTime

  case class CrawledFiles(n: Int)
  case class MatchResult(l: List[String])

  class CrawlDirActor(filter: (File) => Boolean, apply: (File) => Unit, continuation: Actor) extends Actor {

    var countDir = 1
    var CountFile = 0

    def act {
      while (true) {
        receive {
          case file:File => {
            file.listFiles.foreach {
              _ match {
                case d: File if d.isDirectory => countDir += 1; this ! d
                case f: File if filter(f) => CountFile += 1; apply(f)
                case _ => ()
              }
            }
            countDir -= 1
            if (countDir == 0) {
              continuation ! CrawledFiles(CountFile)
              println("crawl ended : %d milliseconds".format((System.nanoTime - now) / 1000000))
              exit()
            }

          }
        }
      }
    }
  }

  class CountWordActor(reg: Regex) extends Actor {
    def act {
      react {
        case ( f: File, continuation: Actor ) =>
          continuation ! MatchResult(reg.findAllIn(Source.fromFile(f, "ISO-8859-1").getLines.mkString).toList)
      }
    }
  }

  class DisplayMatchResultActor(continuation: Actor) extends Actor {
    val wordSet = scala.collection.mutable.SortedSet[String]()
    var count = 0
    var totalCount = 0
    def act {
      loop {
        react {
          case mr @ MatchResult(l) => {
            count += 1
            wordSet ++= l
            continuation ! mr
            if ( totalCount == count ) {
              wordSet.foreach(println)
              exit()
            }
          }
          case CrawledFiles(0) => exit()
          case cf @ CrawledFiles(n) => totalCount = n; continuation ! cf
        }
      }
    }
  }

  class AccumulateCounts extends Actor {
    var count = 0
    var sum = 0
    var totalCount = 0
    def act {
      loop {
        react {
          case MatchResult(l) => {
            count += 1
            sum += l.size
            if ( totalCount == count ) {
              println(sum + " matches in " + count + " files")
              println("AccumulateCounts : %d milliseconds".format((System.nanoTime - now) / 1000000))
              exit()
            }
          }
          case CrawledFiles(0) => exit()
          case CrawledFiles(n) => totalCount = n
        }
      }
    }
  }


  val reg = """(?i)\b(?=z)\w+\b""".r
  val acc = new AccumulateCounts
  acc.start()

  val display = new DisplayMatchResultActor(acc)
  display.start()


  val crawl = new CrawlDirActor(
    (f:File) =>  f.toString.endsWith(".htm"),
    (f:File) =>  (new CountWordActor(reg)).start ! (f, display),
    display
  )
  crawl.start()


  crawl ! new File("""C:\dev\html""")


}
