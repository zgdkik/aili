package com.deppon.esb.management.svccfg.dao.extend;

import java.util.List;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.deppon.esb.management.common.test.DaoDBUnitSupportUnitTests;
import com.deppon.esb.management.svccfg.domain.extend.ESBServiceConfiguration;
import com.deppon.esb.management.svccfg.domain.extend.ESBSystemConfiguration;

/**
 * The Class ExtendConfigurationDaoTest.
 */
@ContextConfiguration(locations = { "classpath*:com/deppon/esb/management/svccfg/META-INF/dao/spring-test.xml" })
@TransactionConfiguration(defaultRollback = true)
public class ExtendConfigurationDaoTest extends DaoDBUnitSupportUnitTests {

	/** The configuration dao. */
	@Resource
	IExtendConfigurationDao configurationDao;

	/**
	 * Load all system channel test.
	 */
	@Test
	public void loadAllSystemChannelTest() {
		ESBSystemConfiguration configuration = configurationDao.loadAllSystemChannel("FOSS");
		Assert.assertNotNull(configuration);
		Assert.assertEquals("FOSS", configuration.getSystemCode());
	}

	/**
	 * Load service configuration.
	 */
	@Test
	public void loadServiceConfiguration() {
		List<ESBServiceConfiguration> configurations = configurationDao.loadServiceConfiguration("FOSS");
		Assert.assertNotNull(configurations);
		Assert.assertTrue(configurations.size() == 1);
		Assert.assertEquals("XML", configurations.get(0).getMessageFormat());
		Assert.assertEquals("C", configurations.get(0).getClientOrServer());
	}
}
