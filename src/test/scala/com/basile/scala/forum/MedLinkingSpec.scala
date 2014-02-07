package com.basile.scala.forum

import org.scalatest.FlatSpec

trait ForumTitleAppTestComponent extends PageDataComponent
  with ForumCatIdsComponent with  CsvSourceComponent with CsvOutputComponent {

  val forumCatIds = new ForumCatIds(
    (f: String) => """{"resource":{"resources":[{"id":38, "url_name":"apples_and_pears"}]}}"""
  )

  val pageData = new JsonData(
    (s: Seq[String]) => """{"resource":{"title":"Apple pie"}}"""
  )

  val csvSource = new CsvSource(Iterator(
    "0,http://forum.doctissimo.fr/grossesse-bebe/apples_and_pears/transat-choisir-sujet_604415_1.htm,1"
  ))

  val csvOutput = new CsvConsoleOutput()

}


/**
 *
 */
class MedLinkingSpec extends FlatSpec {

  val forumTitleApp = new ForumTitleApp("grossesse-bebe") with ForumTitleAppTestComponent

  forumTitleApp.run

}
