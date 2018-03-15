package org.hbhk.aili.jms.server.listener;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

import javax.jms.ConnectionFactory;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.aili.jms.server.main.JmsLogUi;
import org.hbhk.aili.jms.server.sender.IJmsSender;
import org.hbhk.aili.jms.server.sender.JmsSender;

/**
 * 消息监听基类
 */
public class BusinessListener implements MessageListener {
	private Log log = LogFactory.getLog(getClass());

	private IJmsSender jmsSender;

	private String type;

	private String brokerUrl;

	private String qmn;
	private String hostName;
	private int port;
	private int ccsid;
	private String channelName;

	private String sendDestinationName;

	private ConnectionFactory cf;
	private int count = 1;

	@Override
	public void onMessage(Message message) {
		try {
			StringBuffer str = new StringBuffer();
			str.append("处理第:" + count + "条消息 \r\n");
			log.info("接送消息:" + message);
			str.append("接送消息:" + message + "\r\n");
			if (jmsSender == null) {
				jmsSender = new JmsSender(cf);
			}
			log.info("发送消息开始:" + sendDestinationName);
			str.append("发送消息开始: 队列名称" + sendDestinationName+"\r\n");
			jmsSender.sendJms(sendDestinationName, (TextMessage) message);
			log.info("发送消息结束:" + sendDestinationName);
			str.append("发送消息结束:队列名称" + sendDestinationName+"\r\n");
			Queue<String>  logs = JmsLogUi.logMap.get(this.toString());
			if(logs==null){
				logs = new LinkedBlockingDeque<String>();
				JmsLogUi.logMap.put(this.toString(), logs);
			}
			JmsLogUi.logMap.get(this.toString()).add(str.toString());
			log.info("消息处理:" + str.toString());
			count++;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	public IJmsSender getJmsSender() {
		return jmsSender;
	}

	public void setJmsSender(IJmsSender jmsSender) {
		this.jmsSender = jmsSender;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBrokerUrl() {
		return brokerUrl;
	}

	public void setBrokerUrl(String brokerUrl) {
		this.brokerUrl = brokerUrl;
	}

	public String getQmn() {
		return qmn;
	}

	public void setQmn(String qmn) {
		this.qmn = qmn;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getCcsid() {
		return ccsid;
	}

	public void setCcsid(int ccsid) {
		this.ccsid = ccsid;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getSendDestinationName() {
		return sendDestinationName;
	}

	public void setSendDestinationName(String sendDestinationName) {
		this.sendDestinationName = sendDestinationName;
	}

	public ConnectionFactory getCf() {
		return cf;
	}

	public void setCf(ConnectionFactory cf) {
		this.cf = cf;
	}

}
