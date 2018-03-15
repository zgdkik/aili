package com.deppon.dpap.module.sync.server;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.dpap.module.sync.business.jms.SyncUserInfoRequest;
import com.deppon.dpap.module.sync.business.jms.UserInfo;
import com.deppon.dpap.module.sync.business.transfer.SyncUserInfoRequestTrans;
import com.deppon.dpap.module.sync.esb.exception.ConvertException;
import com.deppon.dpap.module.sync.esb.header.ESBHeader;
import com.deppon.dpap.module.sync.esb.sender.DefaultSender;

public class TestUumsUserListener {
	ApplicationContext context = null;

	@Before
	public void before() {
		context = new ClassPathXmlApplicationContext("spring-jms.xml");
	}

	@Test
	public void testAdd() throws ConvertException {
		DefaultSender jmsSender = (DefaultSender) context.getBean("jmsSender");
		ESBHeader esbHeader = getESBHeader();
		SyncUserInfoRequest req = new SyncUserInfoRequest();
		List<UserInfo> us = new ArrayList<UserInfo>();
		UserInfo u = new UserInfo();
		u.setEmpCode("089115");
		u.setDesPassword("135246");
		u.setUserName("hbhk");
		u.setChangeType("1");
		u.setIsActive(true);
		us.add(u);
		req.setUserList(us);
		SyncUserInfoRequestTrans tr = new SyncUserInfoRequestTrans();
		String body = tr.fromMessage(req);
		System.out.println(body);
		jmsSender.sendJms(esbHeader, body);

	}

	@Test
	public void testUpdate() throws ConvertException {
		DefaultSender jmsSender = (DefaultSender) context.getBean("jmsSender");
		ESBHeader esbHeader = getESBHeader();
		SyncUserInfoRequest req = new SyncUserInfoRequest();
		List<UserInfo> us = new ArrayList<UserInfo>();
		UserInfo u = new UserInfo();
		u.setOrgCode("W01");
		u.setEmpCode("089115");
		u.setDesPassword("135246");
		u.setUserName("hbhk");
		u.setChangeType("2");
		u.setIsActive(false);
		us.add(u);
		req.setUserList(us);
		SyncUserInfoRequestTrans tr = new SyncUserInfoRequestTrans();
		String body = tr.fromMessage(req);
		System.out.println(body);
		jmsSender.sendJms(esbHeader, body);

	}

	@Test
	public void testDel() throws ConvertException {
		DefaultSender jmsSender = (DefaultSender) context.getBean("jmsSender");
		ESBHeader esbHeader = getESBHeader();
		SyncUserInfoRequest req = new SyncUserInfoRequest();
		List<UserInfo> us = new ArrayList<UserInfo>();
		UserInfo u = new UserInfo();
		u.setOrgCode("W01");
		u.setEmpCode("089115");
		u.setDesPassword("135246");
		u.setUserName("hbhk");
		u.setChangeType("3");
		u.setIsActive(false);
		us.add(u);
		req.setUserList(us);
		SyncUserInfoRequestTrans tr = new SyncUserInfoRequestTrans();
		String body = tr.fromMessage(req);
		System.out.println(body);
		jmsSender.sendJms(esbHeader, body);
	}

	public ESBHeader getESBHeader() {
		ESBHeader esbHeader = new ESBHeader();
		esbHeader.setBusinessId("bus00001");
		esbHeader.setEsbServiceCode("WDGH_ESB2WDGH_WDGH_SYNC_USER");
		esbHeader.setBackServiceCode("WDGH_ESB2WDGH_WDGH_SYNC_USER");
		esbHeader.setExchangePattern(2);
		esbHeader.setMessageFormat("XML");
		esbHeader.setRequestId(UUID.randomUUID().toString());
		esbHeader.setSourceSystem("UUMS");
//		esbHeader.setVersion("1.0");
		return esbHeader;
	}
}
