package com.basile.scala.ch05

/**
 * Write a class Time with read-only properties hours and minutes and a method before(other: Time):
 * Boolean that checks whether this time comes before the other.
 *
 * A Time object should be constructed as new Time(hrs, min), where hrs is in military time format (between 0 and 23).
 */
object Ex03 extends App {

  class Time(private var _hrs:Int, private var _min:Int) {
    _min = _min match {
      case i:Int if i%60==0 => {_hrs += i/60; 0}
      case i:Int if i<0 => {_hrs += (i/60 - 1); 60 + i%60}
      case i:Int if i>59 => {_hrs += i/60; i%60}
      case _ => _min
    }
    _hrs = _hrs match {
      case i:Int if i<0 => 24 + i%24
      case i:Int if i>23 => i%24
      case _ => _hrs
    }

    def hrs = _hrs
    def min = _min
    def before(other:Time) = {
      _hrs < other._hrs || (other._hrs == _hrs && _min < other._min)
    }
  }

  val T1 = new Time(-1,70)
  val T2 = new Time(2,-59)

  assert( T1.before(T2) == true )

}
