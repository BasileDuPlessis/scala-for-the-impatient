package com.basile.scala.ch05

/**
 * Make a class Car with read-only properties for manufacturer, model name, and model year, and a read-write property
 * for the license plate.
 *
 * Supply four constructors. All require the manufacturer and model name. Optionally, model year and license plate
 * can also be specified in the constructor. If not, the model year is set to -1 and the license plate
 * to the empty string. Which constructor? Why?
 */
object Ex08 extends App {


  class Car(val manufacturer: String, val modelName: String) {

    var licencePlate: String = ""
    private var _modelYear: Int = -1

    def this(manufacturer: String, modelName: String, modelYear: Int) {
      this(manufacturer, modelName)
      this._modelYear = modelYear
    }

    def this(manufacturer: String, modelName: String, licencePlate: String) {
      this(manufacturer, modelName)
      this.licencePlate = licencePlate
    }

    def this(manufacturer: String, modelName: String, modelYear: Int, licencePlate: String) {
      this(manufacturer, modelName)
      this._modelYear = modelYear
      this.licencePlate = licencePlate
    }

    def modelYear = _modelYear
  }

  val Twingo = new Car("Renault", "Twingo")
  val Twingo2008 = new Car("Renault", "Twingo", 2008)
  val Twingo586QW27 = new Car("Renault", "Twingo", "586 QW 27")



}
