package org.hbhk.aili.route.jms.server.common.log;

import java.util.Map;

import org.hbhk.aili.route.jms.server.common.ESBHeader;

/**
 * 
         作        者： davidcun
         最后修改时间： 2013-4-20
         描        述：定义日志发送接口，包括具体的行为有：发送行为、待发送消息数量获取行为、消息存储容量获取行为
         更新纪录：
 */
public interface ISender {

	/**
	 * 
	 * @descriptor:消息发送行为声明
	 * @author davidcun
	 * @date 2013-4-20 下午03:31:22
	 * @param
	 * @return
	 */
	public void send(ESBHeader esbHeader, Map<String, Object> header,
			String body) ;
	
	/**
	 * 
	 * @descriptor:当前消息缓存容量
	 * @author davidcun
	 * @date 2013-4-20 下午03:31:45
	 * @param
	 * @return
	 */
	public int getQueueSize();
	
	/**
	 * 
	 * @descriptor:消息缓存容量限制
	 * @author davidcun
	 * @date 2013-4-20 下午03:32:00
	 * @param
	 * @return
	 */
	public int getQueueCapacity();
}
