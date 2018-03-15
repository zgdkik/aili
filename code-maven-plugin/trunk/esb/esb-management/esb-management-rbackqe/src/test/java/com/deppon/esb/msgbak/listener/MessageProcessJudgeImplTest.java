package com.deppon.esb.msgbak.listener;

import javax.jms.JMSException;
import javax.jms.Message;

import com.deppon.esb.management.rbackqe.intface.service.IMessageProcessJudge;

public class MessageProcessJudgeImplTest implements IMessageProcessJudge{

	@Override
	public String judeMessage(Message message) throws JMSException {
		return "QU_FOSS_REQUEST_COM_OUT_BK";
	}

}
