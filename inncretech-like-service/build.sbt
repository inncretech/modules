organization := "inncretech"

name := "inncretech-like-service"

version := "1.0"

crossPaths := false

EclipseKeys.withSource := true

libraryDependencies ++= Seq(
   "inncretech" % "inncretech-core" % "1.0",
   "org.hibernate" % "hibernate-entitymanager" % "4.2.1.Final",
   "joda-time" % "joda-time" % "2.0",
   "org.jadira.usertype" % "usertype.core" % "3.0.0.CR1",
   "mysql" % "mysql-connector-java" % "5.1.18",
   "org.springframework" % "spring-context" % "3.2.0.RELEASE",
   "org.springframework.data"  % "spring-data-jpa" % "1.1.0.RELEASE",
   "org.springframework" % "spring-beans" % "3.2.2.RELEASE",
   "org.springframework" % "spring-aspects" % "3.1.1.RELEASE",
   "c3p0" % "c3p0" % "0.9.1.2",
   "org.springframework.security" % "spring-security-core" % "3.1.3.RELEASE")