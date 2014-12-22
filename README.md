Scala for the impatient answers
=======================

My solutions for "Scala for the Impatient" exercises


#### Table of Contents

The [AL][1-3] refer to Martin Odersky's Scala levels.

[The Basics (A1)](src/main/scala/com/basile/scala/ch01)

[Control Structures and Functions (A1)](src/main/scala/com/basile/scala/ch02)

[Arrays (A1)](src/main/scala/com/basile/scala/ch03)

[Maps and Tuples (A1)](src/main/scala/com/basile/scala/ch04)

[Classes (A1)](src/main/scala/com/basile/scala/ch05)

[Objects (A1)](src/main/scala/com/basile/scala/ch06)

[Packages and Imports (A1)](src/main/scala/com/basile/scala/ch07)

[Inheritance (A1)](src/main/scala/com/basile/scala/ch08)

[Files and Regular Expressions (A1))](src/main/scala/com/basile/scala/ch09)


##### Chapter 1

[The Basics (A1)](src/main/scala/com/basile/scala/ch01)

[1. In the Scala REPL, type 3. followed by the Tab key. What methods can be applied?](src/main/scala/com/basile/scala/ch01/Ex01.scala)

[2. In the Scala REPL, compute the square root of 3, and then square that value. By how much does the result differ from 3? (Hint: The res variables are your friend.)](src/main/scala/com/basile/scala/ch01/Ex02.scala)

[3. Are the res variables val or var ?](src/main/scala/com/basile/scala/ch01/Ex03.scala)

[4. Scala lets you multiply a string with a number—try out "crazy" * 3 in the REPL. What does this operation do? Where can you find it in Scaladoc?](src/main/scala/com/basile/scala/ch01/Ex04.scala)

[5. What does 10 max 2 mean? In which class is the max method defined?](src/main/scala/com/basile/scala/ch01/Ex05.scala)

[6. Using BigInt , compute 2 1024 .](src/main/scala/com/basile/scala/ch01/Ex06.scala)

[7. What do you need to import so that you can get a random prime as probablePrime(100, Random) , without any qualifiers before probablePrime and Random ?](src/main/scala/com/basile/scala/ch01/Ex07.scala)

[8. One way to create random file or directory names is to produce a random BigInt and convert it to base 36, yielding a string such as "qsnvbevtomcj38o06kul" . Poke around Scaladoc to find a way of doing this in Scala.](src/main/scala/com/basile/scala/ch01/Ex08.scala)

[9. How do you get the first character of a string in Scala? The last character?](src/main/scala/com/basile/scala/ch01/Ex09.scala)

[10. What do the take , drop , takeRight , and dropRight string functions do? What advantage or disadvantage do they have over using substring ?](src/main/scala/com/basile/scala/ch01/Ex10.scala)

##### Chapter 2

[Control Structures and Functions (A1)](src/main/scala/com/basile/scala/ch02)

[1. The signum of a number is 1 if the number is positive, –1 if it is negative, and 0 if it is zero. Write a function that computes this value.](src/main/scala/com/basile/scala/ch02/Ex01.scala)

[2. What is the value of an empty block expression {} ? What is its type?](src/main/scala/com/basile/scala/ch02/Ex02.scala)

[3. Come up with one situation where the assignment x = y = 1 is valid in Scala. (Hint: Pick a suitable type for x .)](src/main/scala/com/basile/scala/ch02/Ex03.scala)

[4. Write a Scala equivalent for the Java loop for (int i = 10; i >= 0; i--) System.out.println(i);](src/main/scala/com/basile/scala/ch02/Ex04.scala)

[5. Write a procedure countdown(n: Int) that prints the numbers from n to 0.](src/main/scala/com/basile/scala/ch02/Ex05.scala)

[6. Write a for loop for computing the product of the Unicode codes of all letters in a string. For example, the product of the characters in "Hello" is 9415087488L .](src/main/scala/com/basile/scala/ch02/Ex06.scala)

[7. Solve the preceding exercise without writing a loop. (Hint: Look at the StringOps Scaladoc.)](src/main/scala/com/basile/scala/ch02/Ex07.scala)

[8. Write a function product(s : String) that computes the product, as described in the preceding exercises.](src/main/scala/com/basile/scala/ch02/Ex08.scala)

[9. Make the function of the preceding exercise a recursive function.](src/main/scala/com/basile/scala/ch02/Ex09.scala)

[10. Write a function that computes x n , where n is an integer. Use the following recursive definition: • x n = y 2 if n is even and positive, where y = x n / 2 . • x n = x· x n – 1 if n is odd and positive. • x 0 = 1. • x n = 1 / x – n if n is negative. Don’t use a return statement.](src/main/scala/com/basile/scala/ch02/Ex10.scala)

##### Chapter 3

[Arrays (A1)](src/main/scala/com/basile/scala/ch03)

[1. Write a code snippet that sets a to an array of n random integers between 0 (inclusive) and n (exclusive).](src/main/scala/com/basile/scala/ch03/Ex01.scala)

[2. Write a loop that swaps adjacent elements of an array of integers. For example, Array(1, 2, 3, 4, 5) becomes Array(2, 1, 4, 3, 5) .](src/main/scala/com/basile/scala/ch03/Ex02.scala)

[3. Repeat the preceding assignment, but produce a new array with the swapped values. Use for / yield .](src/main/scala/com/basile/scala/ch03/Ex03.scala)

[4. Given an array of integers, produce a new array that contains all positive values of the original array, in their original order, followed by all values that are zero or negative, in their original order.](src/main/scala/com/basile/scala/ch03/Ex04.scala)

[5. How do you compute the average of an Array\[Double\] ?](src/main/scala/com/basile/scala/ch03/Ex05.scala)

[6. How do you rearrange the elements of an Array\[Int\] so that they appear in reverse sorted order? How do you do the same with an ArrayBuffer\[Int\] ?](src/main/scala/com/basile/scala/ch03/Ex06.scala)

[7. Write a code snippet that produces all values from an array with duplicates removed. (Hint: Look at Scaladoc.)](src/main/scala/com/basile/scala/ch03/Ex07.scala)

[8. Rewrite the example at the end of Section 3.4 , “ Transforming Arrays ,” on page 32 . Collect indexes of the negative elements, reverse the sequence, drop the last index, and call a.remove(i) for each index. Compare the efficiency of this approach with the two approaches in Section 3.4 .](src/main/scala/com/basile/scala/ch03/Ex08.scala)

[9. Make a collection of all time zones returned by java.util.TimeZone.getAvailableIDs that are in America. Strip off the "America/" prefix and sort the result.](src/main/scala/com/basile/scala/ch03/Ex09.scala)

[10. Import java.awt.datatransfer._ and make an object of type SystemFlavorMap with the call Click here to view code image val flavors = SystemFlavorMap.getDefaultFlavorMap().asInstanceOf\[SystemFlavorMap\] Then call the getNativesForFlavor method with parameter DataFlavor.imageFlavor and get the return value as a Scala buffer. (Why this obscure class? It’s hard to find uses of java.util.List in the standard Java library.)](src/main/scala/com/basile/scala/ch03/Ex10.scala)

##### Chapter 4

[Maps and Tuples (A1)](src/main/scala/com/basile/scala/ch04)

[1. Set up a map of prices for a number of gizmos that you covet. Then produce a second map with the same keys and the prices at a 10 percent discount.](src/main/scala/com/basile/scala/ch04/Ex01.scala)

[2. Write a program that reads words from a file. Use a mutable map to count how often each word appears. To read the words, simply use a java.util.Scanner : val in = new java.util.Scanner(new java.io.File("myfile.txt")) while (in.hasNext()) process in.next() Or look at Chapter 9 for a Scalaesque way. At the end, print out all words and their counts.](src/main/scala/com/basile/scala/ch04/Ex02.scala)

[3. Repeat the preceding exercise with an immutable map.](src/main/scala/com/basile/scala/ch04/Ex03.scala)

[4. Repeat the preceding exercise with a sorted map, so that the words are printed in sorted order.](src/main/scala/com/basile/scala/ch04/Ex04.scala)

[5. Repeat the preceding exercise with a java.util.TreeMap that you adapt to the Scala API.](src/main/scala/com/basile/scala/ch04/Ex05.scala)

[6. Define a linked hash map that maps "Monday" to java.util.Calendar.MONDAY , and similarly for the other weekdays. Demonstrate that the elements are visited in insertion order.](src/main/scala/com/basile/scala/ch04/Ex06.scala)

[7. Print a table of all Java properties, like this: java.runtime.name | Java(TM) SE Runtime Environment sun.boot.library.path | /home/apps/jdk1.6.0_21/jre/lib/i386 java.vm.version | 17.0-b16 java.vm.vendor | Sun Microsystems Inc. java.vendor.url | http://java.sun.com/ path.separator | : java.vm.name | Java HotSpot(TM) Server VM You need to find the length of the longest key before you can print the table.](src/main/scala/com/basile/scala/ch04/Ex07.scala)

[8. Write a function minmax(values: Array\[Int\]) that returns a pair containing the smallest and largest values in the array.](src/main/scala/com/basile/scala/ch04/Ex08.scala)

[9. Write a function lteqgt(values: Array\[Int\], v: Int) that returns a triple containing the counts of values less than v , equal to v , and greater than v .](src/main/scala/com/basile/scala/ch04/Ex09.scala)

[10. What happens when you zip together two strings, such as "Hello".zip("World") ? Come up with a plausible use case.](src/main/scala/com/basile/scala/ch04/Ex10.scala)


##### Chapter 5

[Classes (A1)](src/main/scala/com/basile/scala/ch05)

[1. Improve the Counter class in Section 5.1 , “Simple Classes and Parameterless Methods,” on page 49 so that it doesn’t turn negative at Int.MaxValue .](src/main/scala/com/basile/scala/ch05/Ex01.scala)

[2. Write a class BankAccount with methods deposit and withdraw , and a read-only property balance .](src/main/scala/com/basile/scala/ch05/Ex02.scala)

[3. Write a class Time with read-only properties hours and minutes and a method before(other: Time): Boolean that checks whether this time comes before the other. A Time object should be constructed as new Time(hrs, min) , where hrs is in military time format (between 0 and 23).](src/main/scala/com/basile/scala/ch05/Ex03.scala)

[4. Reimplement the Time class from the preceding exercise so that the internal representation is the number of minutes since midnight (between 0 and 24 × 60 – 1). Do not change the public interface. That is, client code should be unaffected by your change.](src/main/scala/com/basile/scala/ch05/Ex04.scala)

[5. Make a class Student with read-write JavaBeans properties name (of type String ) and id (of type Long ). What methods are generated? (Use javap to check.) Can you call the JavaBeans getters and setters in Scala Should you?](src/main/scala/com/basile/scala/ch05/Ex05.scala)

[6. In the Person class of Section 5.1 , “Simple Classes and Parameterless Methods,” on page 49 , provide a primary constructor that turns negative ages to 0.](src/main/scala/com/basile/scala/ch05/Ex06.scala)

[7. Write a class Person with a primary constructor that accepts a string containing a first name, a space, and a last name, such as new Person("Fred Smith") . Supply read-only properties firstName and lastName . Should the primary constructor parameter be a var , a val , or a plain parameter? Why?](src/main/scala/com/basile/scala/ch05/Ex07.scala)

[8. Make a class Car with read-only properties for manufacturer, model name, and model year, and a read-write property for the license plate. Supply four constructors. All require the manufacturer and model name. Optionally, model year and license plate can also be specified in the constructor. If not, the model year is set to -1 and the license plate to the empty string. Which constructor are you choosing as the primary constructor? Why?](src/main/scala/com/basile/scala/ch05/Ex08.scala)

[9. Reimplement the class of the preceding exercise in Java, C#, or C++ (your choice). How much shorter is the Scala class?](src/main/scala/com/basile/scala/ch05/Ex09.scala)

[10. Consider the class class Employee(val name: String, var salary: Double) { def this() { this("John Q. Public", 0.0) } } Rewrite it to use explicit fields and a default primary constructor. Which form do you prefer? Why?](src/main/scala/com/basile/scala/ch05/Ex10.scala)


##### Chapter 6

[Objects (A1)](src/main/scala/com/basile/scala/ch06)

[1. Write an object Conversions with methods inchesToCentimeters , gallonsToLiters , and milesToKilometers .](src/main/scala/com/basile/scala/ch06/Ex01.scala)

[2. The preceding problem wasn’t very object-oriented. Provide a general superclass UnitConversion and define objects InchesToCentimeters , GallonsToLiters , and MilesToKilometers that extend it.](src/main/scala/com/basile/scala/ch06/Ex02.scala)

[3. Define an Origin object that extends java.awt.Point . Why is this not actually a good idea? (Have a close look at the methods of the Point class.)](src/main/scala/com/basile/scala/ch06/Ex03.scala)

[4. Define a Point class with a companion object so that you can construct Point instances as Point(3, 4) , without using new .](src/main/scala/com/basile/scala/ch06/Ex04.scala)

[5. Write a Scala application, using the App trait, that prints the command-line arguments in reverse order, separated by spaces. For example, scala Reverse Hello World should print World Hello .](src/main/scala/com/basile/scala/ch06/Ex05.scala)

[6. Write an enumeration describing the four playing card suits so that the toString method returns ♣, ♦, ♥, or ♠.](src/main/scala/com/basile/scala/ch06/Ex06.scala)

[7. Implement a function that checks whether a card suit value from the preceding exercise is red.](src/main/scala/com/basile/scala/ch06/Ex07.scala)

[8. Write an enumeration describing the eight corners of the RGB color cube. As IDs, use the color values (for example, 0xff0000 for Red ).](src/main/scala/com/basile/scala/ch06/Ex08.scala)

##### Chapter 7

[Packages and Imports (A1)](src/main/scala/com/basile/scala/ch07)

[1. Write an example program to demonstrate that package com.horstmann.impatient is not the same as package com package horstmann package impatient](src/main/scala/com/basile/scala/ch07/Ex01.scala)

[2. Write a puzzler that baffles your Scala friends, using a package com that isn’t at the top level.](src/main/scala/com/basile/scala/ch07/Ex02.scala)

[3. Write a package random with functions nextInt(): Int , nextDouble(): Double , and setSeed(seed: Int): Unit . To generate random numbers, use the linear congruential generator next = (previous × a + b ) mod 2 n , where a = 1664525, b = 1013904223, n = 32, and the inital value of previous is seed .](src/main/scala/com/basile/scala/ch07/Ex03.scala)

[4. Why do you think the Scala language designers provided the package object syntax instead of simply letting you add functions and variables to a package?](src/main/scala/com/basile/scala/ch07/Ex04.scala)

[5. What is the meaning of private\[com\] def giveRaise(rate: Double) ? Is it useful?](src/main/scala/com/basile/scala/ch07/Ex05.scala)

[6. Write a program that copies all elements from a Java hash map into a Scala hash map. Use imports to rename both classes.](src/main/scala/com/basile/scala/ch07/Ex06.scala)

[7. In the preceding exercise, move all imports into the innermost scope possible.](src/main/scala/com/basile/scala/ch07/Ex07.scala)

[8. What is the effect of import java._ import javax._ Is this a good idea?](src/main/scala/com/basile/scala/ch07/Ex08.scala)

[9. Write a program that imports the java.lang.System class, reads the user name from the user.name system property, reads a password from the Console object, and prints a message to the standard error stream if the password is not "secret" . Otherwise, print a greeting to the standard output stream. Do not use any other imports, and do not use any qualified names (with dots).](src/main/scala/com/basile/scala/ch07/Ex09.scala)

[10. Apart from StringBuilder , what other members of java.lang does the scala package override?](src/main/scala/com/basile/scala/ch07/Ex10.scala)

##### Chapter 8

[Inheritance (A1)](src/main/scala/com/basile/scala/ch08)

[Extend the following BankAccount class to a CheckingAccount class that charges $1 for every deposit and withdrawal.](src/main/scala/com/basile/scala/ch08/Ex01.scala)

[Extend the BankAccount class of the preceding exercise into a class SavingsAccount that earns interest every month (when a method earnMonthlyInterest is called) and has three free deposits or withdrawals every month. Reset the transaction count in the earnMonthlyInterest method.](src/main/scala/com/basile/scala/ch08/Ex02.scala)

[Consult your favorite Java or C++ textbook that is sure to have an example of a toy inheritance hierarchy, perhaps involving employees, pets, graphical shapes, or the like.](src/main/scala/com/basile/scala/ch08/Ex03.scala)

[Define an abstract class Item with methods price and description . A SimpleItem is an item whose price and description are specified in the constructor. Take advantage of the fact that a val can override a def . A Bundle is an item that contains other items. Its price is the sum of the prices in the bundle. Also provide a mechanism for adding items to the bundle and a suitable description method.](src/main/scala/com/basile/scala/ch08/Ex04.scala)

[Design a class Point whose x and y coordinate values can be provided in a constructor. Provide a subclass LabeledPoint whose constructor takes a label value and x and y coordinates, such as new LabeledPoint("Black Thursday", 1929, 230.07)](src/main/scala/com/basile/scala/ch08/Ex05.scala)

[Define an abstract class Shape with an abstract method centerPoint and subclasses Rectangle and Circle . Provide appropriate constructors for the subclasses and override the centerPoint method in each subclass.](src/main/scala/com/basile/scala/ch08/Ex06.scala)

[Provide a class Square that extends java.awt.Rectangle and has three constructors: one that constructs a square with a that constructs a square with corner (0, 0) and width 0 .](src/main/scala/com/basile/scala/ch08/Ex07.scala)

[Compile the Person and SecretAgent classes in Section 8.6 , “ Overriding Fields ,” on page 89 and analyze the class files with javap . How many name fields are there? How many name getter methods are there? What do they get? (Hint: Use the -c and -private options.)](src/main/scala/com/basile/scala/ch08/Ex08.scala)

[In the Creature class of Section 8.10 , “ Construction Order and Early Definitions ,” on page 92 , replace val range with a def . What happens when you also use a def in the Ant subclass? What happens when you use a val in the subclass? Why?](src/main/scala/com/basile/scala/ch08/Ex09.scala)

[The file scala/collection/immutable/Stack.scala contains the definition class Stack\[A\] protected (protected val elems: List\[A\]) Explain the meanings of the protected keywords. (Hint: Review the discussion of private constructors in Chapter 5.)](src/main/scala/com/basile/scala/ch08/Ex10.scala)

##### Chapter 9

[Files and Regular Expressions (A1))](src/main/scala/com/basile/scala/ch09)

[Write a Scala code snippet that reverses the lines in a file (making the last line the first one, and so on).](src/main/scala/com/basile/scala/ch09/Ex01.scala)

[Write a Scala program that reads a file with tabs, replaces each tab with spaces so that tab stops are at n -column boundaries, and writes the result to the same file.](src/main/scala/com/basile/scala/ch09/Ex02.scala)

[Write a Scala code snippet that reads a file and prints all words with more than 12 characters to the console. Extra credit if you can do this in a single line.](src/main/scala/com/basile/scala/ch09/Ex03.scala)

[Write a Scala program that reads a text file containing average, maximum, and minimum of the numbers in the file.](src/main/scala/com/basile/scala/ch09/Ex04.scala)

[Write a Scala program that writes the powers of 2 and their reciprocals to a file, with the exponent ranging from 0 to 20. Line up the columns: 1 1 2 0.5 4 0.25 ... ...](src/main/scala/com/basile/scala/ch09/Ex05.scala)

[Make a regular expression searching for quoted strings "like this, maybe with \" or \\" in a Java or C++ program. Write a Scala program that prints out all such strings in a source file.](src/main/scala/com/basile/scala/ch09/Ex06.scala)

[Write a Scala program that reads a text file and prints all tokens in the file that are not floating-point numbers. Use a regular expression.](src/main/scala/com/basile/scala/ch09/Ex07.scala)

[ Write a Scala program that prints the src attributes of all img tags of a web page. Use regular expressions and groups.](src/main/scala/com/basile/scala/ch09/Ex08.scala)

[Write a Scala program that counts how many files with .class extension are in a given directory and its subdirectories.](src/main/scala/com/basile/scala/ch09/Ex09.scala)

[Expand the example with the serializable Person class that stores a collection of friends. Construct a few Person objects, make some of them friends of another, and then save an Array\[Person\] to a file. Read the array back in and verify that the friend relations are intact.](src/main/scala/com/basile/scala/ch09/Ex10.scala)


##### Chapter 10

[Traits (L1)](src/main/scala/com/basile/scala/ch10)

[](src/main/scala/com/basile/scala/ch10/Ex.scala)
[](src/main/scala/com/basile/scala/ch10/Ex.scala)
[](src/main/scala/com/basile/scala/ch10/Ex.scala)
[](src/main/scala/com/basile/scala/ch10/Ex.scala)

https://github.com/BasileDuPlessis/scala-for-the-impatient/tree/master/src/main/scala/com/basile/scala/ch10

##### Operators (L1)

https://github.com/BasileDuPlessis/scala-for-the-impatient/tree/master/src/main/scala/com/basile/scala/ch11

##### Higher-Order Functions (L1)

https://github.com/BasileDuPlessis/scala-for-the-impatient/tree/master/src/main/scala/com/basile/scala/ch12

##### Collections (A2)

https://github.com/BasileDuPlessis/scala-for-the-impatient/tree/master/src/main/scala/com/basile/scala/ch13

##### Pattern Matching and Case Classes (A2)

https://github.com/BasileDuPlessis/scala-for-the-impatient/tree/master/src/main/scala/com/basile/scala/ch14

##### Annotations (A2)

https://github.com/BasileDuPlessis/scala-for-the-impatient/tree/master/src/main/scala/com/basile/scala/ch15

##### XML Processing (A2)

https://github.com/BasileDuPlessis/scala-for-the-impatient/tree/master/src/main/scala/com/basile/scala/ch16

##### Type Parameters (L2)

https://github.com/BasileDuPlessis/scala-for-the-impatient/tree/master/src/main/scala/com/basile/scala/ch17

##### Advanced Types  (L2)

https://github.com/BasileDuPlessis/scala-for-the-impatient/tree/master/src/main/scala/com/basile/scala/ch18

##### Parsing and Domain-Specific Languages (A3)

https://github.com/BasileDuPlessis/scala-for-the-impatient/tree/master/src/main/scala/com/basile/scala/ch19

##### Actors (A3)

https://github.com/BasileDuPlessis/scala-for-the-impatient/tree/master/src/main/scala/com/basile/scala/ch20

##### Implicits (L3)

https://github.com/BasileDuPlessis/scala-for-the-impatient/tree/master/src/main/scala/com/basile/scala/ch21

##### Delimited Continuations (L3)

https://github.com/BasileDuPlessis/scala-for-the-impatient/tree/master/src/main/scala/com/basile/scala/ch22
