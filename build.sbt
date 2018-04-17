import Common._
import Dependencies._

name := "spark-storm-mapreduce"

lazy val assemblySettings = Seq(
  assemblyJarName in assembly := name.value + ".jar",
  assemblyMergeStrategy in assembly := {
    case PathList("META-INF", xs @ _*) => MergeStrategy.discard
    case _                             => MergeStrategy.first
  }
)

lazy val root = (project in file("."))
  .settings(commonSettings)

lazy val storm = importSubProject("storm")

lazy val mapreduce = importSubProject("mapreduce")
  .settings(libraryDependencies ++= mapreduceLibraryDependencies)
  .settings(assemblySettings)

lazy val spark = importSubProject("spark")
  .settings(scalaVersion := "2.11.7")
  .settings(libraryDependencies ++= libraryDependencies_spark)
