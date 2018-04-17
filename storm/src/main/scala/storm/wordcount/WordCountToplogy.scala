package storm.wordcount

import org.apache.storm.hdfs.spout.{HdfsSpout, TextFileReader}
import org.apache.storm.topology.TopologyBuilder
import org.apache.storm.tuple.Fields
import org.apache.storm.{Config, LocalCluster, StormSubmitter}

object WordCountTopology {
  def main(args: Array[String]): Unit = {
    println("Hello, world!")

    val spout :HdfsSpout  = new HdfsSpout().withOutputFields("sentence").setReaderType("text")
      .setHdfsUri("hdfs://ec2-52-53-182-196.us-west-1.compute.amazonaws.com:9000")
      .setSourceDir("/user/ec2-user/input")
      .setArchiveDir("/data/done")
      .setBadFilesDir("/data/badfiles")

    val builder = new TopologyBuilder()
    builder.setSpout("spout", spout, 5)
    builder.setBolt("split",
      new SplitSentenceBolt, 8).shuffleGrouping("spout")
    builder.setBolt("count", new WordCountBolt, 12).fieldsGrouping("split",
      new Fields("word"))
    val conf = new Config()
    conf.setDebug(true)
    if (args != null && args.length > 0) {
      conf.setNumWorkers(3)
      StormSubmitter.submitTopology(args(0),
        conf, builder.createTopology())
    } else {
      conf.setMaxTaskParallelism(3)
      val cluster = new LocalCluster
      cluster.submitTopology("word-count", conf, builder.createTopology())
      Thread.sleep(10000)
      cluster.shutdown()
    }
  }
}
