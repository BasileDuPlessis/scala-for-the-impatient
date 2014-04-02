package com.basile.scala.ch20

import scala.actors.Actor
import java.io.File
import scala.io.Source
import scala.util.matching.Regex
import scala.collection.mutable.{Set, Map}
import scala.collection.immutable.SortedMap

/**
 * Modify the program of the preceding exercise to display all matching words,
 * each with a list of all files containing it.
 */
object Ex05 extends App {

  //Represent n files crawled
  case class CrawledFiles(n: Int)
  //Represent a list of all matches in file
  case class MatchResult(l: List[String], f: File)

  /**
   * Actor which crawl a directory and call a function for each file (filterable)
   * Send a CrawledFiles message at the end of crawl
   *
   * @param filter Function for filtering file on name basis
   * @param apply Function to apply to each file
   * @param continuation Actor to call when crawl is done
   */
  class CrawlDirActor(filter: (File) => Boolean, apply: (File) => Unit, continuation: Actor) extends Actor {

    var countDir = 1
    var CountFile = 0
    val now = System.nanoTime

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
            //When all directory crawled, send CrawledFiles message
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

  /** Count match in a file content
   * 
   * @param reg Regex to match in file content
   */
  class CountRegActor(reg: Regex) extends Actor {
    def act {
      react {
        case ( f: File, continuation: Actor ) =>
          continuation ! MatchResult(reg.findAllIn(Source.fromFile(f, "ISO-8859-1").getLines.mkString).toList, f)
      }
    }
  }

  /**
   * Display Match result
   *
   * @param continuation Actor to call after displaying
   */
  class DisplayMatchResultActor(continuation: Actor) extends Actor {
    val wordMap = Map[String, Set[String]]()
    var count = 0
    var totalCount = 0
    def act {
      loop {
        react {
          case mr @ MatchResult(l, f) => {
            count += 1

            l.foreach { k =>
              wordMap.update(k, wordMap.getOrElse(k, Set()) += f.toString)
            }

            continuation ! mr

            if ( totalCount == count ) {
              (SortedMap[String, Set[String]](wordMap.toArray:_*)).foreach {
                t => println(t._1 + ": " + t._2.mkString(","))
              }
              exit()
            }
          }
          case CrawledFiles(0) => exit()
          case cf @ CrawledFiles(n) => totalCount = n; continuation ! cf
        }
      }
    }
  }

  /**
   * Accumulate counts and print sum
   */
  class AccumulateCounts extends Actor {
    var count = 0
    var sum = 0
    var totalCount = 0
    val now = System.nanoTime
    def act {
      loop {
        react {
          case MatchResult(l, _) => {
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

  //Call crawler
  val crawl = new CrawlDirActor(
    (f:File) =>  f.toString.endsWith(".htm"),
    (f:File) =>  (new CountRegActor(reg)).start ! (f, display),
    display
  )
  crawl.start()


  crawl ! new File("""C:\dev\html""")


}
