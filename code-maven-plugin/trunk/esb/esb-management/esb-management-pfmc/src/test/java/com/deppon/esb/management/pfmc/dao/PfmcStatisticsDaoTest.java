package com.deppon.esb.management.pfmc.dao;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.deppon.esb.management.common.test.DaoDBUnitSupportUnitTests;
import com.deppon.esb.management.pfmc.domain.PfmcStatisticsInfo;

/**
 * @Description PfmcStatisticsDao测试类
 * @author HuangHua
 * @date 2012-4-24下午10:41:36
 * @modify 2012-04-24 22:41:51 修改为dbunit方式
 */
@ContextConfiguration(locations = { "classpath*:com/deppon/esb/management/pfmc/META-INF/dao/spring-test.xml" })
@TransactionConfiguration(defaultRollback = true)
public class PfmcStatisticsDaoTest extends DaoDBUnitSupportUnitTests {

	@Autowired
	IPfmcStatisticsDao pfmcStatisticsDao;

	@Test
	public void testQueryPfmcStatistics2Notice() {
		List<PfmcStatisticsInfo> result = pfmcStatisticsDao.queryPfmcStatistics2Notice();
		Assert.assertEquals(1, result.size());
		Assert.assertEquals(new Long(1), result.get(0).getAvgRspTime());
	}

}
