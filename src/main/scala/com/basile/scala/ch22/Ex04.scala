package com.basile.scala.ch22

import java.awt._
import java.awt.event._
import javax.swing._
import scala.util.continuations._

/**
 * The example in Section 22.8 , “ Undoing Inversion of Control ,” on page 329 is a bit unsightly—the application
 * programmer sees the reset statement.
 * Move reset from the run method to the button listener.
 * Is the application programmer now blissfully unaware of continuations?
 */
object Ex04 extends App {

  var cont: Unit => Unit = null
  var callNumber: Int =0

  val frame = new JFrame
  val button = new JButton("Next")

  setListener(button) { reset[Unit, Unit] { run() } }

  val textField = new JTextArea(10, 40)
  textField.setEnabled(true)

  val label = new JLabel("Welcome to the demo app")

  frame.add(label, BorderLayout.NORTH)
  frame.add(textField)

  val panel = new JPanel
  panel.add(button)
  frame.add(panel, BorderLayout.SOUTH)
  frame.pack()
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
  frame.setVisible(true)


  def run(): Unit @cpsParam[Unit, Unit] = {
    val response1 = getResponse("What is your first name?")
    val response2 = getResponse("What is your last name?")
    process(response1, response2)
  }

  def process(s1: String, s2: String) { label.setText("Hello, " + s1 + " " + s2) }


  def getResponse(prompt: String): String @cpsParam[Unit, Unit] = {
    label.setText(prompt)
    textField.setEnabled(true)
    setListener(button) { cont() }
    shift[Unit, Unit, Unit] {
      k: (Unit => Unit) => { cont = k }
    }
    setListener(button) { }
    textField.setEnabled(false)
    val text = textField.getText
    textField.setText(null)
    text
  }


  def setListener(button: JButton)(action:  => Unit) {
    for (l <- button.getActionListeners) button.removeActionListener(l)

    button.addActionListener(
      new ActionListener { override def actionPerformed(event: ActionEvent) { action } }
    )

  }

  // Unfortunatelly Application programmer should be aware of continuation because of def run(): Unit @cpsParam[Unit, Unit]



}
