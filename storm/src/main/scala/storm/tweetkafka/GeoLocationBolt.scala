package storm.tweetkafka

import java.util

import org.apache.storm.task.{OutputCollector, TopologyContext}
import org.apache.storm.topology.{IRichBolt, OutputFieldsDeclarer}
import org.apache.storm.tuple.{Tuple, Values,Fields}
import org.json.simple.{JSONArray, JSONObject, JSONValue}

class GeoLocationBolt extends IRichBolt{

  var collector: OutputCollector = _

  override def prepare(stormConf: util.Map[_, _], context: TopologyContext, collector: OutputCollector): Unit = {
    this.collector = collector
  }

  override def execute(input: Tuple): Unit = {
    try {
      println("Here")
      val obj: JSONObject = JSONValue.parseWithException(input.getString(0)).asInstanceOf[JSONObject]
      println("Done")
      if (obj.containsKey("lang") && "en".equals(obj.get("lang"))) {
        val id : Long= obj.get("id").asInstanceOf[Long]
        val text :String = obj.get("text").asInstanceOf[String]
        val createdAt : String = obj.get("created_at").asInstanceOf[String]
//        val entities: JSONObject = obj.get("entities").asInstanceOf[JSONObject]
//        val hashtags: JSONArray = entities.get("hashtags").asInstanceOf[JSONArray]
//        val hashtagList :util.HashSet[String] = new util.HashSet[String]()
//        for (hashtag <- hashtags) {
//          hashtagList.add((hashtag.asInstanceOf[JSONObject]).get("text").asInstanceOf[String].toLowerCase)
//        }
        collector.emit(new Values( text, createdAt))

      }
      else println("Ignoring non-english tweets")
    } catch {
      case e:Exception =>
        println("Error parsing tweet: " + e.getMessage)
    }
  }

  override def cleanup(): Unit = {

  }

  override def getComponentConfiguration: util.Map[String, AnyRef] = {
    return null
  }

  override def declareOutputFields(declarer: OutputFieldsDeclarer): Unit = {
    declarer.declare(new Fields( "tweet_text", "tweet_created_at"))
  }
}
