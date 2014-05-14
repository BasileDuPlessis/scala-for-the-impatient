package com.basile.scala.ch06

/**
 * The preceding problem wasn’t very object-oriented.
 * Provide a general superclass UnitConversion and define objects
 * InchesToCentimeters , GallonsToLiters , and MilesToKilometers that extend it.
 */
object Ex02 extends App {

  abstract class UnitConversion {
    def convert(value: Double): Double
  }

  object InchesToCentimeters extends UnitConversion {
    def convert(value: Double) = {
      value*2.54
    }
  }
  object GallonsToLiters extends UnitConversion {
    def convert(value: Double) = {
      value*3.78541178
    }
  }
  object MilesToKilometers extends UnitConversion {
    def convert(value: Double) = {
      value*1.609344
    }
  }

  assert( InchesToCentimeters.convert(2) == 5.08)

}
