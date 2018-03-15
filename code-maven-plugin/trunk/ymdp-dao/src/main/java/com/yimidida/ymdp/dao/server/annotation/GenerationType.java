package com.yimidida.ymdp.dao.server.annotation;

/**
 * 
 * @Description: mybatis增强处理
 * @author 何波
 * @date 2015年3月11日 上午10:05:24 
 *
 */
public interface GenerationType {
	String auto_increment = "auto_increment";
	String sequence = "sequence";
	String uuid = "uuid";
	String custom = "custom";
}
