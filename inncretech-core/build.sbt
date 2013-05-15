name := "inncretech-user"

version := "1.0"

libraryDependencies ++= Seq(
   "org.hibernate" % "hibernate-entitymanager" % "3.6.9.Final",
   "mysql" % "mysql-connector-java" % "5.1.18",
   "org.springframework" % "spring-context" % "3.2.0.RELEASE",
   "org.springframework.data"  % "spring-data-jpa" % "1.1.0.RELEASE",
   "org.springframework" % "spring-beans" % "3.2.2.RELEASE",
   "org.springframework" % "spring-aspects" % "3.1.1.RELEASE",
   "c3p0" % "c3p0" % "0.9.1.2",
   "org.springframework.security" % "spring-security-core" % "3.1.3.RELEASE")
