package com.deppon.esb.management.alert.dao;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.deppon.esb.management.alert.domain.QueueThresholdInfo;
import com.deppon.esb.management.alert.domain.view.QueueQueryBean;
import com.deppon.esb.management.common.test.DaoDBUnitSupportUnitTests;

@ContextConfiguration(locations = { "classpath*:com/deppon/esb/management/alert/META-INF/dao/spring-test.xml" })
@TransactionConfiguration(defaultRollback =true)
public class QueueThresholdDaoTest extends DaoDBUnitSupportUnitTests {

	@Autowired
	IQueueThresholdDao queueThresholdDao;


	/**
	 * 查询测试
	 */
	@Test
	public void queryTest(){
		QueueQueryBean bean = new QueueQueryBean();
		bean.setQueue("queue");
		bean.setStart(0);
		bean.setLimit(25);
		List<QueueThresholdInfo> list = queueThresholdDao.queryQueueThreshold(bean);
		Assert.assertNotNull(list.get(0).getQmgr());
		Assert.assertEquals(2, list.size());
	}
	/**
	 * 插入测试
	 */
	@Test
	public void insertTest(){
		QueueQueryBean bean = new QueueQueryBean();
		bean.setStart(0);
		bean.setLimit(1000);
		List<QueueThresholdInfo> list = queueThresholdDao.queryQueueThreshold(bean);
		int size = list.size();
		
		QueueThresholdInfo info =new QueueThresholdInfo();
		info.setClusters(false);
		info.setQmgr("insertTest");
		info.setQueue("insertTest");
		info.setThreshold(1000);
		System.out.println(queueThresholdDao.addQueueThreshold(info));
		list = null;
		list = queueThresholdDao.queryQueueThreshold(bean);
		Assert.assertEquals(1, list.size()-size);
	}
	/**
	 * 更新测试
	 */
	@Test
	public void updateTest(){
		QueueQueryBean bean = new QueueQueryBean();
		bean.setStart(0);
		bean.setLimit(25);
		List<QueueThresholdInfo> list = queueThresholdDao.queryQueueThreshold(bean);
		Assert.assertEquals(2, list.size());
		
		QueueThresholdInfo info = list.get(0);
		info.setQueue("queue2");
		int size = queueThresholdDao.updateQueueThreshold(info);
		Assert.assertEquals(1, size);
	}
	/**
	 * 删除测试
	 */
	@Test
	public void deleteTest(){
		List<String> ids = new ArrayList<String>();
		ids.add("1");
		ids.add("2");
		int size = queueThresholdDao.deleteQueueThreshold(ids);
		Assert.assertEquals(2, size);
	}
	@Test
	public void getFullInfoTest(){
		QueueQueryBean bean = new QueueQueryBean();
		bean.setQueue("queue");
		bean.setStart(0);
		bean.setLimit(25);
		System.out.println(bean.getClusters());
		queueThresholdDao.queryQueueBean(bean);
	}
	
	@Test
	public void findQuThrsldByQmgrAndQueueAndCrntDepthTest(){
		List<QueueThresholdInfo> thresholdInfos = queueThresholdDao.findQuThrsldByQueueAndCrntDepth(
				"QU_BHO2CRM_ORDER_ADD", 1);
		Assert.assertNotNull(thresholdInfos);
//		Assert.assertTrue(thresholdInfos.isEmpty());
	}
	
}
