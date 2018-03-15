package com.deppon.esb.management.rbackqe.intface.service;

import javax.jms.JMSException;
import javax.jms.Message;

public interface IMessageProcessJudge {
	
	//获取消息应该重新发送到什么队列
	String judeMessage(Message message) throws JMSException;
	
	
}
