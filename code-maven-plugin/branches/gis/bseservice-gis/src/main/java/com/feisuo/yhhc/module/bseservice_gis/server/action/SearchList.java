/**
 * SearchList.java
 * Created at 2015年10月15日
 * Created by zhangjianfeng
 * Copyright (C) 2015 SHANGHAI VOLKSWAGEN, All rights reserved.
 */
package com.feisuo.yhhc.module.bseservice_gis.server.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.feisuo.yhhc.module.bseservice_gis.api.shared.dto.TrackOrdersHistoricalDTO;
import com.feisuo.yhhc.module.bseservice_gis.server.util.MongodbUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.QueryOperators;

/**
 * ClassName: SearchList 
 * Description: 获取行车轨迹数据 
 * Author: zhangjianfeng 
 * Date:2015年10月15日
 */
public class SearchList {
	private static Logger logger = Logger.getLogger(SearchList.class);
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	
	public static List<TrackOrdersHistoricalDTO> searchTrackOrdersHistorical(
			Map<Object, Object> paraMa) throws ParseException {
		logger.info("SearchList.searchTrackOrdersHistorical() begin...........................");
		List<TrackOrdersHistoricalDTO> list = new ArrayList<TrackOrdersHistoricalDTO>();
		DBCollection dbCollection = null;
		BasicDBObject basicDBObject = new BasicDBObject();
		if (paraMa != null && paraMa.size() > 0) {
			// 拿到连接
			dbCollection = MongodbUtil.connect(
					String.valueOf(paraMa.get("url")),
					Integer.parseInt(String.valueOf(paraMa.get("port"))),
					String.valueOf(paraMa.get("dataBase")), "TruckGis_"
							+ String.valueOf(paraMa.get("tableName")));
		}

		// 导航开始时间
		Long begintick= null;
		if (paraMa.get("drivingStart") != null && "" != paraMa.get("drivingStart")) {
			 //String begintime = String.valueOf(paraMa.get("drivingStart"));
			Date begin =(Date)paraMa.get("drivingStart");//sdf.parse(begintime);
			begintick = begin.getTime();
			if (begintick != null && begintick != 0l) {
				basicDBObject.append("TimeTick",new BasicDBObject().append(QueryOperators.GTE, begintick));
			}
		}
		// 导航结束时间
		if (paraMa.get("drivingEnd") != null && "" != paraMa.get("drivingEnd")) {
			 //String endTime = String.valueOf(paraMa.get("drivingEnd"));
			Date end = (Date)paraMa.get("drivingEnd");//sdf.parse(endTime);
			Long endtick = end.getTime();
			if (endtick != null && endtick != 0l) {
				//basicDBObject.append("TimeTick", new BasicDBObject().append(QueryOperators.LTE, endtick));
				if(begintick!=null){
					basicDBObject.append(QueryOperators.AND, new BasicDBObject[] {
			                new BasicDBObject("TimeTick", new BasicDBObject(QueryOperators.LTE,endtick)) });
				}else{
					basicDBObject.append("TimeTick",
							new BasicDBObject(QueryOperators.LTE,endtick));
				}
			}
		}
		// 司机编号
		if (paraMa.get("UserID") != null) {
			String userId = String.valueOf(paraMa.get("UserID"));
			if ("" != userId && userId != null) {
				basicDBObject.append("TruckId", userId);
			}
		}
		//结果集
		DBCursor cursor = dbCollection.find(basicDBObject);
		while (cursor.hasNext()) {
			DBObject object = cursor.next();
			TrackOrdersHistoricalDTO po = new TrackOrdersHistoricalDTO();
			if(object.get("Gps")!=null){
				po.setLongitude(Double.parseDouble(String.valueOf(((DBObject)object.get("Gps")).get("Lng"))));
				po.setLatitude(Double.parseDouble(String.valueOf(((DBObject)object.get("Gps")).get("Lat"))));
			}
			po.setUserID(String.valueOf(object.get("TruckId")));
			Date createDate = new Date();//创建时间
			po.setCreateDate(createDate);
			po.setCreateDate_text(sdf.format(createDate));
			list.add(po);
		}
		DB db = dbCollection.getDB();
		if (db != null) {
			db.requestDone();// 关闭db
			db = null;
		}
		logger.info("SearchList.searchTrackOrdersHistorical() success...........................");
		return list;
	}
	
	
	public static void main(String[] args) {
		Map<Object, Object> parm = new HashMap<Object, Object>();
		parm.put("url", "121.40.213.248");
		parm.put("port", 12138);
		parm.put("dataBase", "TruckNo1_Lbs");
		parm.put("tableName", "20151013");
		parm.put("UserID", "");
		parm.put("drivingStart", "");
		parm.put("drivingEnd", "");
		List<TrackOrdersHistoricalDTO> list = null;
		try {
			list = searchTrackOrdersHistorical(parm);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (TrackOrdersHistoricalDTO trackOrdersHistoricalDTO : list) {
			System.out.println(trackOrdersHistoricalDTO.getUserID());
		}
	}
}
