package com.basile.scala.crm

import scala.io.Source
import java.io.{FileOutputStream, OutputStreamWriter, BufferedWriter, File}
import com.basile.scala.forum.CsvRegExp
import java.text.ParseException

/**
 * CRM FILE
 */
object FeedControl extends App {

  val format = new java.text.SimpleDateFormat("dd/MM/yyyy")

  def stringToInt(s: String): Int = {
    s match {
      case "" => 0
      case null => 0
      case v => v.toInt
    }
  }

  def validateDate(s: String): Boolean = {
    try {
      format.parse(s)
      true
    } catch {
      case e: ParseException => false
      case e: NullPointerException => true
    }
  }

  def validateFields(m: Map[String, String]): Boolean = {

    try {
      assert(m("id") != "")
      assert(m("email") != "")

      val nbAgedChild = stringToInt(m("nb_enfants_0_6_ans")) +
        stringToInt(m("nb_enfants_7_12_ans")) +
        stringToInt(m("nb_enfants_13_18_ans"))

      assert(stringToInt(m("nb_enfant")) >= nbAgedChild)

      m.foreach(
        t => t._1 match {
          case CsvRegExp.csvParticipationField(d) => {
            m(t._1) match {
              case "1" => assert(m("date_derniere_" + t._1) != "")
              case v => assert(v != "")
            }
          }
          case _ => ()
        }
      )


      m.foreach(
        t => t._1 match {
          case CsvRegExp.csvDateField(d) => assert(validateDate(t._2) == true)
          case _ => ()
        }
      )

      true
    } catch {
      case a: AssertionError => false
    }
  }

  val header = """ *\r\n *\|""".r.split("""id
                 |email
                 |civilite
                 |nom
                 |prenom
                 |sexe
                 |adresse
                 |complement_adresse
                 |code_postal
                 |ville
                 |pays
                 |pseudo
                 |date_naissance
                 |nb_enfant
                 |nb_enfants_0_6_ans
                 |nb_enfants_7_12_ans
                 |nb_enfants_13_18_ans
                 |enfant_1_prenom
                 |enfant_1_date_naissance
                 |enfant_1_sexe
                 |enfant_2_prenom
                 |enfant_2_date_naissance
                 |enfant_2_sexe
                 |enfant_3_prenom
                 |enfant_3_date_naissance
                 |enfant_3_sexe
                 |enfant_4_prenom
                 |enfant_4_date_naissance
                 |enfant_4_sexe
                 |enfant_5_prenom
                 |enfant_5_date_naissance
                 |enfant_5_sexe
                 |date_derniere_activite
                 |date_creation
                 |date_suppression
                 |date_derniere_connexion
                 |date_derniere_activite_forum
                 |nb_messages_postes_forums
                 |participation_forum_sante
                 |date_derniere_participation_forum_sante
                 |participation_forum_grossesse
                 |date_derniere_participation_forum_grossesse
                 |participation_forum_mode
                 |date_derniere_participation_forum_mode
                 |participation_forum_beaute
                 |date_derniere_participation_forum_beaute
                 |participation_forum_nutrition
                 |date_derniere_participation_forum_nutrition
                 |participation_forum_psycho
                 |date_derniere_participation_forum_psycho
                 |participation_forum_sexualite
                 |date_derniere_participation_forum_sexualite
                 |participation_forum_loisirs
                 |date_derniere_participation_forum_loisirs
                 |participation_forum_people
                 |date_derniere_participation_forum_people
                 |participation_forum_medicaments
                 |date_derniere_participation_forum_medicaments
                 |participation_forum_forme
                 |date_derniere_participation_forum_forme
                 |participation_forum_vie_pratique
                 |date_derniere_participation_forum_vie_pratique
                 |participation_forum_animaux
                 |date_derniere_participation_forum_animaux
                 |participation_forum_cuisine
                 |date_derniere_participation_forum_cuisine
                 |participation_forum_environnement
                 |date_derniere_participation_forum_environnement
                 |participation_forum_voyage
                 |date_derniere_participation_forum_voyage
                 |NL_DOCTI_beaute
                 |date_abo_NL_DOCTI_beaute
                 |date_desabo_NL_DOCTI_beaute
                 |NL_DOCTI_magazine
                 |date_abo_NL_DOCTI_magazine
                 |date_desabo_NL_DOCTI_magazine
                 |NL_DOCTI_recette
                 |date_abo_NL_DOCTI_recette
                 |date_desabo_NL_DOCTI_recette
                 |NL_DOCTI_offres_partenaires
                 |date_abo_NL_DOCTI_offres_partenaires
                 |date_desabo_NL_DOCTI_offres_partenaires
                 |NL_DOCTI_tests
                 |date_abo_NL_DOCTI_tests
                 |date_desabo_NL_DOCTI_tests
                 |NL_DOCTI_developpement_personnel
                 |date_abo_NL_DOCTI_developpement_personnel
                 |date_desabo_NL_DOCTI_developpement_personnel
                 |NL_DOCTI_videos
                 |date_abo_NL_DOCTI_videos
                 |date_desabo_NL_DOCTI_videos
                 |NL_DOCTI_sexo
                 |date_abo_NL_DOCTI_sexo
                 |date_desabo_NL_DOCTI_sexo
                 |NL_DOCTI_forum
                 |date_abo_NL_DOCTI_forum
                 |date_desabo_NL_DOCTI_forum
                 |NL_DOCTI_extra
                 |date_abo_NL_DOCTI_extra
                 |date_desabo_NL_DOCTI_extra""")

  val stats = (new Array[Int](101)).map(_ => 0)

  val dir = new File("""C:\dev\crm""")

  val list = dir.listFiles().filter(f => f.toString.endsWith(".csv"))


  val outputFormatErrorsHandler: BufferedWriter = new BufferedWriter(
    new OutputStreamWriter(
      new FileOutputStream("""C:\dev\logs\crm-format-errors.csv"""),
      "UTF-8"
    )
  )
  val outputDataErrorsHandler: BufferedWriter = new BufferedWriter(
    new OutputStreamWriter(
      new FileOutputStream("""C:\dev\logs\crm-data-errors.csv"""),
      "UTF-8"
    )
  )
  val outputNbColsErrorsHandler: BufferedWriter = new BufferedWriter(
    new OutputStreamWriter(
      new FileOutputStream("""C:\dev\logs\crm-nb-cols-errors.csv"""),
      "UTF-8"
    )
  )

  val tempData = new Array[String](101)
  var value:String = null

  list.foreach {
    f => {
      Source.fromFile(f.toString).getLines.drop(1).foreach{
        l => {


          CsvRegExp.csvLineMatch.findFirstIn(l) match {
            case None => outputFormatErrorsHandler.write(l + "\n")
            case Some(_) => ()
          }

          val matchData = CsvRegExp.csvMatch.findAllMatchIn(l).toList

          matchData.length match {
            case 100 => tempData(100) = null; ()
            case 101 => ()
            case _ => {
              (0 to 100).foreach(tempData(_) = null)
              outputNbColsErrorsHandler.write(l + "\n")
            }
          }

          matchData.zipWithIndex.foreach{
            t => {
              value = t._1.subgroups match {
                case List(null, v) if v != "" => v
                case List(v, null) if v != "" => v
                case _ => null
              }

              value match {
                case null => ()
                case _ => stats(t._2) += 1
              }

             tempData(t._2) = value
            }
          }

          //validate line
          validateFields(Map(header.zip(tempData).toArray: _*)) match {
            case false => outputDataErrorsHandler.write(l + "\n")
            case _ => ()
          }



        }
      }
    }
  }

  header.zip(stats).foreach{
    t => println(t.productIterator.toList.mkString(""," - ", ""))
  }

  outputFormatErrorsHandler.close
  outputNbColsErrorsHandler.close
  outputDataErrorsHandler.close

}

