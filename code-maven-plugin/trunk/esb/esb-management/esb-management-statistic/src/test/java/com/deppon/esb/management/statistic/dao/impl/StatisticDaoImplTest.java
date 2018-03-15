package com.deppon.esb.management.statistic.dao.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.deppon.esb.management.common.test.DaoDBUnitSupportUnitTests;
import com.deppon.esb.management.statistic.dao.IStatisticDao;
import com.deppon.esb.management.statistic.view.StatisticQueryBean;
import com.deppon.esb.management.statistic.view.StatisticView;
import com.sun.istack.logging.Logger;

@ContextConfiguration(locations = { "classpath*:com/deppon/esb/management/statistic/META-INF/dao/spring-test.xml" })
@TransactionConfiguration(defaultRollback = true)
public class StatisticDaoImplTest extends DaoDBUnitSupportUnitTests {
	@Resource
	private IStatisticDao statisiticDao;

	@Test
	public void queryStatisticViewTest() throws Exception {
		// System.out.println(countRowsInTable("T_ESB2_INTERFACESTATISTICS"));
		StatisticQueryBean bean = new StatisticQueryBean();
		bean.setStart(0);
		bean.setLimit(10);
		//
		Calendar cal = Calendar.getInstance();
		cal.set(2013, 00, 01, 01, 1, 1);
		Date start = cal.getTime();
		cal.set(2013, 04, 01, 01, 1, 1);
		Date end = new Date();
		bean.setStartDate(start);
		bean.setEndDate(end);
		bean.setEsbSvcCode("FIN_SELF_ESB2FIN_SELF_RECEIVE_PROVINCECITY");
		bean.setType("JMS");
		List<StatisticView> list = statisiticDao.queryStatisticView(bean);
		StatisticView p=list.get(0);
		logger.info("测试数据："+p.getName()+"\t"+p.getBackSvcCode()+"\t"+p.getEsbSvcCode()+"\t"+p.getTotalCount());
		Assert.assertNotNull(list);
		Assert.assertTrue(list.size() > 0);
	}

	@Test
	public void queryStatisticViewCountTest() throws Exception {
		StatisticQueryBean bean = new StatisticQueryBean();
		Calendar cal = Calendar.getInstance();
		cal.set(2013, 00, 01, 01, 1, 1);
		Date start = cal.getTime();
		cal.set(2013, 04, 01, 01, 1, 1);
		Date end = new Date();
		bean.setStartDate(start);
		bean.setEndDate(end);
		bean.setEsbSvcCode("FIN_SELF_ESB2FIN_SELF_RECEIVE_PROVINCECITY");
		bean.setType("JMS");
		int count = statisiticDao.queryStatisticViewCount(bean);
		Assert.assertEquals(2, count);
	}
}
