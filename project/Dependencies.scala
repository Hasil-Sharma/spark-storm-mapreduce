import sbt._

object Dependencies {
  val mapreduceLibraryDependencies = Seq(
    "org.apache.hadoop" % "hadoop-core" % "1.2.1" % "provided",
    "org.apache.hadoop" % "hadoop-aws" % "2.9.0",
    "org.twitter4j" % "twitter4j-core" % "4.0.6",
    "org.twitter4j" % "twitter4j-stream" % "4.0.6"
  )

  val sparkLibraryDependencies = Seq(
    "org.apache.spark" %% "spark-core" % "2.1.2" % "provided",
    "org.apache.hadoop" % "hadoop-aws" % "2.7.0",
    "org.twitter4j" % "twitter4j-core" % "4.0.6",
    "org.twitter4j" % "twitter4j-stream" % "4.0.6"
  )

  val stormLibraryDependencies = Seq(
    "org.apache.storm" % "storm-core" % "1.0.2" % "provided",
    "org.twitter4j" % "twitter4j-core" % "4.0.6",
    "org.twitter4j" % "twitter4j-stream" % "4.0.6"
  )
}

