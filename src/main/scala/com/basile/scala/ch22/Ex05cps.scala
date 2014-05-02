package com.basile.scala.ch22

/**
 *
 */

/*
object Ex05 extends AnyRef with App {
  def <init>(): com.basile.scala.ch22.Ex05.type = {
    Ex05.super.<init>();
    ()
    };

    private[this] var cont: Unit => String = null;
    <accessor> def cont: Unit => String = Ex05.this.cont;
    <accessor> def cont_=(x$1: Unit => String): Unit = Ex05.this.cont = x$1;

    private[this] val a: Array[String] = "Mary was a little lamb".split(" ");

    <stable> <accessor> def a: Array[String] = Ex05.this.a;

    scala.util.continuations.`package`.reset[String, String]({
      var i: Int = 0;
      def while$1(): scala.util.continuations.ControlContext[Unit,String,String] = if (i.<(Ex05.this.a.length))
        {
          val tmp2$shift: scala.util.continuations.ControlContext[Unit,String,String] = {
            `package`.this.shiftR[Unit, String, String](((k: Unit => String) => {
              Ex05.this.cont_=(k);
              Ex05.this.a.apply(i)
              })).map[Unit](((tmp1: Unit) => {
                tmp1;
                i = i.+(1)
              }))
            };

          if (tmp2$shift.isTrivial)
            {
              val tmp2: Unit = tmp2$shift.getTrivialValue;
              tmp2;
              while$1()
            }
          else
            tmp2$shift.flatMap[Unit, String, String](((tmp2: Unit) => {
              tmp2;
              while$1()
            }))
          }
          else
            `package`.this.shiftUnitR[Unit, String](());
          while$1().map[String](((tmp3: Unit) => {
            tmp3;
            ""
          }))
          });

      scala.this.Predef.println(Ex05.this.cont.apply(()));
      scala.this.Predef.println(Ex05.this.cont.apply(()))
  }
}

*/














































