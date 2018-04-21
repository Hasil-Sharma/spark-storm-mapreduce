package storm.tweet

//import org.apache.storm.kafka.{BrokerHosts, KafkaSpout, SpoutConfig, ZkHosts}
import org.apache.storm.{Config, LocalCluster, StormSubmitter}
import org.apache.storm.topology.TopologyBuilder

object TweetTopology {

  def main(args: Array[String]): Unit = {

//    val zkConnString: String = "34.217.111.247:2181"
//    val hosts: BrokerHosts = new ZkHosts(zkConnString,"/brokers")
//    val topic: String = "twitter-topic"
//    val kafkaSpoutConfig: SpoutConfig = new SpoutConfig(hosts, topic, "/brokers", "twitter-topic")
//    val topologyBuilder: TopologyBuilder = new TopologyBuilder()
//    topologyBuilder.setSpout("kafkaspout", new KafkaSpout(kafkaSpoutConfig))
//    topologyBuilder.setBolt("tweet-process",
//      new TweetProcessingBolt, 3).shuffleGrouping("kafkaspout")

    val builder:TopologyBuilder = new TopologyBuilder()
    builder.setSpout("twitter-spout", new TwitterSpout(),2)

    builder.setBolt("twitter-filter", new TweetProcessingBolt(),4).shuffleGrouping("twitter-spout")

    val conf = new Config()
    conf.setDebug(true)

    conf.setNumWorkers(6)
    StormSubmitter.submitTopology("twitter",
        conf, builder.createTopology())

  }
}
