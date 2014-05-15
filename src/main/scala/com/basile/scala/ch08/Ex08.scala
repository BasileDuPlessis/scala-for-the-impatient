package com.basile.scala.ch08

/**
 * Compile the Person and SecretAgent classes in Section 8.6 , “ Overriding Fields ,” on page 89 and analyze
 * the class files with javap .
 *
 * How many name fields are there? How many name getter methods are there?
 *
 * What do they get? (Hint: Use the -c and -private options.)
 */
object Ex08 extends App {

  /*
  public class Person {
    private final java.lang.String name;
    public java.lang.String name();
    public java.lang.String toString();
    public Person(java.lang.String);
  }
   */
  class Person(val name: String) {
    override def toString = getClass.getName + "[name=" + name + "]"
  }

  /*
  public class SecretAgent extends Person {
    public java.lang.String name();
    public java.lang.String toString();
    public SecretAgent(java.lang.String);
  }
   */
  class SecretAgent(codename: String) extends Person(codename) {
    override val name = "secret"
    override val toString = "secret"
  }

}
