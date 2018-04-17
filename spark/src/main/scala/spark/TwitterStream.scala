/* PageRank.scala */
package spark

import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf
import scala.io.Source
import java.io._

object Twitter {



  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("Page Rank")
    val sc = new SparkContext(conf)

    val file = new File(args(1))
    val bw = new BufferedWriter(new FileWriter(file))
    val filename = args(0)
    for (line <- Source.fromFile(filename).getLines) {
      if(line.contains("California"))
      {
        bw.write(line)
      }
    }
    bw.close()

    sc.stop()
  }
}
