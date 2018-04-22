package storm.wordcount;


import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;
import org.apache.storm.utils.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class RandomSentenceSpout extends BaseRichSpout {
    SpoutOutputCollector _collector;
    Random _rand;
    List<String> list = new ArrayList<>();
    BufferedReader _br = null;
    int count = 0;

    @Override
    public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
        _collector = collector;
        InputStream in = getClass().getResourceAsStream("/input.txt");
        _br = new BufferedReader(new InputStreamReader(in));
        String line = null;
        try {
            while ((line = _br.readLine()) != null) {
                list.add(line);
                count++;
            }
            _br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void nextTuple() {
        Utils.sleep(100);

        for (int i = 0; i < count; i++) {
            _collector.emit(new Values(list.get(i)));
        }

        while (true) {
            Utils.sleep(10000);

        }

    }

    @Override
    public void ack(Object id) {
    }

    @Override
    public void fail(Object id) {
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("word"));
    }
}
