package org.hbhk.hstorm.topology;

import org.hbhk.hstorm.bolt.PrintBolt;
import org.hbhk.hstorm.spout.RandomSentenceSpout;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.topology.TopologyBuilder;

public class Topology1 {

    private static TopologyBuilder builder = new TopologyBuilder();

    public static void main(String[] args) throws Exception {

        Config config = new Config();

        builder.setSpout("RandomSentence", new RandomSentenceSpout(), 2);
        builder.setBolt("WordNormalizer", new PrintBolt(), 2).shuffleGrouping("RandomSentence");
        config.setDebug(true);
        config.setMaxTaskParallelism(1);
        LocalCluster cluster = new LocalCluster();
        cluster.submitTopology("wordcount", config, builder.createTopology());
    }
}
