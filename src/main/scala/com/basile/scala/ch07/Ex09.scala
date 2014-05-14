package com.basile.scala.ch07

import java.lang.System._

/**
 * Write a program that imports the java.lang.System class, reads the user name from the user.name system property,
 * reads a password from the Console object, and prints a message to the standard error stream
 * if the password is not "secret" .
 *
 * Otherwise, print a greeting to the standard output stream.
 * Do not use any other imports, and do not use any qualified names (with dots).
 */
object Ex09 extends App {

  val userName = getProperties.getProperty("user.name")

  if (args.length > 0 && args(0) == "secret")
    println("Welcome " + userName)
  else
    println("Wrong password")

}
