package org.hbhk.hstorm.spout;

import java.util.Map;
import java.util.Random;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;
import backtype.storm.utils.Utils;

// �������һ��������Ϣ����spout�̳�BaseRichSpout/IRichSpout��
@SuppressWarnings("serial")
public class RandomSentenceSpout extends BaseRichSpout {

    SpoutOutputCollector spoutOutputCollector;
    Random random;

    // ����spout��һЩ��ʼ��������������������
    @SuppressWarnings("rawtypes")
    public void open(Map conf, TopologyContext context,
                     SpoutOutputCollector collector) {
        spoutOutputCollector = collector;
        random = new Random();
    }

    // ����Tuple�������Ҫ����
    
    public void nextTuple() {
        Utils.sleep(10);
        String[] sentences = new String[]{
                "jikexueyuan is a good school",
                "And if the golden sun",
                "four score and seven years ago",
                "storm hadoop spark hbase",
                "blogchong is a good man",
                "Would make my whole world bright",
                "blogchong is a good website",
                "storm would have to be with you",
                "Pipe to subprocess seems to be broken No output read",
                " You make me feel so happy",
                "For the moon never beams without bringing me dreams Of the beautiful Annalbel Lee",
                "Who love jikexueyuan and blogchong",
                "blogchong.com is Magic sites",
                "Ko blogchong swayed my leaves and flowers in the sun",
                "You love blogchong.com", "Now I may wither into the truth",
                "That the wind came out of the cloud",
                "at backtype storm utils ShellProcess",
                "Of those who were older than we"};
        // ��sentences�����У������ȡһ����䣬��Ϊ���spout���͵���Ϣ
        String sentence = sentences[random.nextInt(sentences.length)];
        System.out.println("ѡ�������Դ��: " + sentence);
        // ʹ��emit��������Tuple������������Values����
        spoutOutputCollector.emit(new Values(sentence.trim().toLowerCase()));
    }

    // ��Ϣ��֤�����е�ackȷ�Ϸ���
    public void ack(Object id) {
    }

    // ��Ϣ��֤�����е�failȷ�Ϸ���
    public void fail(Object id) {
    }

    // �����ֶ�
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("word"));
    }

}
