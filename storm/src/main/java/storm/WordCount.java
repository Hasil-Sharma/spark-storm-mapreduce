package storm;

import org.apache.storm.Config;
import org.apache.storm.StormSubmitter;
import org.apache.storm.generated.AlreadyAliveException;
import org.apache.storm.generated.AuthorizationException;
import org.apache.storm.generated.InvalidTopologyException;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;
import storm.wordcount.RandomSentenceSpout;
import storm.wordcount.SplitSentenceBolt;
import storm.wordcount.WordCountBolt;

public class WordCount {
    public static final String SPOUT_ID = "hdfsspout";
    public static final String BOLT_ID = "constbolt";

    public static void main(String[] args) throws AlreadyAliveException, InvalidTopologyException, AuthorizationException {

        TopologyBuilder builder = new TopologyBuilder();

        builder.setSpout("spout", new RandomSentenceSpout(), 1);
        builder.setBolt("split",
                new SplitSentenceBolt(), 3).shuffleGrouping("spout");

        builder.setBolt("count", new WordCountBolt(), 3).fieldsGrouping("split",
                new Fields("word"));
        Config conf = new Config();

        conf.setNumWorkers(1);

        StormSubmitter.submitTopology("word-count", conf, builder.createTopology());

    }
}
