package org.hbhk.aili.jms.server.sender;

import javax.jms.TextMessage;


/**
 * 
 * @Description: jms增强处理
 * @author 何波
 * @date 2015年3月11日 上午10:05:24 
 *
 */
public interface IJmsSender {
	void sendJms(String queueName, TextMessage msg);
}
