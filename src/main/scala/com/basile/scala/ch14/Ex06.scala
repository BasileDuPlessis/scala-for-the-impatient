package com.basile.scala.ch14

/**
 * A better way of modeling such trees is with case classes. Let’s start with binary trees.
 * sealed abstract class BinaryTree case class Leaf(value: Int) extends BinaryTree
 * case class Node(left: BinaryTree, right: BinaryTree) extends BinaryTree
 *
 * Write a function to compute the sum of all elements in the leaves.
 */
object Ex06 extends App {

  sealed abstract class BinaryTree
  case class Leaf(value: Int) extends BinaryTree
  case class Node(left: BinaryTree, right: BinaryTree) extends BinaryTree


  val n = Node(Node(Leaf(3), Leaf(8)), Leaf(5))

  def leafSum(bt: BinaryTree): Int = bt match {
    case Node(left, right) => leafSum(left) + leafSum(right)
    case Leaf(value) => value
  }

  assert( leafSum(n) == 16 )

}
