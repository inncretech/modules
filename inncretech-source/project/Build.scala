import sbt._
import Keys._

object SourceBuild extends Build {
    lazy val source = Project(id = "source",
                            base = file(".")).dependsOn(core).aggregate(core)

    lazy val core = RootProject(file("../inncretech-core"))

}