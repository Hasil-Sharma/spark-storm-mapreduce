import sbt._
import  Keys._

object Common {
  val commonSettings = Seq(
    organization := "com.ds"
  )

    def importSubProject(name: String) : Project = (
    Project(name, file(name))
    settings(commonSettings:_*)
  )
}
