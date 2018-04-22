package storm.pagerank;

import org.apache.storm.shade.com.google.common.io.Resources;
import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;
import org.apache.storm.utils.Utils;

import java.io.*;
import java.util.Map;


public class PageRankSpout extends BaseRichSpout {
    SpoutOutputCollector _collector;
    BufferedReader _br = null;
    String inputFile = Resources.getResource("input1.txt").getPath();
    int count=0;

    @Override
    public void nextTuple() {
        // TODO Auto-generated method stub
        try {
            String line = _br.readLine();
            if (line != null) {
                while (line.startsWith("#")) {
                    line = _br.readLine();
                }
                _collector.emit(new Values(line));
                ++count;
            } else {
                _collector.emit(new Values("-1"));
                System.out.println("finished!!!!!!");
                while (true) {
                    Utils.sleep(10000);
                }
            }
            if(count==50000){
                _collector.emit(new Values("-1"));
                System.out.println("finished!!!!!!");
                while (true) {
                    Utils.sleep(10000);
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void open(Map conf, TopologyContext context,
                     SpoutOutputCollector collector) {
        // TODO Auto-generated method stub
        _collector = collector;
//		try {
        InputStream in = getClass().getResourceAsStream("/ssc.txt");
        _br = new BufferedReader(new InputStreamReader(in));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        // TODO Auto-generated method stub
        declarer.declare(new Fields("data"));
    }

}