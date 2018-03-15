package com.deppon.esb.management.svccfg.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.deppon.esb.management.common.test.DaoDBUnitSupportUnitTests;
import com.deppon.esb.management.svccfg.domain.SvcPointInfo;
import com.deppon.esb.management.svccfg.domain.SvcPointRelationInfo;
import com.deppon.esb.management.svccfg.domain.view.SvcpointBean;
import com.deppon.esb.management.svccfg.domain.view.SvcpointQueryBean;

/**
 * @Description SvcPointDao测试类
 * @author HuangHua
 * @date 2012-4-24下午10:42:08
 * @modify 2012-04-24 22:42:19 修改为dbunit方式
 */
@ContextConfiguration(locations = { "classpath*:com/deppon/esb/management/svccfg/META-INF/dao/spring-test.xml" })
@TransactionConfiguration(defaultRollback = true)
public class SvcPointDaoTest extends DaoDBUnitSupportUnitTests {
	public static final String SVCCODE = "ERP2ESB_updateOrderState";
	@Autowired
	ISvcPointDao svcPointDao;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	protected Map getDatasetReplacements() {
		Map replacements = new HashMap();
		replacements.put("[SVCCODE]", SVCCODE);
		return replacements;
	}

	@Test
	public void insertTest() {
		SvcPointInfo svcPointInfo = new SvcPointInfo();
		svcPointInfo.setFrntOrBck("B");
		svcPointInfo
				.setSvcAddr("http://192.168.17.183:8080/webservice/hello.wsdl");
		svcPointInfo.setSvcCode("insertTest");
		svcPointInfo.setSvcName("insertTest");
		svcPointInfo.setSvcProvdId("CRM");
		svcPointInfo.setCreateTime(new Date());
		svcPointInfo.setSvcAgrmt("MQ");
		svcPointInfo.setResponseType("insertTest");
		svcPointDao.addSvcpointInfo(svcPointInfo);
		SvcPointInfo info = null;
		info = svcPointDao.loadConfigBySvcCode("insertTest");
		Assert.assertEquals(svcPointInfo.getFrntOrBck(), info.getFrntOrBck());
		Assert.assertEquals(svcPointInfo.getResponseType(),
				info.getResponseType());
		Assert.assertEquals(svcPointInfo.getSvcAddr(), info.getSvcAddr());
		Assert.assertEquals(svcPointInfo.getSvcAgrmt(), info.getSvcAgrmt());
		Assert.assertEquals(svcPointInfo.getSvcName(), info.getSvcName());
		Assert.assertEquals(svcPointInfo.getSvcProvdId(), info.getSvcProvdId());
	}

	@Test
	public void loadConfig() {
		SvcPointInfo svcPointInfo = svcPointDao
				.loadConfigBySvcCode("ERP2ESB_updateOrderState");
		Assert.assertTrue(svcPointInfo != null);
		Assert.assertEquals(SVCCODE, svcPointInfo.getSvcCode());
		Assert.assertTrue(svcPointInfo.getIsRcdErrLog());
		Assert.assertTrue(svcPointInfo.getIsRcdOrgBody());
		Assert.assertFalse(svcPointInfo.getIsRcdPfmcLog());
		Assert.assertNotNull(svcPointInfo.getResponseType());
	}

	@Test
	public void testUpdateSvcpoint() {
		SvcPointInfo svcPointInfo = new SvcPointInfo();
		svcPointInfo.setFrntOrBck("B");
		svcPointInfo
				.setSvcAddr("http://192.168.17.183:8080/webservice/hello.wsdl");
		svcPointInfo.setSvcCode("testUpdateSvcpoint");
		svcPointInfo.setSvcName("testUpdateSvcpoint");
		svcPointInfo.setSvcProvdId("CRM");
		svcPointInfo.setIsRcdErrLog(true);
		svcPointInfo.setIsRcdOrgBody(true);
		svcPointInfo.setIsRcdPfmcLog(true);
		svcPointInfo.setSvcAgrmt("MQ");
		svcPointInfo.setIsAutoResend(true);
		svcPointDao.addSvcpointInfo(svcPointInfo);
		svcPointInfo.setFrntOrBck("F");
		svcPointDao.updateSvcPointInfo(svcPointInfo);
		svcPointInfo = null;
		svcPointInfo = svcPointDao.loadConfigBySvcCode("testUpdateSvcpoint");
		Assert.assertEquals("F", svcPointInfo.getFrntOrBck());
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void getSvcpointByCondidtionTest() {
		SvcPointInfo info = new SvcPointInfo();
		info.setSvcCode("getSvcpointByCondidtionTest1");
		info.setSvcName("getSvcpointByCondidtionTest1");
		info.setFrntOrBck("F");
		svcPointDao.addSvcpointInfo(info);
		info.setSvcCode("getSvcpointByCondidtionTest2");
		info.setSvcName("getSvcpointByCondidtionTest");
		info.setFrntOrBck("F");
		svcPointDao.addSvcpointInfo(info);
		info.setSvcCode("getSvcpointByCondidtionTest3");
		info.setSvcName("getSvcpointByCondidtionTest");
		info.setFrntOrBck("B");
		svcPointDao.addSvcpointInfo(info);
		Map map = new HashMap();
		map.put("start", 0);
		map.put("limit", 10);
		List<SvcPointInfo> list = null;
		map = new HashMap();
		map.put("frntOrBck", "F");
		map.put("start", 0);
		map.put("limit", 10);
		list = svcPointDao.loadConfigByConditions(map);
		Assert.assertEquals(3, list.size());
	}

	/*
	 * =====================svcpointRelation测试
	 */
	// @Test
	public void saveSvcpointRelationTest() {
		SvcPointRelationInfo svcPointRelationInfo = new SvcPointRelationInfo();
		svcPointRelationInfo.setFrontSvcCode("CRM_Order");
		svcPointRelationInfo.setBackSvcCode("ERP_Order");
	}

	@Test
	public void querySvcpointBeanTest() {
		SvcpointQueryBean bean = new SvcpointQueryBean();
		bean.setBackSvcName("OA");
		bean.setClientSystem("CRM");
		bean.setSvcAgrmt("WS");
		bean.setFrontSvcName("CRM2OA_ADDWORKFLOW");
		bean.setSvcAddr("http://...");
		bean.setStart(new Integer(0));
		bean.setLimit(new Integer(20));
		List<SvcpointBean> list = svcPointDao.getSvcpointBean(bean);
		Assert.assertEquals(0, list.size());
	}
}
