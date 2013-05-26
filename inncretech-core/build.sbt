organization := "inncretech"

name := "inncretech-core"

version := "1.0"

crossPaths := false

libraryDependencies ++= Seq(
   "org.hibernate" % "hibernate-entitymanager" % "4.2.1.Final",
   "joda-time" % "joda-time" % "2.0",
   "org.jadira.usertype" % "usertype.core" % "3.0.0.CR1",
   "mysql" % "mysql-connector-java" % "5.1.18",
   "org.springframework" % "spring-context" % "3.2.0.RELEASE",
   "org.springframework" % "spring-test" % "3.2.0.RELEASE",
   "org.springframework.data"  % "spring-data-jpa" % "1.1.0.RELEASE",
   "org.springframework" % "spring-beans" % "3.2.2.RELEASE",
   "org.springframework" % "spring-aspects" % "3.1.1.RELEASE",
   "org.springframework" % "spring-aop" % "3.2.0.RELEASE",
   "aspectj" % "aspectjrt" % "1.5.4",
   "aspectj" % "aspectjweaver" % "1.5.4",
   "c3p0" % "c3p0" % "0.9.1.2",
   "org.springframework.security" % "spring-security-core" % "3.1.3.RELEASE")
