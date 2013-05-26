import sbt._
import Keys._

object NotiBuild extends Build {
    lazy val noti = Project(id = "noti",
                            base = file(".")).dependsOn(core).aggregate(core)

    lazy val core = RootProject(file("../inncretech-core"))

}