package com.basile.scala.forum

/**
 *
 */
object ForumRegExp {

  val matchSubject1 =
    ("""^[0-9]+,""" +
      """"?(http://forum\.doctissimo\.fr/([^/\.]+)/([^/\.]+)/.*sujet_([0-9]+)_1\.htm)"?,""" +
      """([0-9]+)$""").r

}
