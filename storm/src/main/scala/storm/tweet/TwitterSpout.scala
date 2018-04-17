package storm.tweet

import java.util

import org.apache.storm.spout.SpoutOutputCollector
import org.apache.storm.task.TopologyContext
import org.apache.storm.topology.OutputFieldsDeclarer
import org.apache.storm.topology.base.BaseRichSpout
import org.apache.storm.utils.Utils
import org.apache.storm.Config
import org.apache.storm.tuple.Fields
import java.util.concurrent.LinkedBlockingQueue

import org.apache.storm.tuple.Values
import twitter4j._
import twitter4j.conf.ConfigurationBuilder

class TwitterSpout extends BaseRichSpout {

  var _collector: SpoutOutputCollector = _
  var queue: LinkedBlockingQueue[Status] = _
  var _twitterStream: TwitterStream = _
  var consumerKey: String = "ibKVFwn6prLt6uHlOmvs1cTBu"
  var consumerSecret: String = "6fujwa7Q3nAJdhEjH20NSMdqdRWzzQWXPAGsbYkIZ3qIvnMiOT"
  var accessToken: String = "1327782752-CnVRtLcEOLuOBlYhhFowfjli2bvjWqrSPbWxVgx"
  var accessTokenSecret: String = "7KAva5NHSc1ExAf3Ci3cmJFxwsQU4nlkoIIyHl9nCUeUc"
  var keyWords: Array[String] = Array("CSK","MI")

  override def open(conf: util.Map[_, _], context: TopologyContext, collector: SpoutOutputCollector): Unit = {
    queue = new LinkedBlockingQueue[Status](1000)
    _collector = collector
    val listener: StatusListener = new StatusListener () {

      def onStatus(status: Status): Unit = {
        println(status.getText())
        queue.offer(status)
      }

      def onDeletionNotice(sdn: StatusDeletionNotice): Unit
      =
      {
      }
      def onTrackLimitationNotice(i: Int): Unit
      =
      {
      }
      def onScrubGeo(l: Long, l1: Long): Unit
      =
      {
      }
      def onException(ex: Exception): Unit
      =
      {
      }
      def onStallWarning(arg0: StallWarning): Unit
      =
      {
      }
    }

    val cb: ConfigurationBuilder = new ConfigurationBuilder()
    cb.setDebugEnabled(true).setOAuthConsumerKey(consumerKey).setOAuthConsumerSecret(consumerSecret).setOAuthAccessToken(accessToken).setOAuthAccessTokenSecret(accessTokenSecret)
    _twitterStream = new TwitterStreamFactory(cb.build()).getInstance()
    _twitterStream.addListener(listener)

    if (keyWords.length == 0) _twitterStream.sample()
    else {
      val query: FilterQuery = new FilterQuery().track(keyWords)
      _twitterStream.filter(query)
    }
  }

  override def nextTuple(): Unit = {
    val ret = queue.poll()
    if (ret == null)
      {

      }
    else _collector.emit(new Values(ret))
  }

  override def close(): Unit = {
    _twitterStream.shutdown()
  }

  override def getComponentConfiguration: Config = {
    val ret:Config  = new Config()
    ret.setMaxTaskParallelism(1)
    return ret
  }

  override def ack(id: Any): Unit = {
  }

  override def fail(id: Any): Unit = {
  }

  override def declareOutputFields(declarer: OutputFieldsDeclarer): Unit = {
    declarer.declare(new Fields("tweet"))
  }

}
