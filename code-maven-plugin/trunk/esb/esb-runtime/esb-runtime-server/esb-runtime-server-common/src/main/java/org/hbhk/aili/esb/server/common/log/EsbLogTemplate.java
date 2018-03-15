package org.hbhk.aili.esb.server.common.log;

/*
 * PROJECT NAME: esb-runtime-common
 * PACKAGE NAME: com.deppon.esb.common.log
 * FILE    NAME: EsbLogTemplate.java
 * COPYRIGHT: Copyright(c) 2012 Deppon All Rights Reserved.
 */

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.aili.esb.server.common.entity.ESBHeader;
import org.hbhk.aili.esb.server.common.exception.LogSendException;
import org.hbhk.aili.esb.server.common.utils.HeaderUtil;
import org.hbhk.aili.esb.server.common.utils.jms.EsbJmsSender;
import org.hbhk.aili.esb.server.common.utils.jms.EsbLogMessage;

/**
 * 
 作 者： huanghua 最后修改时间： 2013-4-15 描 述：日志发送组件的API，里面有方法逻辑主要是依赖JMS、DB等发送方式。 更新纪录：
 * 1、davidcun修改，修改sendLog方式，分离不同逻辑实现到不同的私有方法里。
 */
public class EsbLogTemplate {

	protected static final Log LOGGER = LogFactory.getLog(EsbLogTemplate.class);

	// JMS发送组件
	private EsbJmsSender esbJmsSender;

	// DB发送组件
	private ISender dbSender;

	private boolean jmsValid = true;
	
	//这两个值的设置必须小于esbJmsSender及dbSender的内存队列大小
	private int jmsSize=50000;
	private int dbSize=100000;

	public void sendLog(ESBHeader esbHeader, Map<String, Object> header,
			String body) {

		// 如果MQ的queue大小达到50%,并且数据库的queue没有满,则保存到数据库

		if (esbJmsSender.getQueueSize()< getJmsSize()  && isJmsValid()) {
			// 保存到队列
			save2Jms(esbHeader, header, body);
			
		} else if (dbSender.getQueueSize() < getDbSize()) {
			// 保存到数据库
			save2Db(esbHeader, header, body);

		} else {
			// MQ和数据库都不可用的情况下,把日志保存在本地磁盘文件里//TODO
			save2File(esbHeader, header, body);
		}
	}

	/**
	 * 
	 * @descriptor:通过JMS方式发送日志消息的实现
	 * @author davidcun
	 * @date 2013-4-26 下午03:25:52
	 * @param esbHeader
	 *            消息头消息。
	 * @param header
	 *            针对Map类型的扩展，如果消息头协议修改后，多余的参数克存放在map里
	 * @param body
	 *            具体的消息内容
	 */
	private void save2Jms(ESBHeader esbHeader, Map<String, Object> header,
			String body) {
		try {
			esbJmsSender.send(esbHeader, header, body);
		} catch (LogSendException e) {
			save2Db(esbHeader, header, body);
		}
	}

	/**
	 * 
	 * @descriptor:通过DB方式发送日志消息的实现
	 * @author davidcun
	 * @date 2013-4-26 下午03:25:52
	 * @param esbHeader
	 *            消息头消息。
	 * @param header
	 *            针对Map类型的扩展，如果消息头协议修改后，多余的参数克存放在map里
	 * @param body
	 *            具体的消息内容
	 */
	private void save2Db(ESBHeader esbHeader, Map<String, Object> header,
			String body) {
		try {
			dbSender.send(esbHeader, header, body);
		} catch (LogSendException e) {
			save2File(esbHeader, header, body);
		}
	}

	/**
	 * 
	 * @descriptor:通过DB方式发送日志消息的实现
	 * @author davidcun
	 * @date 2013-4-26 下午03:25:52
	 * @param esbHeader
	 *            消息头消息。
	 * @param header
	 *            针对Map类型的扩展，如果消息头协议修改后，多余的参数克存放在map里
	 * @param body
	 *            具体的消息内容
	 */
	private void save2File(ESBHeader esbHeader, Map<String, Object> header,
			String body) {
		EsbLogMessage msg = new EsbLogMessage();
		Map<String, Object> headerMap = HeaderUtil.esbHeader2Map(esbHeader);
		if (headerMap == null) {
			headerMap = new HashMap<String, Object>();
		}
		if (header != null) {
			headerMap.putAll(header);
		}
		msg.setHeader(headerMap);
		msg.setBody(body);
		// 以固定格式输出,把msg转换成json
		LOGGER.info("save message to log file:"+msg);
	}

	public EsbJmsSender getEsbJmsSender() {
		return esbJmsSender;
	}

	public void setEsbJmsSender(EsbJmsSender esbJmsSender) {
		this.esbJmsSender = esbJmsSender;
	}

	public ISender getDbSender() {
		return dbSender;
	}

	public void setDbSender(ISender dbSender) {
		this.dbSender = dbSender;
	}

	public int getJmsSize() {
		return jmsSize;
	}

	public void setJmsSize(int jmsSize) {
		this.jmsSize = jmsSize;
	}

	public int getDbSize() {
		return dbSize;
	}

	public void setDbSize(int dbSize) {
		this.dbSize = dbSize;
	}
	public boolean isJmsValid() {
		return jmsValid;
	}

	public void setJmsValid(boolean jmsValid) {
		this.jmsValid = jmsValid;
	}
}
