import sbt._
import Keys._

object FollowBuild extends Build {
    lazy val follow = Project(id = "follow",
                            base = file(".")).dependsOn(core).aggregate(core)

    lazy val core = RootProject(file("../inncretech-core"))
    
    val appDependencies = Seq(
        "org.apache.derby" % "derby" % "10.4.1.3"
    )

}