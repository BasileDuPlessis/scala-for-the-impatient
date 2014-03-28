package com.basile.scala.ch20

import javax.imageio.ImageIO
import ImageIO.{read => readImage, write => writeImage}
import scala.actors.Actor
import java.io.File
import java.awt.image.BufferedImage

/**
 * Write a program that reads in a large image into a BufferedImage , using javax.imageio.ImageIO.read .
 *  Use multiple actors, each of which inverts the colors in a strip of the image.
 *  When all strips have been inverted, write the result.
 */
object Ex02 extends App {

  // Divide a value in n parts, sum of parts is equal to value
  def divideInN(v: Int, n: Int): List[Int] = {
    //last value :: first n-1 values
    (v - (n-1) * v/n) :: (1 to n-1).foldLeft(List.empty[Int])( (l, i) => v/n :: l )
  }

  //Invert color in an image strip
  class InvertColorActor(ma: MergeImagePartActor, bi: BufferedImage) extends Actor {
    val w = bi.getWidth
    def act {
      while (true) {
        receive {
          case (y: Int, h: Int) => {
            bi.setRGB( 0, y, w, h, bi.getRGB(0, y, w, h, null, 0, w).map { 0xFFFFFF - _ }, 0, w )
            ma ! true
            exit()
          }
        }
      }
    }
  }

  //Write image when all strips inverted
  class MergeImagePartActor(bi: BufferedImage, n: Int) extends Actor {
    val now = System.nanoTime
    var countParts = 0
    def act {
      react {
        case true => {
          countParts += 1
          if (countParts < n) {act} else {
            println("%d milliseconds".format((System.nanoTime - now) / 1000000))
            writeImage(bi, "jpg", new File("""C:\dev\Ex02.jpg"""))
          }
        }
      }
    }
  }


  val img = getClass.getResource("/sample.jpg")
  val bi = readImage(img)
  val n = 4

  var startY = 0

  val myMergeActor = new MergeImagePartActor(bi, n)
  myMergeActor.start

  /* On core duo
  1400ms with 4 strips
  1600ms with 2 strips
  2200ms with 1 strips
   */
  divideInN(bi.getHeight, n).foreach {startH =>
    (new InvertColorActor(myMergeActor, bi)).start ! (startY, startH)
    startY += startH
  }







}
