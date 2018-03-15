/*
 * PROJECT NAME: esb-management-web
 * PACKAGE NAME: com.deppon.esb.management.web.action.verification
 * FILE    NAME: VerifyAction.java
 * COPYRIGHT: Copyright(c) 2012 Deppon All Rights Reserved.
 */
package com.deppon.esb.management.web.action.verification;

import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.sql.DataSource;

import org.apache.struts2.dispatcher.DefaultActionSupport;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.deppon.esb.management.verification.db.service.impl.VerifyDbConnService;
import com.deppon.esb.management.verification.mq.service.impl.VerifyMqConnService;

/**
 * 
 * @author HuangHua
 * @date 2013-2-28 下午5:01:01
 */
@Controller("verifyAction")
@Scope("prototype")
public class VerifyAction extends DefaultActionSupport {

	private static final long serialVersionUID = 2379899843985447188L;

	@Resource
	private VerifyMqConnService verifyMqConnService;

	@Resource
	private VerifyDbConnService verifyDbConnService;

	@Resource
	private DataSource esbmgmtdb;

	@Resource
	private ConnectionFactory jmsConnectionFactory;
	
	private String msg;

	public String verifyMq() {
		boolean result = verifyMqConnService.isConnect(jmsConnectionFactory);
		if(result){
			msg = "MQ已经连接.o(≧v≦)o~~好棒";
		}else{
			msg = "MQ连接失败了?我靠( ‵o′)凸";
		}
		return SUCCESS;
	}

	public String verifyDb() {
		boolean result = verifyDbConnService.isConnect(esbmgmtdb);
		if(result){
			msg = "DB已经连接.( ^_^ )不错嘛";
		}else{
			msg = "DB连接失败了?(ˇˍˇ） 想～";
		}
		return SUCCESS;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
