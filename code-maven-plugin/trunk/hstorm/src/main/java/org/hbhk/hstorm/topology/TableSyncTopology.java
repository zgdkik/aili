package org.hbhk.hstorm.topology;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.time.DateUtils;
import org.hbhk.hstorm.bolt.TableSyncBolt;
import org.hbhk.hstorm.spout.TableSyncSpout;
import org.hbhk.hstorm.sync.SrcDBCPUtils;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.topology.TopologyBuilder;

public class TableSyncTopology {

	private static TopologyBuilder builder = new TopologyBuilder();
	
	public static Map<String, Integer> startSize = new  ConcurrentHashMap<String, Integer>();
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static SimpleDateFormat sdfn = new SimpleDateFormat("yyyyMMddHHmmss");


	public static void main(String[] args) throws Exception {
		Config conf = new Config();
		conf.setDebug(true);
		conf.setMaxTaskParallelism(1);
		// conf.put("src_tabName", args[0]);
		// conf.put("dest_tabName", args[1]);
		builder.setSpout("SrcTableSync", new TableSyncSpout(), 2);
		builder.setBolt("DestTableSync", new TableSyncBolt(), 2)
				.shuffleGrouping("SrcTableSync");
		LocalCluster cluster = new LocalCluster();
		// DBCPUtils.getConnection();
		Properties pro = new Properties();
		pro.load(SrcDBCPUtils.class.getClassLoader().getResourceAsStream(
				"tableSync.properties"));
		String tableNum = pro.getProperty("table.num");
		String[] arrtn = tableNum.split(",");
		String tableThread = pro.getProperty("table.thread");
		//table.startTime
		String tableStartTime = pro.getProperty("table.startTime");
		String 	tableDays = pro.getProperty("table.days");
		if(tableDays!=null && tableStartTime!=null){
			conf.put("table.days", tableDays );
		}
		String endDateStr =  pro.getProperty("table.endTime");
		Date endDate = sdf.parse(endDateStr);
		conf.put("table.thread", Integer.parseInt(tableThread));
		for (String str : arrtn) {
			String src_tabName = pro.getProperty("src_tabName" + str);
			String dest_tabName = pro.getProperty("dest_tabName" + str);
			conf.put("src_tabName", src_tabName);
			conf.put("dest_tabName", dest_tabName);
			conf.put("table.startTime", tableStartTime);
			Date startDate = sdf.parse(tableStartTime);
			Date ed = DateUtils.addDays(startDate, Integer.parseInt(tableDays));
			conf.put("table.endTime",  sdf.format(ed));
			cluster.submitTopology("TableSync" + str+""+sdfn.format(startDate), conf,
					builder.createTopology());
			String startSizeKey =  "TableSync" + str+sdfn.format(startDate);
			startSize.put(startSizeKey, 1);
			System.out.println("startSizeKey:"+startSizeKey);
			while (endDate.after(ed)) {
				startSize.put("startSize", 1);
				Date d1 = sdf.parse(String.valueOf(conf.get("table.endTime")));
				startSizeKey =  "TableSync" + str+sdfn.format(d1);
				startSize.put(startSizeKey, 1);
				System.out.println("startSizeKey:"+startSizeKey);
				conf.put("table.startTime", sdf.format(d1));
				ed = DateUtils.addDays(d1, Integer.parseInt(tableDays));
				conf.put("table.endTime",sdf.format(ed));
				System.out.println("²ð·ÖÈÕÆÚ:"+sdf.format(d1)+"=>"+sdf.format(ed));
				cluster.submitTopology("TableSync" + str+""+sdfn.format(startDate), conf,
						builder.createTopology());
			}
		}

	}
}
