name := "scala"

version := "1.0"

libraryDependencies += "org.scalatest" % "scalatest_2.10" % "2.0" % "test"

libraryDependencies ++= Seq(
  "junit" % "junit" % "4.11" % "test",
  "com.novocode" % "junit-interface" % "0.10" % "test"
)
    