package com.deppon.esb.management.svccfg.dao;

import java.util.List;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.deppon.esb.management.common.test.DaoDBUnitSupportUnitTests;
import com.deppon.esb.management.svccfg.domain.ESBRuntimeConfiguration;
import com.deppon.esb.management.svccfg.domain.view.EsbRuntimeConfigAddrBean;

/**
 * The Class EsbConfigurationDaoTest.
 */
@ContextConfiguration(locations = { "classpath*:com/deppon/esb/management/svccfg/META-INF/dao/spring-test.xml" })
@TransactionConfiguration(defaultRollback = true)
public class EsbConfigurationDaoTest extends DaoDBUnitSupportUnitTests {

	/** The configuration dao. */
	@Resource
	IEsbConfigurationDao configurationDao;

	/**
	 * Load esb runtime configuration.
	 */
	@Test
	public void loadESBRuntimeConfiguration() {
		List<ESBRuntimeConfiguration> configurations = configurationDao
				.loadESBRuntimeConfiguration();
		Assert.assertNotNull(configurations);
		Assert.assertTrue(configurations.size() == 1);
	}
	
	@Test
	public void selectBackendWsAddr() {
		List<EsbRuntimeConfigAddrBean> configurations = configurationDao
				.selectBackendWsAddr();
		Assert.assertNotNull(configurations);
		Assert.assertTrue(configurations.size() == 1);
	}
}
