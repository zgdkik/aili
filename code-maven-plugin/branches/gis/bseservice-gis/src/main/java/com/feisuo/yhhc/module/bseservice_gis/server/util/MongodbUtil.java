/**
 * util.java
 * Created at 2015年10月15日
 * Created by zhangjianfeng
 * Copyright (C) 2015 SHANGHAI VOLKSWAGEN, All rights reserved.
 */
package com.feisuo.yhhc.module.bseservice_gis.server.util;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;



/**
 * ClassName: util
 * Description: TODO
 * Author: zhangjianfeng
 * Date: 2015年10月15日
 */
public class MongodbUtil{
	/**
	 * 
	 * Description: 链接数据库(mongodb)
	 * @param url 数据库地址
	 * @param port 端口
	 * @param dataBase 数据库名称
	 * @param tableName 表名(这里只需传日期  如:20151015)
	 * @return
	 * Created by zhangjianfeng 2015年10月15日
	 */
	public static DBCollection connect(String url,int port,String dataBase,String tableName){
		DBCollection collection = null;
		try {
			Mongo mongo = new Mongo(url, port);
			DB db = mongo.getDB(dataBase);
			collection = db.getCollection(tableName);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return collection;
	}
	
}
