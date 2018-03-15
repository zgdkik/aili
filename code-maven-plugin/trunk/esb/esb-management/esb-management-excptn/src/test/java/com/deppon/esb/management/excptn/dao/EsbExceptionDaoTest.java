package com.deppon.esb.management.excptn.dao;

import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.deppon.esb.management.common.entity.jms.header.AuthInfo;
import com.deppon.esb.management.common.entity.jms.header.EsbHeader;
import com.deppon.esb.management.common.test.DaoDBUnitSupportUnitTests;
import com.deppon.esb.management.excptn.domain.EsbExceptionInfo;
import com.deppon.esb.management.exptn.generate.CommonExceptionInfo;

/**
 * @Description ExceptionDao测试类
 * @author HuangHua
 * @date 2012-4-24下午10:40:23
 */
@ContextConfiguration(locations={"classpath*:com/deppon/esb/management/excptn/META-INF/dao/spring-test.xml"})
@TransactionConfiguration(defaultRollback = true)
public class EsbExceptionDaoTest extends DaoDBUnitSupportUnitTests {
	private static final Logger LOGGER = LoggerFactory.getLogger(EsbExceptionDaoTest.class);
	@Autowired
	IEsbExceptionDao esbExceptionDao;
	
	@Test
	public void saveTest(){
		int count = countRowsInTable("T_ESB2_EXCEPTION");
		EsbExceptionInfo exceptionInfo = new EsbExceptionInfo();
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
		esbHeader.setExchangePattern(2);
		esbHeader.setMessageFormat("value");
		esbHeader.setRequestId("value");
		esbHeader.setResponseId("value");
		esbHeader.setResultCode(1);
		esbHeader.setSentSequence(0);
		esbHeader.setSourceSystem("value");
		esbHeader.setTargetSystem("value");
		esbHeader.setVersion("value");
		exceptionInfo.setEsbHeader(esbHeader);
		exceptionInfo.setHostIp("192.168.13.247");
		exceptionInfo.setCreateTime(new Date());
		CommonExceptionInfo commonExceptionInfo = new CommonExceptionInfo();
		commonExceptionInfo.setCreatedTime(new Date());
		commonExceptionInfo.setDetailedInfo("ddddddddddddd");
		commonExceptionInfo.setExceptiontype("ddddddddddddd");
		exceptionInfo.setCommonExceptionInfo(commonExceptionInfo);
		esbExceptionDao.saveEsbException(exceptionInfo);
		int count1 = countRowsInTable("T_ESB2_EXCEPTION");
		LOGGER.info(String.valueOf(count));
		Assert.assertEquals(1, count1-count);
		commonExceptionInfo.setExceptioncode("ddddddddddddd");
		esbExceptionDao.saveEsbException(exceptionInfo);
		int count2 = countRowsInTable("T_ESB2_EXCEPTION");
		Assert.assertEquals(2, count2-count);
	}
	
	@Test
	public void testQuerySysExcption(){
		List<EsbExceptionInfo> esbExceptionInfos  = esbExceptionDao.querySysExcption();
		Assert.assertEquals(2, esbExceptionInfos.size());
	}
}
