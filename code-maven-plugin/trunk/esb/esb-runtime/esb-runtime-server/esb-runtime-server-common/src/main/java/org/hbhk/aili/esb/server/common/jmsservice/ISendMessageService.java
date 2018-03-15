package org.hbhk.aili.esb.server.common.jmsservice;

import java.util.List;

import org.hbhk.aili.esb.server.common.entity.ESBHeader;
import org.hbhk.aili.esb.server.common.log.audit.AuditInfo;

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
