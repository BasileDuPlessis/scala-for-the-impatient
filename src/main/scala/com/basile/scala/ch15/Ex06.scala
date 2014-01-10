package com.basile.scala.ch15


/**
 * Created by basile.duplessis on 10/01/14.
 */
object Ex06 extends App {

  //@volatile var status = false
  var status = false

  new Thread {
    override def run() {
      Thread.sleep(200)
      status = true
      println("## Status field to true")
    }
  }.start()

  new Thread {
    override def run() {
      while (!status) {
       println("Status field is false")
       Thread.sleep(10)
      }
      println("Status field is true")
    }
  }.start()
}
