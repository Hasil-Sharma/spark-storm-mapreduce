package storm.pagerank;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;

import java.util.Map;

public class PageRankSink extends BaseRichBolt {

    @Override
    public void prepare(Map stormConf, TopologyContext context,
                        OutputCollector collector) {

    }

    @Override
    public void execute(Tuple input) {
        // TODO Auto-generated method stub
        int node = input.getIntegerByField("node");
        Double pagerank = input.getDoubleByField("result");
        System.out.println("received by sink: node="+node+", value="+pagerank);
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {

    }

}
