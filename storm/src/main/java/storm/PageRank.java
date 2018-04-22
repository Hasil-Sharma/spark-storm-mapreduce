package storm;

import org.apache.storm.Config;
import org.apache.storm.StormSubmitter;
import org.apache.storm.generated.AlreadyAliveException;
import org.apache.storm.generated.AuthorizationException;
import org.apache.storm.generated.InvalidTopologyException;
import org.apache.storm.topology.BoltDeclarer;
import org.apache.storm.topology.TopologyBuilder;

import storm.pagerank.PageRankFixPoint;
import storm.pagerank.PageRankSink;
import storm.pagerank.PageRankSpout;
import storm.pagerank.PageRankUpdate;

public class PageRank {

    public static void main(String[] args) throws InvalidTopologyException, AuthorizationException, AlreadyAliveException {
        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("spout", new PageRankSpout(),1);
        BoltDeclarer fixpoint = builder.setBolt("fixpoint", new PageRankFixPoint(),1);
        BoltDeclarer updater = builder.setBolt("updater", new PageRankUpdate(), 1);
        BoltDeclarer sink = builder.setBolt("sink", new PageRankSink(),1);
        /////////////////////////////////
        fixpoint.globalGrouping("spout");
        /////////////////////////////////
        updater.globalGrouping("fixpoint", "toupdater");
        fixpoint.globalGrouping("updater");
        /////////////////////////////////
        sink.globalGrouping("fixpoint", "tosink");

        Config conf = new Config();
        conf.setDebug(true);
        conf.setNumWorkers(3);
        StormSubmitter.submitTopology("PageRank", conf, builder.createTopology());
    }
}
