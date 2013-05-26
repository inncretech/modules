import sbt._
import Keys._

object CommentBuild extends Build {
    lazy val comment = Project(id = "comment",
                            base = file(".")).dependsOn(core).aggregate(core)

    lazy val core = RootProject(file("../inncretech-core"))

}