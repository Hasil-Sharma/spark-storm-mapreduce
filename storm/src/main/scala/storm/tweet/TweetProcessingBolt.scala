package storm.tweet

import java.util

import org.apache.storm.task.{OutputCollector, TopologyContext}
import org.apache.storm.topology.{IRichBolt, OutputFieldsDeclarer}
import org.apache.storm.tuple.{Fields, Tuple, Values}
import twitter4j.Status

class TweetProcessingBolt extends IRichBolt{
  var collector: OutputCollector = _

  override def getComponentConfiguration: util.Map[String, AnyRef] = {
    return null
  }

  override def declareOutputFields(declarer: OutputFieldsDeclarer): Unit = {
    declarer.declare(new Fields("tweet"))
  }

  override def prepare(stormConf: util.Map[_, _], context: TopologyContext, collector: OutputCollector): Unit = {
    this.collector = collector
  }

  override def execute(input: Tuple): Unit = {
    val tweet: Status = input.getValueByField("tweet").asInstanceOf[Status]
    val text = tweet.getText()
    this.collector.emit(new Values(text))
  }

  override def cleanup(): Unit = {

  }

}
