package storm.wordcount

import org.apache.storm.topology.base.BaseBasicBolt
import org.apache.storm.topology.{BasicOutputCollector, OutputFieldsDeclarer}
import org.apache.storm.tuple.{Fields, Tuple, Values}

class SplitSentenceBolt extends BaseBasicBolt {
  override def execute(input: Tuple, collector: BasicOutputCollector): Unit = {
    val sentence = input.getString(0)
    val words = sentence.split(" ").filterNot(_ == "")
    for(i <- 0 until words.length){
      collector.emit(new Values(words(i)))
    }
  }

  override def declareOutputFields(declarer: OutputFieldsDeclarer): Unit = {
    declarer.declare(new Fields("word"))
  }
}