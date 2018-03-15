package org.hbhk.hstorm.bolt;

import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.hbhk.hstorm.sync.DBCPUtils;
import org.hbhk.hstorm.sync.DbSyncUtil;

import backtype.storm.task.TopologyContext;
import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;

@SuppressWarnings("serial")
public class ChuanxinTableSyncBolt extends BaseBasicBolt {

	private Connection conn;

	private String sql;

	private String tabName;

	private static ExecutorService exe = Executors.newCachedThreadPool();
	private Map conf = null;

	@Override
	public void prepare(Map conf, TopologyContext context) {
		this.conf = conf;
		super.prepare(conf, context);

	}

	public void execute(Tuple input, BasicOutputCollector collector) {
		try {
			if (conn == null) {
				try {
					conn = DBCPUtils.getConnection();
					tabName = (String) conf.get("dest_tabName");
					DbSyncUtil.intiDbInfo(conn, tabName);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			} else {
				conn = DBCPUtils.getConnection();
			}
			Object mesg = input.getValue(0);
			if (mesg != null) {
				List<Map<String, Object>> dataList = (List<Map<String, Object>>) mesg;
				System.out.println(Thread.currentThread().getName()
						+ "目标数据源开始插入数据:" + dataList.size());
				DbSyncUtil.insert(conn, tabName, dataList, 1000);
				System.out.println(Thread.currentThread().getName()
						+ "目标数据源结束插入数据:" + dataList.size());
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("TableSync"));
	}
}
