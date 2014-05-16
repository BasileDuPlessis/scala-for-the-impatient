package com.basile.scala.ch10

import java.beans.{PropertyChangeSupport, PropertyChangeListener, PropertyChangeEvent}
import java.awt.Point

/**
 * The JavaBeans specification has the notion of a property change listener , a standardized way for beans
 * to communicate changes in their properties.
 *
 * The PropertyChangeSupport class is provided as a convenience superclass for any bean that wishes to support
 * property change listeners.
 *
 * Unfortunately, a class that already has another superclass—such as JComponent —must reimplement the methods.
 *
 * Reimplement PropertyChangeSupport as a trait, and mix it into the java.awt.Point class.
 */
object Ex05 extends App {

  trait ScalaPropertyChangeSupport {
    val pcs = new PropertyChangeSupport(this);

    def addPropertyChangeListener(propertyName: String, listener: PropertyChangeListener) {
      pcs.addPropertyChangeListener(propertyName, listener);
    }
    def removePropertyChangeListener(listener: PropertyChangeListener) {
      pcs.removePropertyChangeListener(listener);
    }
  }

  class Listener extends PropertyChangeListener {
    def propertyChange(evt: PropertyChangeEvent) {
      println(
        evt.getSource.asInstanceOf[Point].x
      )
    }
  }

  trait ListenedPoint extends Point {
    this: ScalaPropertyChangeSupport =>
      override def setLocation(x: Int, y: Int) {
        pcs.firePropertyChange("setLocation", (getX, getY), (x, y))
        super.setLocation(x, y)
      }
  }

  val L = new Listener

  val P = new Point(4,4) with ListenedPoint with ScalaPropertyChangeSupport

  P.addPropertyChangeListener("setLocation", L)

  P.setLocation(5, 6)
}
