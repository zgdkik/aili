package org.hbhk.aili.esb.server.common.jmsservice.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hbhk.aili.esb.common.constant.ESBServiceConstant;
import org.hbhk.aili.esb.server.common.entity.ESBHeader;
import org.hbhk.aili.esb.server.common.jmsservice.ISendMessageService;
import org.hbhk.aili.esb.server.common.log.EsbLogTemplate;
import org.hbhk.aili.esb.server.common.log.audit.AuditInfo;


/**
 * 
 * 
 * @author Administrator
 * @date 2013-4-16 上午10:11:14
 */
public  class SendMessageImpl implements ISendMessageService{
	/**
	 * 发送模板
	 */
	protected EsbLogTemplate esbLogTemplate;
	
	@Override
	public void send( ESBHeader esbHeader,  String msg) {
		
		esbLogTemplate.sendLog( esbHeader,null,msg);
	}
	
	public void send(List<AuditInfo> list){
		for(AuditInfo info:list){
			//为了让审计日志中穿入审计日志创建日期，需要传递一个日期对象。
			Map<String,Object> header = new HashMap<String,Object>();
			header.put(ESBServiceConstant.ESB_LOGMSG_CREATETIME, new Date());
			esbLogTemplate.sendLog(info.getHeader(), header, info.getBody());			
		}
	}

	public EsbLogTemplate getEsbLogTemplate() {
		return esbLogTemplate;
	}

	public void setEsbLogTemplate(EsbLogTemplate esbLogTemplate) {
		this.esbLogTemplate = esbLogTemplate;
	}
	
}
