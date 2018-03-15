/*
 * PROJECT NAME: esb-management-svccfg
 * PACKAGE NAME: com.deppon.esb.management.svccfg.service
 * FILE    NAME: ConfigurationServiceTest.java
 * COPYRIGHT: Copyright(c) 2012 Deppon All Rights Reserved.
 */
package com.deppon.esb.management.svccfg.service;

import java.util.List;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.deppon.esb.management.common.test.DaoDBUnitSupportUnitTests;
import com.deppon.esb.management.svccfg.domain.ESBRuntimeConfiguration;
import com.deppon.esb.management.svccfg.domain.extend.ESBServiceConfiguration;
import com.deppon.esb.management.svccfg.domain.extend.ESBSystemConfiguration;
import com.deppon.esb.management.svccfg.domain.view.EsbRuntimeConfigAddrBean;
import com.deppon.esb.security.domain.InterfaceCount;
import com.deppon.esb.security.domain.UserInfo;
import com.deppon.esb.security.domain.UserInterfaceCount;
import com.deppon.esb.security.domain.UserInterfaceRelation;

/**
 * The Class ConfigurationServiceTest.
 * 
 * @author HuangHua
 * @date 2012-12-29 下午2:51:54
 */
@ContextConfiguration(locations = {
		"classpath*:com/deppon/esb/management/svccfg/META-INF/dao/spring-test.xml",
		"classpath*:com/deppon/esb/management/svccfg/META-INF/configuration-spring.xml" })
@TransactionConfiguration(defaultRollback = true)
public class ConfigurationServiceTest extends DaoDBUnitSupportUnitTests {

	@Resource
	IConfigurationService configurationService;

	/**
	 * Test method for
	 * {@link com.deppon.esb.management.svccfg.service.impl.ConfigurationService#loadAllSystemChannel(java.lang.String)}
	 * .
	 */
	@Test
	public void testLoadAllSystemChannel() {
		ESBSystemConfiguration configuration = configurationService.loadAllSystemChannel("FOSS");
		Assert.assertNotNull(configuration);
		Assert.assertEquals("FOSS", configuration.getSystemCode());
	}

	/**
	 * Test method for
	 * {@link com.deppon.esb.management.svccfg.service.impl.ConfigurationService#loadServiceConfiguration(java.lang.String)}
	 * .
	 */
	/*@Test
	public void testLoadServiceConfiguration() {
		List<ESBServiceConfiguration> configurations = configurationService.loadServiceConfiguration("FOSS");
		Assert.assertNotNull(configurations);
		Assert.assertTrue(configurations.size() == 1);
		Assert.assertEquals("XML", configurations.get(0).getMessageFormat());
		Assert.assertEquals("C", configurations.get(0).getClientOrServer());
	}*/

	/**
	 * Test method for
	 * {@link com.deppon.esb.management.svccfg.service.impl.ConfigurationService#loadAllSystemConfiguration(java.lang.String)}
	 * .
	 */
	@Test
	public void testLoadAllSystemConfiguration() {
		String configuration = configurationService.loadAllSystemConfiguration("FOSS");
		Assert.assertNotNull(configuration);
	}

	/**
	 * Test method for
	 * {@link com.deppon.esb.management.svccfg.service.impl.ConfigurationService#loadESBRuntimeConfiguration()}
	 * .
	 */
	@Test
	public void testLoadESBRuntimeConfiguration() {
		ESBRuntimeConfiguration[] configurations = configurationService.loadESBRuntimeConfiguration();
		Assert.assertNotNull(configurations);
		Assert.assertTrue(configurations.length == 1);
	}
	
	@Test
	public void testselectBackendWsAddr() {
		List<EsbRuntimeConfigAddrBean> configurations = configurationService.selectBackendWsAddr();
		Assert.assertNotNull(configurations);
		Assert.assertTrue(configurations.size() == 1);
	}
	
	
	@Test
	public void testloadUserInterfaceRelation(){
		UserInterfaceRelation[] configurations = configurationService.loadUserInterfaceRelation();
		Assert.assertNotNull(configurations);
		//Assert.assertTrue(configurations.length == 1);
	}
	
	@Test
	public void testloadUserInterfaceCount(){
		UserInterfaceCount[] configurations = configurationService.loadUserInterfaceCount();
		Assert.assertNotNull(configurations);
		//Assert.assertTrue(configurations.length == 1);
	}
	
	@Test
	public void testloadInterfaceCount(){
		InterfaceCount[] configurations = configurationService.loadInterfaceCount();
		Assert.assertNotNull(configurations);
		//Assert.assertTrue(configurations.length == 1);
	}
	
	@Test
	public void testloadAgentUserConfiguration(){
		UserInfo[] configurations = configurationService.loadAgentUserConfiguration();
		System.out.println(configurations[0].getUser());
		Assert.assertNotNull(configurations);
		//Assert.assertTrue(configurations.length == 1);
	}
	
}
