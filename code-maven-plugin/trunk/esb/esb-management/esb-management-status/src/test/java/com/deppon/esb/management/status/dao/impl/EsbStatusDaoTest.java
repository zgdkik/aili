/*
 * PROJECT NAME: esb-management-status
 * PACKAGE NAME: com.deppon.esb.management.status.dao.impl
 * FILE    NAME: EsbStatusDaoTest.java
 * COPYRIGHT: Copyright(c) 2012 Deppon All Rights Reserved.
 */
package com.deppon.esb.management.status.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.deppon.esb.management.common.entity.jms.header.StatusInfo;
import com.deppon.esb.management.common.test.DaoDBUnitSupportUnitTests;
import com.deppon.esb.management.status.dao.IEsbStatusDao;
import com.deppon.esb.management.status.domain.EsbStatusInfo;
import com.deppon.esb.management.status.view.StatusQueryBean;
import com.deppon.esb.management.status.view.StatusView;

/**
 * 状态信息处理DAO测试
 * @author HuangHua
 * @date 2013-1-10 下午1:40:54
 */
@ContextConfiguration(locations = { "classpath*:com/deppon/esb/management/status/META-INF/dao/spring-test.xml" })
@TransactionConfiguration(defaultRollback = true)
public class EsbStatusDaoTest extends DaoDBUnitSupportUnitTests {

	@Resource
	IEsbStatusDao esbStatusDao;

	/**
	 * Test method for
	 * {@link com.deppon.esb.management.status.dao.impl.EsbStatusDao#saveStatus(java.util.List)}
	 * .
	 */
	@Test
	public void testSaveStatus() {
		int start = countRowsInTable("T_ESB2_STATUS");
		List<EsbStatusInfo> esbStatusInfos = new ArrayList<EsbStatusInfo>();
		EsbStatusInfo esbStatusInfo = new EsbStatusInfo();
		esbStatusInfo.setCreateTime(new Date());
		esbStatusInfo.setBackServiceCode("backServiceCode");
		esbStatusInfo.setBusinessId("businessId");
		esbStatusInfo.setEsbServiceCode("esbServiceCode");
		esbStatusInfo.setRequestId("requestId");
		esbStatusInfo.setResponseId("responseId");
		esbStatusInfo.setSourceSystem("sourceSystem");
		// esbStatusInfo.setTargetSystem("targetSystem");
		StatusInfo statusInfo = new StatusInfo();
		statusInfo.setStatusId("value");
		statusInfo.setTimeStamp(System.currentTimeMillis());
		esbStatusInfo.setStatusInfo(statusInfo);
		esbStatusInfos.add(esbStatusInfo);
		esbStatusInfos.add(esbStatusInfo);
		esbStatusDao.saveStatus(esbStatusInfos);
		int end = countRowsInTable("T_ESB2_STATUS");
		Assert.assertEquals(2, end-start);
	}
	
	@Test
	public void testGetNoteCompleteRecords(){
		List<EsbStatusInfo> esbStatusInfos = esbStatusDao.getNotCompleteRecords(10);
		Assert.assertNotNull(esbStatusInfos);
		Assert.assertEquals(1, esbStatusInfos.size());
	}
	
	@Test
	public void queryStatusListTest(){
		StatusQueryBean bean = new StatusQueryBean();
		bean.setStart(0);
		bean.setLimit(10);
		bean.setEsbServiceCode("ESB_UUMS2ESB_SEND_ROLEINFO");
		bean.setReqid("reqid");
		bean.setBizid("dddddddddd");
		bean.setResid("resid");
		bean.setSourceSys("UUMS");
		bean.setTargetServiceCode("FOSS_ESB2FOSS_SEND_ROLEINFO");
		bean.setTargetSys("FOSS");
		List<StatusView>list = esbStatusDao.queryStatusView(bean);
		Assert.assertNotNull(list);
		Assert.assertTrue(list.size()==1);
	}
	
	@Test
	public void queryStatusListCount(){
		StatusQueryBean bean = new StatusQueryBean();
		bean.setStart(0);
		bean.setLimit(10);
		bean.setEsbServiceCode("ESB_UUMS2ESB_SEND_ROLEINFO");
		bean.setReqid("reqid");
		bean.setBizid("dddddddddd");
		bean.setResid("resid");
		bean.setSourceSys("UUMS");
		bean.setTargetServiceCode("FOSS_ESB2FOSS_SEND_ROLEINFO");
		bean.setTargetSys("FOSS");
		Integer count = esbStatusDao.queryStatusViewCount(bean);
		Assert.assertTrue(1==count);
	}

	//@Test
	public void queryOriginalStatus(){
		StatusQueryBean bean = new StatusQueryBean();
		bean.setStart(1);
		bean.setLimit(10);
		bean.setEsbServiceCode("ESB_UUMS2ESB_SEND_ROLEINFO");
//		bean.setTargetServiceCode("FOSS_ESB2FOSS_SEND_ROLEINFO");
//		bean.setBizid("dddddddddd");
//		bean.setReqid("reqid2");
//		bean.setResid("resid2");
//		bean.setSourceSys("UUMS");
//		bean.setTargetSys("FOSS");
		List<EsbStatusInfo> list= esbStatusDao.queryOriginalStatus(bean);
		Assert.assertNotNull(list);
		System.out.println(list.get(0).getEsbServiceCode());
		Assert.assertTrue(list.size()==1);
	}
	
	@Test
	public void testUpdate(){
		List<String> list = new ArrayList<String>();
		list.add("36986");
		int count = esbStatusDao.updateStatusByIds(list, 2);
		Assert.assertEquals(1, count);
	}
}
