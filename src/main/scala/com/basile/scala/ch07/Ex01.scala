package com.basile.scala.ch07

/**
 * Write an example program to demonstrate that package com.horstmann.impatient is not the same as
 * package com
 *  package horstmann
 *    package impatient
 */
object Ex01 extends App {
}

package com.horstmann {
  object Horst {
    val name: String = "horstmann"
  }
}

package com {
  package horstmann {
    package impatient {
      class EmployeeA(val name: String) {
        println(Horst.name) //Horst is accessible
      }
    }
  }
}

package com.horstmann.impatient {
  class EmployeeB(val name: String) {
    //println(Horst.name) Horst is not accessible
  }
}
