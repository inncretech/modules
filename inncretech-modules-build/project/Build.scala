import sbt._
import Keys._
import com.typesafe.sbteclipse.plugin.EclipsePlugin.EclipseKeys
import com.typesafe.sbteclipse.plugin.EclipsePlugin.EclipseCreateSrc

object AllBuild extends Build {
  
    EclipseKeys.createSrc := EclipseCreateSrc.Default + EclipseCreateSrc.Resource
   
    lazy val all = Project(id = "all",
                            base = file(".")).dependsOn(core, user, follow, tag, comment, notification, source).
                            aggregate(core,user, follow, tag, comment, notification, source)

    lazy val core = RootProject(file("../inncretech-core"))

    lazy val user = RootProject(file("../inncretech-user"))
    
    lazy val follow = RootProject(file("../inncretech-follow"))
    
    lazy val tag = RootProject(file("../inncretech-tag"))
    
    lazy val comment = RootProject(file("../inncretech-comment"))
    
    lazy val notification = RootProject(file("../inncretech-notification"))
    
    lazy val source = RootProject(file("../inncretech-source"))
}