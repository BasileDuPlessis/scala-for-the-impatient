package com.basile.scala.ch08

/**
 * Extend the BankAccount class of the preceding exercise into a class SavingsAccount that earns interest every month
 * (when a method earnMonthlyInterest is called) and has three free deposits or withdrawals every month.
 * Reset the transaction count in the earnMonthlyInterest method.
 */
object Ex02 extends App {

  class SavingsAccount(initialBalance: Double) extends Ex01.BankAccount(initialBalance) {

    var countDeposit = 0

    def earnMonthlyInterest(rate: Double = 0.01) {
      balance += balance * rate
      countDeposit = 0
    }

    def charge {
      countDeposit += 1
      if (countDeposit > 3) balance -= 1
    }
    override def deposit(amount: Double) = { charge; super.deposit(amount) }
    override def withdraw(amount: Double) = { charge; super.withdraw(amount) }

  }

  val MyAccount = new SavingsAccount(100)

  MyAccount.deposit(10)
  MyAccount.withdraw(20)
  MyAccount.deposit(10)

  assert( MyAccount.getBalance == 100.0)

  MyAccount.deposit(10)

  assert( MyAccount.getBalance == 109.0)

  MyAccount.earnMonthlyInterest()

  assert( MyAccount.getBalance == 110.09 )

  MyAccount.deposit(10)

  assert( MyAccount.getBalance == 120.09 )

}
