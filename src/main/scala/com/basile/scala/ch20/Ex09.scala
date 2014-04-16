package com.basile.scala.ch20

import java.io.File
import scala.actors.Actor
import scala.util.matching.Regex
import scala.io.Source

/**
 * Produce a faulty implementation of the program in exercise 3, in which the actors update a shared counter.
 * Can you demonstrate that the program acts incorrectly?
 */
object Ex09 extends App {

  var faultySum = 0
  case class CrawledFiles(n: Int)
  case class CountValue(n: Int)

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
              exit()
            }

          }
        }
      }
    }
  }

  class CountWordActor(continuation: Actor, reg: Regex) extends Actor {
    def act {
      react {
        case f: File => {
          val count = reg.findAllIn(Source.fromFile(f, "ISO-8859-1").getLines.mkString).size
          faultySum += count
          continuation ! CountValue(count)
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
          case CountValue(n) => {
            count += 1
            sum += n
            if ( totalCount == count ) {
              println(sum + " matches in " + count + " files")
              println(faultySum + " matches in " + count + " files")
              exit()
            }
          }
          case CrawledFiles(n) => {
            totalCount = n
            if ( count == 0 ) exit()
          }
        }
      }
    }
  }


  val reg = """(?i)\b(?=z)\w+\b""".r
  val acc = new AccumulateCounts
  acc.start()

  val crawl = new CrawlDirActor(
    (f:File) =>  f.toString.endsWith(".htm"),
    (f:File) =>  (new CountWordActor(acc, reg)).start ! f,
    acc
  )
  crawl.start()


  crawl ! new File("""C:\dev\html""")

  /*
  6682 matches in 3386 files => Good sum
  6677 matches in 3386 files => Faulty sum
  */

}
