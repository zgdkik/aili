package com.deppon.esb.management.svccfg.dao;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.deppon.esb.management.common.test.DaoDBUnitSupportUnitTests;
import com.deppon.esb.management.svccfg.domain.SvcPointRelationInfo;

//钱老板，下次记得写上断言,如saveSvcpointRelationTest(),其他的自己加一下;并且把没有引用的类去掉，减少没有必要的惊叹号。。。。。
@ContextConfiguration(locations = { "classpath*:com/deppon/esb/management/svccfg/META-INF/dao/spring-test.xml" })
@TransactionConfiguration(defaultRollback = true)
public class SvcPointRelationDaoTest extends DaoDBUnitSupportUnitTests {

	@Autowired
	private ISvcPointRelationDao svcPointRelationDao;

	/*
	 * =====================svcpointRelation测试
	 */
	@Test
	public void saveSvcpointRelationTest() {

		int count = countRowsInTable("T_ESB_SVCPOINT_RELATION");
		SvcPointRelationInfo svcPointRelationInfo = new SvcPointRelationInfo();
		svcPointRelationInfo.setFrontSvcCode("CRM_Order");
		svcPointRelationInfo.setBackSvcCode("ERP_Order");
		svcPointRelationDao.addRelation(svcPointRelationInfo);
		int count1 = countRowsInTable("T_ESB_SVCPOINT_RELATION");
		Assert.assertEquals(1, count1 - count);
	}

	@Test
	public void updateSvcpointRelationTest() {
		SvcPointRelationInfo svcPointRelationInfo = new SvcPointRelationInfo();
		svcPointRelationInfo.setFrontSvcCode("CRM_Order");
		svcPointRelationInfo.setBackSvcCode("ERP_Order");
		svcPointRelationInfo.setId("1");
		svcPointRelationDao.updateRelation(svcPointRelationInfo);
	}

	@Test
	public void deleteSvcpointRelationTest() {
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		Integer count = svcPointRelationDao.deleteRelation(list);
		Assert.assertTrue(0== count);
	}

	@Test
	public void querySvcpointRelationTest() {
		String frontSvcCode ="CRM2OA_TEST";
		List<SvcPointRelationInfo> list = svcPointRelationDao.getSvcpointRelation(frontSvcCode);
		Assert.assertEquals(0, list.size());
	}
	@Test
	public void isSvcpointRelationExistTest(){
		SvcPointRelationInfo info = new SvcPointRelationInfo();
		info.setBackSvcCode("1");
		info.setFrontSvcCode("2");
		Boolean flag =svcPointRelationDao.isExistSvcPointRelation(info);
		Assert.assertFalse(flag);
	}
}
