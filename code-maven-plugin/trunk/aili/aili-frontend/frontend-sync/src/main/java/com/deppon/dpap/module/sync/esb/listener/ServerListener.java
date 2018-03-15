package com.deppon.dpap.module.sync.esb.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import com.deppon.dpap.module.sync.esb.definition.Configuration;
import com.deppon.dpap.module.sync.esb.header.ESBHeader;
import com.deppon.dpap.module.sync.esb.header.ServiceMessage;
import com.deppon.dpap.module.sync.esb.util.HeaderUtils;

/**
 * 
 * 消息监听基类
 * <p style="display:none">modifyRecord</p>
 * <p style="display:none">version:V1.0,author:ztjie,date:2013-8-29 上午10:11:51,content:TODO </p>
 * @author ztjie
 * @date 2013-8-29 上午10:11:51
 * @since
 * @version
 */
public class ServerListener implements MessageListener {
	
	@Override
	public void onMessage(Message message) {
		ESBHeader header;
		try {
			header = HeaderUtils.fillServiceHeader(message);
			String body = ((TextMessage) message).getText();
			ServiceMessage serviceMessage = new ServiceMessage(header, body);
			Configuration.getServerThreadPool().process(serviceMessage);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
