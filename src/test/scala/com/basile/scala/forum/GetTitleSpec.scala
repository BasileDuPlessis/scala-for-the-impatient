package com.basile.scala.forum

import org.scalatest.FlatSpec

trait ForumTitleAppTestComponent extends PageDataComponent
  with ForumCatIdsComponent with  CsvSourceComponent with CsvOutputComponent {

  class CsvLogOutput extends CsvOutput {
    var log: String = ""
    def write(s: String*) {log += format(s)}
  }

  val forumCatIds = new ForumCatIds(
    (f: String) => """{"resource":{"resources":[{"id":38, "url_name":"apples_and_pears"}]}}"""
  )

  val pageData = new JsonData(
    (s: Seq[String]) => """{"resource":{"title":"Apple pie"}}"""
  )

  val csvSource = new CsvSource(Iterator(
    "0,http://forum.doctissimo.fr/grossesse-bebe/apples_and_pears/transat-choisir-sujet_604415_1.htm,1"
  ))

  val csvOutput = new CsvLogOutput()

}


/**
 *
 */
class GetTitleSpec extends FlatSpec {

  "ForumTitleApp.run" should "Return formatted csv line" in {
    val forumTitleApp = new ForumTitleApp("grossesse-bebe") with ForumTitleAppTestComponent
    forumTitleApp.run

    val log = "http://forum.doctissimo.fr/grossesse-bebe/apples_and_pears/transat-choisir-sujet_604415_1.htm" +
      "\tgrossesse-bebe\t38\tapples_and_pears\t604415\t1\tApple pie\n"

    assert(forumTitleApp.csvOutput.log == log)
  }


}
