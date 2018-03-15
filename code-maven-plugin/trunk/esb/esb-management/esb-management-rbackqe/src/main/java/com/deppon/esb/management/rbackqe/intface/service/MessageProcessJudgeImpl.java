package com.deppon.esb.management.rbackqe.intface.service;

import javax.jms.JMSException;
import javax.jms.Message;

import com.deppon.esb.management.common.constant.ESBConstants;
import com.deppon.esb.management.common.constant.ESBServiceConstant;
import com.deppon.esb.management.rbackqe.config.IServiceConfigLoader;
import com.deppon.esb.management.svccfg.domain.EsbSvcPoint;

public class MessageProcessJudgeImpl implements IMessageProcessJudge {

	private IServiceConfigLoader configLoader;

	public IServiceConfigLoader getConfigLoader() {
		return configLoader;
	}

	public void setConfigLoader(IServiceConfigLoader configLoader) {
		this.configLoader = configLoader;
	}

	@Override
	public String judeMessage(Message message) throws JMSException {
		String queueName = message.getJMSDestination().toString();
		if (ESBConstants.QUEUE_APP_BK_NAME.equals(queueName)) {
			return queueAppName(message);
		} else if (ESBConstants.QUEUE_ESB_BK_NAME.equals(queueName)) {
			return queueEsbName(message);
		} else if (ESBConstants.QUEUE_AUDITLOG_BK_NAME.equals(queueName)) {
			return queueAuditLog(message);
		} else if (ESBConstants.QUEUE_FAILURE_BK_NAME.equals(queueName)) {
			return queueFailureName(message);
		} else if (ESBConstants.QUEUE_EXCEPTION_BK_NAME.equals(queueName)) {
			return queueExceptionName(message);
		} else if (ESBConstants.QUEUE_STATUS_BK_NAME.equals(queueName)) {
			return queueStatusName(message);
		} else {
			return queueName(message);
		}
	}

	protected String queueName(Message message) throws JMSException {
		// TODO Auto-generated method stub
		return null;
	}

	protected String queueStatusName(Message message) throws JMSException {
		if (message != null) {
			return ESBConstants.QUEUE_STATUS_NAME;
		}
		return null;
	}

	protected String queueExceptionName(Message message) throws JMSException {
		if (message != null) {
			return ESBConstants.QUEUE_EXCEPTION_NAME;
		}
		return null;
	}

	protected String queueFailureName(Message message) throws JMSException {
		if (message != null) {
			return ESBConstants.QUEUE_FAILURE_NAME;
		}
		return null;
	}

	protected String queueAuditLog(Message message) throws JMSException {
		if (message != null) {
			return ESBConstants.QUEUE_AUDITLOG_NAME;
		}
		return null;
	}

	protected String queueEsbName(Message message) throws JMSException {
		String responseId = message.getStringProperty(ESBServiceConstant.RESPONSE_ID);
		String sourceSystem = message.getStringProperty(ESBServiceConstant.SOURCE_SYSTEM);
		String targetSystem = message.getStringProperty(ESBServiceConstant.TARGET_SYSTEM);
//		String backServiceCode = message.getStringProperty(ESBServiceConstant.BACK_SERVICE_CODE);
//		String esbServiceCode = message.getStringProperty(ESBServiceConstant.ESB_SERVICE_CODE);
		if (sourceSystem != null) {
			if (responseId == null) {
//				EsbSvcPoint esbSvcPoint = configLoader.get(backServiceCode).get(0);
//				String queueName = esbSvcPoint.getEsbRequestAddr();
//				return "QU_"+queueName.substring(queueName.indexOf("RQ_"));
				if(sourceSystem.equals("FOSS-CRM") || sourceSystem.equals("FOSS-CMS")){
					return "QU_FOSS_REQUEST_COM_OUT";
				}else{
					return "QU_"+sourceSystem+"_REQUEST_COM_OUT";
				}
			} else {
//				EsbSvcPoint esbSvcPoint = configLoader.get(esbServiceCode).get(0);
//				String queueName =  esbSvcPoint.getEsbResponseAddr();
//				return "QU_"+queueName.substring(queueName.indexOf("RQ_"));
				if(targetSystem.equals("FOSS-CRM") || targetSystem.equals("FOSS-CMS")){
					return "QU_FOSS_RESPONSE_COM_OUT";
				}else{
					return "QU_"+targetSystem+"_RESPONSE_COM_OUT";
				}
			}
		}
		return null;
	}

	protected String queueAppName(Message message) throws JMSException {
		String backServiceCode = message.getStringProperty(ESBServiceConstant.BACK_SERVICE_CODE);
		String esbServiceCode = message.getStringProperty(ESBServiceConstant.ESB_SERVICE_CODE);
		String responseId = message.getStringProperty(ESBServiceConstant.RESPONSE_ID);
		if (backServiceCode != null) {
			if (responseId == null) {
				EsbSvcPoint esbSvcPoint = configLoader.get(backServiceCode).get(0);
				return esbSvcPoint.getEsbRequestAddr();
			} else {
				EsbSvcPoint esbSvcPoint = configLoader.get(esbServiceCode).get(0);
				return esbSvcPoint.getEsbResponseAddr();
			}
		}
		return null;
	}
}
