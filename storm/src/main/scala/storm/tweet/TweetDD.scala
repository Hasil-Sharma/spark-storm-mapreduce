package storm.tweet

import java.util

import org.apache.storm.spout.SpoutOutputCollector
import org.apache.storm.task.TopologyContext
import org.apache.storm.topology.OutputFieldsDeclarer
import org.apache.storm.topology.base.BaseRichSpout

class TweetDD extends BaseRichSpout{
  override def declareOutputFields(declarer: OutputFieldsDeclarer): Unit = ???

  override def nextTuple(): Unit = ???

  override def open(conf: util.Map[_, _], context: TopologyContext, collector: SpoutOutputCollector): Unit = ???
}
