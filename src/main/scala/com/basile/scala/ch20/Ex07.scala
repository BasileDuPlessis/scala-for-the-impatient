package com.basile.scala.ch20

import java.io.{FileNotFoundException, File}
import scala.actors.{UncaughtException, Exit, Actor}
import scala.util.matching.Regex
import scala.io.Source

/**
 * Add a supervisor to the program of exercise 3 that monitors the file reading
 * actors and logs any that exit with an IOException .
 * Try triggering the exception by removing files that have been scheduled for processing.
 */
object Ex07 extends App {

  val now = System.nanoTime
  case class CrawledFiles(n: Int)
  case class CountValue(n: Int)

  /*
  Supervisor log FileNotFoundException to console
   */
  class SupervisorActor extends Actor {
    def act {
      link(acc)
      trapExit = true
      while (true) {
        receive {
          case Exit(linked, UncaughtException(_, _, _, _, cause: FileNotFoundException)) => {
            //Log cause to console
            println(cause)
            //Send count zero to accumulator
            acc ! CountValue(0)
          }
          case Exit(linked: AccumulateCounts, _) => exit()
        }
      }
    }
  }

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
                case f: File if filter(f) => {
                  CountFile += 1
                  //simulate file not found exception
                  if (f.getName.endsWith("2003.htm"))
                    apply(new File("file_not_found.htm"))
                  else apply(f)
                }
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

  class CountWordActor(continuation: Actor, reg: Regex) extends Actor {
    def act {
      link(sup)
      react {
        case f: File => continuation ! CountValue(
          reg.findAllIn(Source.fromFile(f.toString, "ISO-8859-1").getLines.mkString).size
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
              println("AccumulateCounts : %d milliseconds".format((System.nanoTime - now) / 1000000))
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

  val sup = new SupervisorActor
  sup.start()


  val crawl = new CrawlDirActor(
    (f:File) =>  f.toString.endsWith(".htm"), //filter
    (f:File) =>  (new CountWordActor(acc, reg)).start ! f, //actor to call for each file
    acc
  )
  crawl.start()


  crawl ! new File("""C:\dev\html""")



}
