package com.basile.scala.ch18

/**
 * Complete the fluent interface in Section 18.1 , “ Singleton Types ,”
 * on page 246 so that one can call
 * set Title to "Scala for the Impatient" set Author to "Cay Horstmann"
 */
object Ex03 extends App {

  object Title
  object Author

  class Document {
    var title: String = _
    var author: String = _
    var useNextArgAs: Any = null

    def set(obj: Title.type) = {
      useNextArgAs = obj
      this
    }
    def set(obj: Author.type) = {
      useNextArgAs = obj
      this
    }
    def to(s: String) = {
      useNextArgAs match {
        case Title => title = s
        case Author => author = s
      }
      this
    }
  }

  val myBook = new Document

  myBook set Title to "Scala for the Impatient" set Author to "Cay Horstmann"

  println(myBook.title)
  println(myBook.author)

}
