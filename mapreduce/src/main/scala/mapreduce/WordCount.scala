package mapreduce


import mapreduce.mappers.TokenizeMapper
import mapreduce.reducers.IntSumReader
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.Path
import org.apache.hadoop.io.{IntWritable, Text}
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat
import org.apache.hadoop.mapreduce.Job


object WordCount {

  def main(args: Array[String]): Unit = {
    val configuration = new Configuration
    val job = new Job(configuration, "word count")

    job.setJarByClass(this.getClass)

    job.setMapperClass(classOf[TokenizeMapper])
    job.setCombinerClass(classOf[IntSumReader])
    job.setReducerClass(classOf[IntSumReader])

    job.setOutputKeyClass(classOf[Text])
    job.setOutputValueClass(classOf[IntWritable])

    FileInputFormat.addInputPath(job, new Path(args(0)))
    FileOutputFormat.setOutputPath(job, new Path(args(1)))

    System.exit(if(job.waitForCompletion(true)) 0 else 1)
  }
}
