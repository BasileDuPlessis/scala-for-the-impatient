package com.basile.scala.ch21

import scala.language.implicitConversions

/**
 * Some people are fond of “fluent APIs” that read vaguely like English sentences.
 * Create such an API for reading integers, floating-point numbers, and strings from the console.
 * For example:
 * Read in aString askingFor "Your name" and anInt askingFor "Your age" and aDouble askingFor "Your weight" .
 */
object Ex04 extends App {

  object Read {
    private def readLine(text: String): String = Console.readLine(text + ": ")
    def readString(text: String): String = readLine(text)
    def readInt(text: String): Int = readLine(text).toInt
    def readDouble(text: String): Double = readLine(text).toDouble
  }

  abstract class aType
  object aString extends aType
  object anInt extends aType
  object aDouble extends aType

  type Reader = Read.type

  trait LoggerComponent {
    def log(msg:String) {println(msg)}
  }

  class FluentReader(reader: Reader) {
      this: LoggerComponent =>
        abstract class FluentAsk { def askingFor(name:String): Reader }

        def in(at: aType): FluentAsk = new FluentAsk {
          def askingFor(name:String): Reader = {

            val res = at match {
              case _:anInt.type => reader.readInt(name)
              case _:aDouble.type => reader.readDouble(name)
              case _ => reader.readString(name)
            }

            log(name + " -> " + res.toString)

            reader
          }
        }
        def and(at: aType): FluentAsk = in(at)
  }

  implicit def reader2FluentReader(reader: Reader): FluentReader = new FluentReader(reader) with LoggerComponent


  Read in aString askingFor "Your name" and anInt askingFor "Your age" and aDouble askingFor "Your weight"

}
