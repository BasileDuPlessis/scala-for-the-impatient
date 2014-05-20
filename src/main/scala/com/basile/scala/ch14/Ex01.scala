package com.basile.scala.ch14

/**
 * Your Java Development Kit distribution has the source code for much of the JDK in the src.zip file.
 * Unzip and search for case labels (regular expression case [^:]+: ).
 * Then look for comments starting with // and containing [Ff]alls? thr to catch comments such as
 * // Falls through or // just fall thru .
 *
 * Assuming the JDK programmers follow the Java code convention, which requires such a comment,
 * what percentage of cases falls through?
 */
object Ex01 extends App {

  //Case with no break statement requires such comment

  import java.nio.file._
  import scala.io.Source
  import scala.language.implicitConversions

  implicit def makeFileVisitor(f: (Path) => Unit) = new SimpleFileVisitor[Path] {
    override def visitFile(p: Path, attrs: attribute.BasicFileAttributes) = {
      f(p)
      FileVisitResult.CONTINUE
    }
  }

  var (cf, cc) = (0, 0)

  val matchComment = (f: Path) => {
    val rf = """(?s)case [^:]+:[^:]+?/[/*] [Ff]alls? thr""".r
    val rc = """case [^:]+:""".r

    val str = Source.fromFile(f.toString, "ISO-8859-1").getLines.mkString

    cf += rf.findAllIn(str).length
    cc += rc.findAllIn(str).length
  }

  Files.walkFileTree(FileSystems.getDefault().getPath("src"), matchComment)

  println(cf)
  println(cc)
}
