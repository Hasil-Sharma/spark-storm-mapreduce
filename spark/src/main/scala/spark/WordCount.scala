package spark

import org.apache.spark._
import org.apache.spark.SparkContext._

object WordCount {
  def main(args: Array[String]): Unit = {
    val inputFile = args(0)
    val outputFile = args(1)
    val conf = new SparkConf().setAppName("wordCount")
    // Create a Scala Spark Context.
    val sc = new SparkContext(conf)
    val textFile = sc.textFile(inputFile)
    val counts = textFile.flatMap(line => line.split(" "))
      .map(word => (word, 1))
      .reduceByKey(_ + _)
    counts.saveAsTextFile(outputFile)
  }
}
