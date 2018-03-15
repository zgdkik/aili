package org.hbhk.aili.esb.server.common.log.audit;

import javax.jms.JMSException;

import org.apache.camel.Exchange;
import org.hbhk.aili.esb.common.exception.ESBRunTimeException;

/**
 * 审计日志接口, 审计日志的消息就是路由中的消息.
 * 
 * @author HuangHua
 */
public interface IAuditLogService {
	
	/**
	 * 保存审计日志.
	 * 
	 * @param exchange
	 *            the exchange
	 * @throws ESBRunTimeException
	 *             the eSB run time exception
	 * @throws JMSException 
	 */
	public void saveAudit(Exchange exchange) throws ESBRunTimeException, JMSException;

}
