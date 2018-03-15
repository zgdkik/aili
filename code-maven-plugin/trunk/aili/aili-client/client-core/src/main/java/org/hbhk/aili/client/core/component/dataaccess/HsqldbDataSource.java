package org.hbhk.aili.client.core.component.dataaccess;

import java.io.File;

/**
 *	此接口定义了数据源的一些配置信息。
 */
public interface HsqldbDataSource {

	/**
	 * 定义数据原配置文件的位置
	 */
	public static final String location        = System.getProperty("foss_db_home");
	
	public static final String encrypted       = "crypt_key=604a6105889da65326bf35790a923932;crypt_type=blowfish;hsqldb.default_table_type=memory";
	/**
	 * 定义链接数据库的url
	 */
	//public static final String DB_URL          = "jdbc:"+"hsqldb:"+"file:"+location+";"+encrypted+File.separator+"foss";
	public static final String DB_URL          = "jdbc:"+"hsqldb:"+"file:"+location+ File.separator +"foss;"+encrypted;
	/**
	 * 定义连接数据库的用户名
	 */
	public static final String DB_USERNAME     = "SA";
	/**
	 * 定义链接数据库的用户名密码
	 */
	public static final String DB_PASSWORD     = "";
	/**
	 * 定义JDBC驱动器的类名
	 */
	public static final String DB_DRIVER       = "org.hsqldb.jdbc.JDBCDriver";
}
