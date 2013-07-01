import sbt._
import Keys._
import com.typesafe.sbteclipse.plugin.EclipsePlugin.EclipseKeys
import com.typesafe.sbteclipse.plugin.EclipsePlugin.EclipseCreateSrc

object UserBuild extends Build {
    
    EclipseKeys.createSrc := EclipseCreateSrc.Default + EclipseCreateSrc.Resource
    
    lazy val user = Project(id = "user",
                            base = file(".")).dependsOn(core).aggregate(core)

    lazy val core = RootProject(file("../inncretech-core"))

}