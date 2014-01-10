package com.basile.scala.ch15

import java.io.IOException

/**
 * Created by basile.duplessis on 09/01/14.
 */
class Ex05 {

  import scala.io.Source

  @throws(classOf[IOException]) def fileToString(file: String): String = {
    val url = getClass.getResource(file)

    if (url == null) throw new IOException("File not found") else Source.fromURL(url).mkString
  }

}
