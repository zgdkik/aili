package org.hbhk.hstorm.spout;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.hbhk.hstorm.sync.SrcDBCPUtils;
import org.hbhk.hstorm.topology.TableSyncTopology;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;

// 随机发送一条内置消息，该spout继承BaseRichSpout/IRichSpout类
@SuppressWarnings("serial")
public class TableSyncSpout extends BaseRichSpout {

	private SpoutOutputCollector spoutOutputCollector;

	private Connection conn;

	private String sql = "";

	private String tabName;

	private int pageSize = 10000;

	Long totalCount = 0L;
	int totalPage = 0;
	static int i = 1;
	Date startTime;
	Date endTime;
	
	String startSizeKey = "";
	public static SimpleDateFormat sdfn = new SimpleDateFormat("yyyyMMddHHmmss");

	// 进行spout的一些初始化工作，包括参数传递

	@SuppressWarnings("rawtypes")
	public void open(Map conf, TopologyContext context,
			SpoutOutputCollector collector) {
		spoutOutputCollector = collector;
		tabName = (String) conf.get("src_tabName");
		try {
			String startTimeStr = (String) conf.get("table.startTime");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			startTime = sdf.parse(startTimeStr);
			startSizeKey =  "TableSync" + tabName+sdfn.format(startTime);
			String endTimeStr = (String) conf.get("table.endTime");
			endTime = sdf.parse(endTimeStr);
			conn = SrcDBCPUtils.getConnection();
			String sql = "select count(*) from " + tabName;
			if (startTime != null) {
				sql = sql + " where creater_time >= ? and  ? < creater_time ";
			}
			PreparedStatement st = conn.prepareStatement(sql);
			if (startTime != null) {
				java.sql.Date sqlDate = new java.sql.Date(startTime.getTime());
				st.setDate(1, sqlDate);
				java.sql.Date eDate = new java.sql.Date(endTime.getTime());
				st.setDate(2, eDate);
			}
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				totalCount = rs.getLong(1);
			}
			totalPage = getPages(totalCount);
			System.out.println("需要处理的总数据:" + totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 进行Tuple处理的主要方法

	public void nextTuple() {
		try {
			i = TableSyncTopology.startSize.get("startSize");
			if (i > totalPage) {
				System.out.println("数据抽取处理完成");
				return;
			}
			for (i = TableSyncTopology.startSize.get(startSizeKey); i <= totalPage; i++) {
				List<Map<String, Object>> dataList = select(tabName, i,
						pageSize);
				if (dataList.size() > 0) {
					TableSyncTopology.startSize.put(startSizeKey, i + 1);
					spoutOutputCollector.emit(new Values(dataList));
					return;
				}
			}
			try {
				TimeUnit.SECONDS.sleep(15);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getPages(Long totalCount) {
		if (totalCount <= 0) {
			return 1;
		}
		return (int) Math.ceil((double) totalCount / pageSize);
	}

	public List<Map<String, Object>> select(String tabName, int pageNum,
			int pageSize) throws Exception {
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		int start = (pageNum - 1) * pageSize;
		System.out.println(Thread.currentThread().getName() + "开始处理第:" + start
				+ "到" + (start + pageSize) + "数据");
		String sql = "select  *from " + tabName + " ";

		if (startTime != null) {
			sql = sql
					+ " where creater_time >= ? and ? <  creater_time   ORDER BY  creater_time   limit ?,?  ";
		} else {
			sql = sql + " ORDER BY  creater_time   limit ?,?  ";
		}
		PreparedStatement st = conn.prepareStatement(sql);
		if (startTime != null) {
			java.sql.Date sqlDate = new java.sql.Date(startTime.getTime());
			st.setDate(1, sqlDate);
			java.sql.Date eDate = new java.sql.Date(endTime.getTime());
			st.setDate(2, eDate);
			st.setInt(3, start);
			st.setInt(4, pageSize);
		} else {
			st.setInt(1, start);
			st.setInt(2, pageSize);
		}
		ResultSet rs = st.executeQuery();
		ResultSetMetaData me = rs.getMetaData();
		while (rs.next()) {
			Map<String, Object> map = new HashMap<String, Object>();
			for (int i = 1; i < me.getColumnCount() + 1; i++) {
				String col = me.getColumnName(i).toLowerCase();

				Object val = rs.getObject(i);
				if (val instanceof Boolean) {
					val = rs.getInt(i);
					map.put(col, val);
				} else {
					map.put(col, val);

				}
			}
			dataList.add(map);
		}
		System.out.println(Thread.currentThread().getName() + "结束处理第:" + start
				+ "到" + (start + pageSize) + "数据");
		return dataList;
	}

	// 消息保证机制中的ack确认方法
	public void ack(Object id) {
	}

	// 消息保证机制中的fail确认方法
	public void fail(Object id) {
	}

	// 声明字段
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("TableSync"));
	}

}
