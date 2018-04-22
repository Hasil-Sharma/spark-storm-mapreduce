package storm

import org.apache.storm.{Config, StormSubmitter}
import org.apache.storm.topology.TopologyBuilder
import storm.twitter.{TweetProcessingBolt, TwitterSpout}


object TwitterLiveStream {

  def main(args: Array[String]): Unit = {
    val builder: TopologyBuilder = new TopologyBuilder()
    builder.setSpout("twitter-spout", new TwitterSpout(), 2)

    builder.setBolt("twitter-filter", new TweetProcessingBolt(), 4).shuffleGrouping("twitter-spout")

    val conf = new Config()
    conf.setDebug(true)

    conf.setNumWorkers(6)
    StormSubmitter.submitTopology("twitter", conf, builder.createTopology())

  }
}
