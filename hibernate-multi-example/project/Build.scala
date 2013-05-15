import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "hibernate-multi-example"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
    javaCore,
    javaJdbc,
    javaEbean,
    "org.hibernate" % "hibernate-entitymanager" % "3.6.9.Final",
    "mysql" % "mysql-connector-java" % "5.1.18",
    "org.springframework" % "spring-context" % "3.2.0.RELEASE",
    "org.springframework" % "spring-beans" % "3.2.2.RELEASE",
    "org.springframework" % "spring-aspects" % "3.1.1.RELEASE",
    "org.springframework" % "spring-orm" % "3.2.0.RELEASE",
    "org.springframework" % "spring-aop" % "3.2.0.RELEASE",
    "aspectj" % "aspectjrt" % "1.5.4",
    "aspectj" % "aspectjweaver" % "1.5.4",
    "c3p0" % "c3p0" % "0.9.1.2"
  )

  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here      
  )

}
