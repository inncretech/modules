import sbt._
import Keys._

object UserBuild extends Build {
    lazy val user = Project(id = "user",
                            base = file(".")).dependsOn(core).aggregate(core)

    lazy val core = RootProject(file("../inncretech-core"))

}