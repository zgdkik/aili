package org.hbhk.aili.route.jms.server.common.jmsservice;

import java.util.List;

import org.hbhk.aili.route.jms.server.common.ESBHeader;
import org.hbhk.aili.route.jms.server.common.log.audit.AuditInfo;

/**
 * 
 * 信息发送
 * @author qiancheng
 * @date 2013-1-12 下午1:45:25
 */
public interface ISendMessageService {
	/**
	 * @author qiancheng
	 * @param 
	 * @date 2013-1-12 下午1:45:40
	 * @return
	 */
	public void send(ESBHeader esbHeader ,String msg);
	public void send(List<AuditInfo> list);
}
