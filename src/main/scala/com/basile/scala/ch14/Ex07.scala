package com.basile.scala.ch14

/**
 * Extend the tree in the preceding exercise so that each node can have an arbitrary number of children, and reimplement
 * the leafSum function.
 *
 * The tree in exercise 5 should be expressible as Node(Node(Leaf(3), Leaf(8)), Leaf(2), Node(Leaf(5)))
 */
object Ex07 extends App {

  sealed abstract class BinaryTree
  case class Leaf(value: Int) extends BinaryTree
  case class Node(bt: BinaryTree*) extends BinaryTree


  val n = Node(Node(Leaf(3), Leaf(8)), Leaf(2), Node(Leaf(5)))

  def leafSum(bt: BinaryTree): Int = bt match {
    case Node(bts @ _*) => bts.map(leafSum).sum
    case Leaf(value) => value
  }

  assert( leafSum(n) == 18 )

}
