package com.basile.scala.ch08

/**
 * Extend the following BankAccount class to a CheckingAccount class that charges $1 for every deposit
 * and withdrawal.
 *
 * class BankAccount(initialBalance: Double) {
 *  private var balance = initialBalance
 *  def currentBalance = balance
 *  def deposit(amount: Double) = { balance += amount; balance }
 *  def withdraw(amount: Double) = { balance -= amount; balance }
 *  }
 *
 */
object Ex01 extends App {

  class BankAccount(initialBalance: Double) {
    protected var balance = initialBalance
    def deposit(amount: Double) = { balance += amount; balance }
    def withdraw(amount: Double) = { balance -= amount; balance }
    def getBalance = balance
  }

  class CheckingAccount(initialBalance: Double) extends BankAccount(initialBalance) {
    def charge {balance -= 1}
    override def deposit(amount: Double) = { charge; super.deposit(amount) }
    override def withdraw(amount: Double) = { charge; super.withdraw(amount) }
  }

  val MyAccount = new CheckingAccount(100)

  MyAccount.deposit(10)

  assert( MyAccount.getBalance == 109.0 )

}
