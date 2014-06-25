package com.basile.di

/**
 * http://stackoverflow.com/questions/11276319/using-reader-monad-for-dependency-injection
 */
object Ex01 extends App {

  trait FlyBehaviour { def fly() }
  trait QuackBehaviour { def quack() }
  trait Animal { def makeSound() }

  trait Config {
    def fly: FlyBehaviour
    def quack: QuackBehaviour
  }

  class Duck(f: FlyBehaviour, q: QuackBehaviour) extends Animal {
    def makeSound {
      q.quack()
    }
  }

  class FlyFast extends FlyBehaviour {
    def fly {println("Fly fast")}
  }

  class QuackLoud extends QuackBehaviour {
    def quack {println("Quack loud")}
  }

  class Zoo(val animal: Animal)

  type Env[A] = Config => A

  val a: Env[Animal] = c => new Duck(c.fly, c.quack)

  val TextConf = new Config {
    def fly = new FlyFast
    def quack = new QuackLoud
  }

  val z: Env[Zoo] = a andThen (new Zoo(_))

  z(TextConf).animal.makeSound()



}
