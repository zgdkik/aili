package com.deppon.esb.management.pfmc.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.deppon.esb.management.pfmc.dao.impl.PerformanceDao;
import com.deppon.esb.management.pfmc.domain.PerformanceInfo;

/**
 * PerformanceDao测试类
 * @author HuangHua
 * @date 2012-12-29 下午3:26:15
 */
@ContextConfiguration(locations = { "classpath*:com/deppon/esb/management/pfmc/META-INF/dao/spring-test.xml" })
@TransactionConfiguration(defaultRollback = true)
public class PerformanceDaoTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	PerformanceDao performanceDao;

	@Test
	public void testSave() {
		int count = countRowsInTable("T_ESB_PERFORMANCE");

		PerformanceInfo performanceInfo = new PerformanceInfo();
		performanceInfo.setCamelContextId("camelContextId");
		performanceInfo.setCreateTime(new Date());
		performanceInfo.setFromEndPoint("fromEndPoint");
		performanceInfo.setReqMsg(new byte[] {});
		performanceInfo.setRouteId("routeid");
		performanceInfo.setRspMsg(new byte[] {});
		performanceInfo.setStatisticsFlg(1);
		performanceInfo.setSvcCode("svcCode");
		performanceInfo.setExchangeId("exchangeId");
		performanceInfo.setTimecosts(10l);
		performanceInfo.setRequestTime(new Date());
		performanceInfo.setResponseTime(new Date());
		performanceInfo.setToEndPoint("toEndPoint");

		performanceDao.savePerformance(performanceInfo);
		logger.info("savePerformance end!!!");

		int count1 = countRowsInTable("T_ESB_PERFORMANCE");

		Assert.assertEquals(1, count1 - count);
	}

	@Test
	public void storeList() {
		int count = countRowsInTable("T_ESB_PERFORMANCE");

		List<PerformanceInfo> list = new ArrayList<PerformanceInfo>();
		PerformanceInfo performanceInfo = new PerformanceInfo();
		performanceInfo.setCamelContextId("camelContextId");
		performanceInfo.setCreateTime(new Date());
		performanceInfo.setFromEndPoint("fromEndPoint");
		performanceInfo.setReqMsg(new byte[] {});
		performanceInfo.setRouteId("routeid");
		performanceInfo.setRspMsg(new byte[] {});
		performanceInfo.setStatisticsFlg(1);
		performanceInfo.setSvcCode("svcCode");
		performanceInfo.setTimecosts(10l);
		performanceInfo.setToEndPoint("toEndPoint");
		list.add(performanceInfo);
		list.add(performanceInfo);
		performanceDao.storePerformanceInfoList(list);
		logger.info("storeList end!!!");
		int count1 = countRowsInTable("T_ESB_PERFORMANCE");

		Assert.assertEquals(2, count1 - count);
	}

	@Test
	public void testQuery() {
		simpleJdbcTemplate.update("insert into t_esb_performance (FID, CAMELCONTEXTID, ROUTEID, SVCCODE, FROMENDPOINT, TIMECOSTS, TOENDPOINT) values ('1', '1', '1', '1', '1', 1, '1')");
		simpleJdbcTemplate.update("insert into t_esb_svcpoint (FID, SVCNAME, SVCCODE, SVCPROVDID, SVCAGRMT, ISAUTORESEND, ISRCDORGBODY, ISRCDPFMCLOG, ISRCDERRLOG) values ('36985', '1', '1', '1', '1', '0', '0', '1', '1')" );
		List<PerformanceInfo> list = performanceDao.queryPerformanceList("1", "1");
		for (Iterator<PerformanceInfo> iterator = list.iterator(); iterator.hasNext();) {
			PerformanceInfo performanceInfo = (PerformanceInfo) iterator.next();
			logger.info(performanceInfo);
			Assert.assertEquals("1", performanceInfo.getFromEndPoint());
		}
		logger.info("queryTest ended!!!");
	}

}
