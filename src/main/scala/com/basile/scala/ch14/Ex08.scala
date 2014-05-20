package com.basile.scala.ch14

/**
 * Extend the tree in the preceding exercise so that each non-leaf node stores an operator in addition to the child nodes.
 * Then write a function eval that computes the value.
 *
 * For example, the tree + /|\ * 2 - / \ | 3 8 5 has value (3 × 8) + 2 + (–5) = 21.
 */
object Ex08 extends App {

  sealed abstract class BinaryTree
  case class Leaf(value: Int) extends BinaryTree
  case class Node(operator: Char, bt: BinaryTree*) extends BinaryTree

  val n = Node('+', Node('*', Leaf(3), Leaf(8)), Leaf(2), Node('-', Leaf(5)))

  def eval(bt: BinaryTree): Int = bt match {
    case Node('+', bts @ _*) => bts.map(eval).sum
    case Node('-', bts @ _*) => bts.map(eval).foldLeft(0)(_ - _)
    case Node('*', bts @ _*) => bts.map(eval).product
    case Leaf(value) => value
    case _ => 0
  }

  assert( eval(n) == 21)


}
