package com.deppon.esb.management.svccfg.dao;

import java.util.ArrayList;
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
import com.deppon.esb.management.svccfg.domain.SvcPoint2Info;
import com.deppon.esb.management.svccfg.domain.view.SvcPoint2QueryBean;

/**
 * @Description SvcPointDao测试类
 * @author HuangHua
 * @date 2012-4-24下午10:42:08
 * @modify 2012-04-24 22:42:19 修改为dbunit方式
 */
@ContextConfiguration(locations = { "classpath*:com/deppon/esb/management/svccfg/META-INF/dao/spring-test.xml" })
@TransactionConfiguration(defaultRollback = true)
public class SvcPoint2DaoTest extends DaoDBUnitSupportUnitTests {
	public static final String SVCCODE = "ERP2ESB_updateOrderState";
	@Autowired
	ISvcPoint2Dao svcPoint2Dao;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	protected Map getDatasetReplacements() {
		Map replacements = new HashMap();
		replacements.put("[SVCCODE]", SVCCODE);
		return replacements;
	}

	@Test
	public void insertTest() {
		int i = svcPoint2Dao.addSvcpoint2(createSvcPoint2Info());
		Assert.assertEquals(1, i);
	}
	@Test
	public void queryTest(){
		svcPoint2Dao.addSvcpoint2(createSvcPoint2Info());
		SvcPoint2QueryBean bean = new SvcPoint2QueryBean();
		bean.setStart(0);
		bean.setLimit(10);
		bean.setName("测试");
		bean.setCode("");
		//bean.
		int count = svcPoint2Dao.queryCount(bean);
		List<SvcPoint2Info> list = svcPoint2Dao.querySvcpoint2(bean);
		Assert.assertTrue(count>0);
		Assert.assertTrue(list.size()>0);
		
	}
	
	@Test
	public void updateTest(){
		SvcPoint2Info info = null;
		SvcPoint2QueryBean bean = new SvcPoint2QueryBean();
		bean.setStart(0);
		bean.setLimit(10);
		bean.setName("测试");
		List<SvcPoint2Info> list = svcPoint2Dao.querySvcpoint2(bean);
		info = list.get(0);
		info.setName("updateName");
		info.setAgrmt("JMS");
		info.setCreateTime(new Date());
		info.setFrontOrBack("F");
		info.setMessageFormat("XML");
		info.setExpattern(3);
		info.setReqConvertorClass("requestConvertor");
		info.setResConvertorClass("resConvertor");
		int i = svcPoint2Dao.updateSvcpoint2(info);
		Assert.assertEquals(1, i);
	}
	
	@Test
	public void deleteTest(){
		String code = "TEST_CODE123";
		List<String> list = new  ArrayList<String>();
		list.add(code);
		int i =svcPoint2Dao.deleteSvcpoint2(list);
		Assert.assertEquals(1, i);
	}
	
	@Test
	public void existNameTest(){
		boolean b =svcPoint2Dao.existName("abc");
		Assert.assertFalse(b);
	}
	
	@Test
	public void existCodeTest(){
		boolean b =svcPoint2Dao.existCode("abc");
		Assert.assertFalse(b);
	}
	
	public SvcPoint2Info createSvcPoint2Info(){
		SvcPoint2Info info = new SvcPoint2Info();
		info.setAgrmt("JMS");
		info.setAppRequestAddr("appRequestAddr");
		info.setAppResponseAddr("appresponseAddr");
		info.setCode("testCode");
		info.setCreateTime(new Date());
		info.setEsbRequestAddr("esbRequestAddr");
		info.setEsbResponseAddr("esbResponseAddr");
		info.setExpattern(1);
		info.setFrontOrBack("F");
		info.setName("测试");
		info.setReqConvertorClass("a.b.c");
		info.setResConvertorClass("c.d.e");
		info.setSysid("FOSS");
		info.setTimeout(1000);
		info.setMessageFormat("SOAP");
		return info;
	}
	
	
}
