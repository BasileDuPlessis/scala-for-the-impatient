package com.basile.scala.ch20

import scala.actors.Actor
import java.io.File
import scala.io.Source
import scala.util.matching.Regex

/**
 * Write a program that counts how many words match a given regular expression in all files of all subdirectories
 * of a given directory. Have one actor per file, one actor that traverses the subdirectories,
 * and one actor to accumulate the results.
 */
object Ex03 extends App {

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
        case f:File =>  continuation ! CountValue(
          reg.findAllIn(Source.fromFile(f, "ISO-8859-1").getLines.mkString).size
        )
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


  crawl ! new File("""C:\dev\mag_2000""")


}
