package mapreduce

import mapreduce.mappers.TweetMapper
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.Path
import org.apache.hadoop.io.{NullWritable, Text}
import org.apache.hadoop.mapreduce.Job
import org.apache.hadoop.mapreduce.lib.input.{FileInputFormat, KeyValueTextInputFormat}
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat

object TweetMR {
  def main(args: Array[String]): Unit = {
    val configuration = new Configuration
    val inputPath = args(0)
    val outputPath = args(1)
    val num = args(2).toInt

    val token = args(3)
    configuration.setInt("num", num)
    configuration.set("token", token)
    val job = new Job(configuration, s"Tweet MR : Num - $num Token - $token")
    job.setJarByClass(this.getClass())

    job.setMapperClass(classOf[TweetMapper])

    job.setInputFormatClass(classOf[KeyValueTextInputFormat])
    job.setOutputKeyClass(classOf[NullWritable])
    job.setOutputValueClass(classOf[Text])

    FileInputFormat.addInputPath(job, new Path(inputPath))
    FileOutputFormat.setOutputPath(job, new Path(outputPath))
    job.waitForCompletion(true)

  }
}
