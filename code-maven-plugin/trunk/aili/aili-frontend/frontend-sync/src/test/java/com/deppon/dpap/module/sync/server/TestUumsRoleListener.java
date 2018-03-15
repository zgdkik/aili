package com.deppon.dpap.module.sync.server;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.dpap.module.sync.business.jms.SyncRoleRequest;
import com.deppon.dpap.module.sync.business.transfer.SyncRoleRequestTrans;
import com.deppon.dpap.module.sync.esb.exception.ConvertException;
import com.deppon.dpap.module.sync.esb.header.ESBHeader;
import com.deppon.dpap.module.sync.esb.sender.DefaultSender;

public class TestUumsRoleListener {

	ApplicationContext context = null;

	@Before
	public void before() {
		context = new ClassPathXmlApplicationContext("spring-jms.xml");
	}

	@Test
	public void testAdd() throws ConvertException {
		DefaultSender jmsSender = (DefaultSender) context.getBean("jmsSender");
		ESBHeader esbHeader = getESBHeader();
		SyncRoleRequest req = new SyncRoleRequest();
		req.setRoleid("r0asd01asdasdasdasd");
		req.setRoleName("比超级管理员大");
		// 角色编码
		req.setRoleStandardNumber("r0011");
		req.setOperateType("1");
		SyncRoleRequestTrans tr = new SyncRoleRequestTrans();
		String body = tr.fromMessage(req);
		System.out.println(body);
		jmsSender.sendJms(esbHeader, body);

	}

	@Test
	public void testUpdate() throws ConvertException {
		DefaultSender jmsSender = (DefaultSender) context.getBean("jmsSender");
		ESBHeader esbHeader = getESBHeader();
		SyncRoleRequest req = new SyncRoleRequest();
		req.setRoleid("r0asd01asdasdasdasd");
		req.setRoleName("比超级管理员大一点");
		// 角色编码
		req.setRoleStandardNumber("r001");
		req.setOperateType("2");
		SyncRoleRequestTrans tr = new SyncRoleRequestTrans();
		String body = tr.fromMessage(req);
		System.out.println(body);
		jmsSender.sendJms(esbHeader, body);

	}

	@Test
	public void testDel() throws ConvertException {
		DefaultSender jmsSender = (DefaultSender) context.getBean("jmsSender");
		ESBHeader esbHeader = getESBHeader();
		SyncRoleRequest req = new SyncRoleRequest();
		req.setRoleid("4863d6cca0ff47e5a35f64d081ee2b1a");
		req.setRoleName("比超级管理员大");
		// 角色编码
		req.setRoleStandardNumber("r0011");
		req.setOperateType("3");
		SyncRoleRequestTrans tr = new SyncRoleRequestTrans();
		String body = tr.fromMessage(req);
		System.out.println(body);
		jmsSender.sendJms(esbHeader, body);

	}

	public ESBHeader getESBHeader() {
		ESBHeader esbHeader = new ESBHeader();
		esbHeader.setBusinessId("bus00001");
		esbHeader.setEsbServiceCode("WDGH_ESB2WDGH_WDGH_SYNC_ROLE");
		esbHeader.setBackServiceCode("WDGH_ESB2WDGH_WDGH_SYNC_ROLE");
		esbHeader.setExchangePattern(2);
		esbHeader.setMessageFormat("XML");
		esbHeader.setRequestId(UUID.randomUUID().toString());
		esbHeader.setSourceSystem("UUMS");
		esbHeader.setVersion("1.0");
		return esbHeader;
	}
}
