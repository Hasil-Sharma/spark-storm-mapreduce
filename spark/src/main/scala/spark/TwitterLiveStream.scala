package spark

import org.apache.spark.{SparkConf, SparkContext}
import spark.meta.GetTweets

object TwitterLiveStream {
  def main(args: Array[String]): Unit = {

    val outputFile = args(0)
    val num = args(1).toInt
    val token = args(2)

    val conf = new SparkConf().setAppName(f"Twitter Live Stream - $num")
    val sc = new SparkContext(conf)
    val numPartitions = Array.fill[Int](5)(num)

    val distData = sc.parallelize(numPartitions, numPartitions.length)

    val tweetsRDD = distData.mapPartitions(lines => {
      lines.flatMap(x => {
        val tweets = new GetTweets()
        tweets.consumeTweets(x, token)
      })
    })

    tweetsRDD.saveAsTextFile(outputFile)
    sc.stop()
  }
}
