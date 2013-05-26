import sbt._
import Keys._

object TagBuild extends Build {
    lazy val tag = Project(id = "tag",
                            base = file(".")).dependsOn(core).aggregate(core)

    lazy val core = RootProject(file("../inncretech-core"))

}