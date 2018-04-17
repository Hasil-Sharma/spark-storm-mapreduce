package storm.tweetkafka

import org.apache.storm.kafka._
import org.apache.storm.spout.SchemeAsMultiScheme
import org.apache.storm.{Config, LocalCluster, StormSubmitter}
import org.apache.storm.topology.TopologyBuilder
import storm.tweet.{TweetProcessingBolt, TwitterSpout}

object TwitterKafkaTopology {

  def main(args: Array[String]): Unit = {

    val zkConnString: String = "34.217.111.247:2181"
    val hosts: BrokerHosts = new ZkHosts(zkConnString,"/brokers")
    val topic: String = "twitter-topic"
    val kafkaSpoutConfig: SpoutConfig = new SpoutConfig(hosts, topic, "/twitter", "test")

    kafkaSpoutConfig.scheme = new SchemeAsMultiScheme(new StringScheme())
    val topologyBuilder: TopologyBuilder = new TopologyBuilder()
    topologyBuilder.setSpout("kafkaspout", new KafkaSpout(kafkaSpoutConfig))
    topologyBuilder.setBolt("tweet-process",
      new GeoLocationBolt(), 3).shuffleGrouping("kafkaspout")

    val conf = new Config()
    conf.setDebug(true)

    conf.setNumWorkers(3)
    StormSubmitter.submitTopology("twitter",
      conf, topologyBuilder.createTopology())

//    val cluster: LocalCluster = new LocalCluster()
//    cluster.submitTopology("wordCounter", conf, topologyBuilder.createTopology())
//    Thread.sleep(10 * 1000)
//    cluster.killTopology("wordCounter")

  }
}
