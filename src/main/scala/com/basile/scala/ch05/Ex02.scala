package com.basile.scala.ch05

/**
 * Write a class BankAccount with methods deposit and withdraw, and a read-only property balance.
 */
object Ex02 extends App {

  class BankAccount(private var _balance:Double = 0.0) {

    def deposit(value:Int) {
      _balance += value
    }

    def withdraw(value:Int) {
      if (_balance - value > 0){
        _balance -= value
      }
    }

    def balance = _balance

  }

  val myBA = new BankAccount
  myBA.deposit(100)
  myBA.withdraw(20)

  assert( myBA.balance == 80.0 )

}
