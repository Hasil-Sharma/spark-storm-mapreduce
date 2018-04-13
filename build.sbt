import Common._

name := "spark-storm-mapreduce"

version := "0.1"

scalaVersion := "2.12.5"

scalacOptions += "-target:jvm-1.7"


lazy val storm = importSubProject("storm")

lazy val mapreduce = importSubProject("mapreduce")

lazy val spark = importSubProject("spark")