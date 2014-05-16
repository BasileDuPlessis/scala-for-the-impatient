package com.basile.scala.ch09

import scala.collection.mutable.ArrayBuffer

/**
 * Expand the example with the serializable Person class that stores a collection of friends.
 * Construct a few Person objects, make some of them friends of another, and then save an Array[Person] to a file.
 *
 * Read the array back in and verify that the friend relations are intact.
 */
object Ex10 extends App {


  @SerialVersionUID(42L) class Person(val name: String) extends Serializable {
    private val friends = new ArrayBuffer[Person]
    def addFriend(p: Person) {friends += p}
    def isFriend(p: Person) = friends.contains(p)
  }

  val paul = new Person("paul")
  val pierre = new Person("pierre")
  val jacques = new Person("jacques")

  paul.addFriend(pierre)
  pierre.addFriend(paul)
  jacques.addFriend(paul)
  jacques.addFriend(pierre)

  val persons = Array(paul, pierre, jacques)

  import java.io._
  val out = new ObjectOutputStream(new FileOutputStream("test.obj"))
  out.writeObject(persons)
  out.close()

  val in = new ObjectInputStream(new FileInputStream("test.obj"))
  val Array(paulA, pierreA, jacquesA) = in.readObject().asInstanceOf[Array[Person]]

  assert( jacquesA.isFriend(paulA) == true )
}
