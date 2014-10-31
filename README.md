Scala for the impatient answers
=======================

My solutions for "Scala for the Impatient" exercises


#### Table of Contents

The [AL][1-3] refer to Martin Odersky's Scala levels.

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

##### Maps and Tuples (A1)

https://github.com/BasileDuPlessis/scala-for-the-impatient/tree/master/src/main/scala/com/basile/scala/ch04

##### Classes (A1)

https://github.com/BasileDuPlessis/scala-for-the-impatient/tree/master/src/main/scala/com/basile/scala/ch05

##### Objects (A1)

https://github.com/BasileDuPlessis/scala-for-the-impatient/tree/master/src/main/scala/com/basile/scala/ch06

##### Packages and Imports (A1)

https://github.com/BasileDuPlessis/scala-for-the-impatient/tree/master/src/main/scala/com/basile/scala/ch07

##### Inheritance (A1)

https://github.com/BasileDuPlessis/scala-for-the-impatient/tree/master/src/main/scala/com/basile/scala/ch08

##### Files and Regular Expressions (A1)

https://github.com/BasileDuPlessis/scala-for-the-impatient/tree/master/src/main/scala/com/basile/scala/ch09

##### Traits (L1)

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
