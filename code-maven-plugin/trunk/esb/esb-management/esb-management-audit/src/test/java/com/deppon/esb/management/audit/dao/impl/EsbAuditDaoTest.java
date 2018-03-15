/*
 * PROJECT NAME: esb-management-audit
 * PACKAGE NAME: com.deppon.esb.management.audit.dao.impl
 * FILE    NAME: EsbAuditDaoTest.java
 * COPYRIGHT: Copyright(c) 2012 Deppon All Rights Reserved.
 */
package com.deppon.esb.management.audit.dao.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.deppon.esb.management.audit.dao.IEsbAuditDao;
import com.deppon.esb.management.audit.domain.EsbAuditInfo;
import com.deppon.esb.management.audit.view.EsbAuditInfoQueryBean;
import com.deppon.esb.management.audit.view.EsbAuditInfoView;
import com.deppon.esb.management.common.entity.jms.header.AuthInfo;
import com.deppon.esb.management.common.entity.jms.header.EsbHeader;
import com.deppon.esb.management.common.test.DaoDBUnitSupportUnitTests;

/**
 * 审计日志DAO测试
 * 
 * @author HuangHua
 * @date 2013-1-10 下午4:13:58
 */
@ContextConfiguration(locations = { "classpath*:com/deppon/esb/management/audit/META-INF/dao/spring-test.xml" })
@TransactionConfiguration(defaultRollback = true)
public class EsbAuditDaoTest extends DaoDBUnitSupportUnitTests {

	@Resource
	IEsbAuditDao esbAuditDao;

	@Test
	public void test() {
		int start = countRowsInTable("T_ESB2_AUDIT");
		EsbAuditInfo esbAuditInfo = new EsbAuditInfo();
		esbAuditInfo.setCreateTime(new Date());
		esbAuditInfo.setEsbBody("1246578");
		EsbHeader esbHeader = new EsbHeader();
		esbHeader.setVersion("value");
		AuthInfo auth = new AuthInfo();
		auth.setPassword("value");
		auth.setUsername("value");
		esbHeader.setAuthentication(auth);
		esbHeader.setBackServiceCode("value");
		esbHeader.setBusinessDesc1("value");
		esbHeader.setBusinessDesc2("value");
		esbHeader.setBusinessDesc3("value");
		esbHeader.setBusinessId("value");
		esbHeader.setEsbServiceCode("value");
//		esbHeader.setExchangePattern(2);
		esbHeader.setMessageFormat("value");
		esbHeader.setRequestId("value");
		esbHeader.setResponseId("value");
		esbHeader.setResultCode(1);
		esbHeader.setSentSequence(0);
//		esbHeader.setSourceSystem("value");
		esbHeader.setTargetSystem("value");
		esbHeader.setVersion("value");
		esbAuditInfo.setEsbHeader(esbHeader);
		esbAuditInfo.setEsbBody("value");
		int count = esbAuditDao.saveAudit(esbAuditInfo);
		Assert.assertEquals(1, count);
		int end = countRowsInTable("T_ESB2_AUDIT");
		Assert.assertEquals(1, end - start);
	}
	
	@Test
	public void queryEsbAuditLogListTest()throws Exception{
		EsbAuditInfoQueryBean queryBean = creatTestData();
		queryBean.setStart(0);
		queryBean.setLimit(10);
		EsbHeader esbHeader = new EsbHeader();
		queryBean.setEsbHeader(esbHeader);
		List<EsbAuditInfoView> list = esbAuditDao.queryEsbAuditLogList(queryBean);
		System.out.println(list.size());
		Assert.assertTrue(list.size()>0);
		//String  body = esbAuditDao.queryAuditLogBody("36986");
		//Assert.assertNotNull(body);
		//Integer i = esbAuditDao.queryAuditLogCount(queryBean);
		//Assert.assertNotNull(i);
		//Assert.assertTrue(i.intValue()>0);
	}
	
	
	/**
	 * 生成测试查询条件
	 * 
	 */
	public EsbAuditInfoQueryBean creatTestData(){
		EsbAuditInfoQueryBean queryBean = new EsbAuditInfoQueryBean();
		Calendar cal = Calendar.getInstance();
		cal.set(2013, 0, 01);//2013-01-01
		queryBean.setStartTime(cal.getTime());
		cal.set(2013, 0,20);//2013-01-20
		queryBean.setEndTime(cal.getTime());
		queryBean.setStart(1);
		queryBean.setLimit(20);
		return queryBean;
	}
	
	
	@Test
	public void queryEsbAuditLogListTest1()throws Exception{
		EsbAuditInfoQueryBean queryBean = new EsbAuditInfoQueryBean();
		queryBean.setStart(0);
		queryBean.setLimit(25);
		EsbHeader esbHeader = new EsbHeader();
		queryBean.setEsbHeader(esbHeader);
		List<EsbAuditInfoView> list = esbAuditDao.queryEsbAuditLogList(queryBean);
		System.out.println(list.size());
		Assert.assertTrue(list.size()>0);
/*		String  body = esbAuditDao.queryAuditLogBody("36986");
		Assert.assertNotNull(body);
		Integer i = esbAuditDao.queryAuditLogCount(queryBean);
		Assert.assertNotNull(i);
		Assert.assertTrue(i.intValue()>0);*/
	}
	

}
