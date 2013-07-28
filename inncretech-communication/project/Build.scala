import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "inncretech-communication"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
    javaCore,
    javaJdbc,
    javaEbean,
    "mysql" % "mysql-connector-java" % "5.1.18",
    "org.springframework" % "spring-context" % "3.2.0.RELEASE",
    "org.springframework" % "spring-beans" % "3.2.2.RELEASE",
    "org.springframework" % "spring-aspects" % "3.1.1.RELEASE",
    "c3p0" % "c3p0" % "0.9.1.2",
    "org.antlr" % "stringtemplate" % "4.0.2",
    "javax.mail" % "mail" % "1.4.6"
  )

  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here      
  )

}
