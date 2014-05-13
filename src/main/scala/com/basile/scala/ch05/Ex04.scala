package com.basile.scala.ch05

/**
 * Reimplement the Time class from the preceding exercise so that the internal representation is the number of minutes
 * since midnight (between 0 and 24 × 60 – 1).
 *
 * Do not change the public interface. That is, client code should be unaffected by your change.
 */
object Ex04 extends App {

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

    private val _nmin = 60*_hrs + _min

    def hrs = _nmin/60
    def min = _nmin%60

    def before(other:Time) = _nmin < other._nmin
  }
  val T1 = new Time(2,70)
  val T2 = new Time(2,60)

  assert( T1.before(T2) == false )

}
