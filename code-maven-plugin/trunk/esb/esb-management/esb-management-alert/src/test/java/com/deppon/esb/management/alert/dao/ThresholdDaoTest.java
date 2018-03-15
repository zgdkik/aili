package com.deppon.esb.management.alert.dao;

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

import com.deppon.esb.management.alert.domain.InterfaceThresholdInfo;
import com.deppon.esb.management.alert.domain.view.InterfaceThresholdBean;
import com.deppon.esb.management.alert.domain.view.InterfaceThresholdQueryBean;
import com.deppon.esb.management.common.test.DaoDBUnitSupportUnitTests;


@ContextConfiguration(locations = { "classpath*:com/deppon/esb/management/alert/META-INF/dao/spring-test.xml" })
@TransactionConfiguration(defaultRollback =true)
public class ThresholdDaoTest extends DaoDBUnitSupportUnitTests {
	public static final String SVCCODE = "ERP2ESB_updateOrderState";
	// ApplicationContext context;
	@Autowired
	IinterfaceThresholdDao interfaceThresholdDao;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	protected Map getDatasetReplacements() {
		Map replacements = new HashMap();
		replacements.put("[SVCCODE]", SVCCODE);
		return replacements;
	}
	//插入、查找测试
	@Test
	public void insertTest(){
		int size =0;
		List<InterfaceThresholdInfo> list = null;
		list =  interfaceThresholdDao.getInterfaceThreshold(new InterfaceThresholdQueryBean());
		size = list.size();
		InterfaceThresholdInfo thresholdInfo = new InterfaceThresholdInfo();
		thresholdInfo.setChannelId("fetion");
		thresholdInfo.setSvcCode("OA2CRM_CLAIMRESULT");
		thresholdInfo.setThreshold(2);
		thresholdInfo.setPersonId("1,2,3,4");
		interfaceThresholdDao.addInterfacethreshold(thresholdInfo);
		Assert.assertNotNull(thresholdInfo.getId());
		list =  null;
		list = interfaceThresholdDao.getInterfaceThreshold(new InterfaceThresholdQueryBean());
		Assert.assertEquals(1, list.size()-size);
	}
	//更新测试
	@Test
	public void updateTest(){
		InterfaceThresholdInfo thresholdInfo = new InterfaceThresholdInfo();
		thresholdInfo.setChannelId("updateTest");
		thresholdInfo.setSvcCode("updateTest");
		thresholdInfo.setThreshold(2);
		interfaceThresholdDao.addInterfacethreshold(thresholdInfo);
		thresholdInfo.setThreshold(1000);
		interfaceThresholdDao.updateInterfaceThreshold(thresholdInfo);
		InterfaceThresholdQueryBean thresholdQueryBean = new InterfaceThresholdQueryBean();
		thresholdQueryBean.setSvcCode("updateTest");
		thresholdQueryBean.setThreshold(1000);
		List<InterfaceThresholdInfo> list = interfaceThresholdDao.getInterfaceThreshold(thresholdQueryBean);
		Assert.assertEquals(1, list.size());
	}

	@Test
	public void getFullThresholdTest(){
		InterfaceThresholdInfo thresholdInfo = new InterfaceThresholdInfo();
		thresholdInfo.setSvcCode("getFullThresholdTest");
		thresholdInfo.setThreshold(1000);
		thresholdInfo.setChannelId("fetion");
		interfaceThresholdDao.addInterfacethreshold(thresholdInfo);
		InterfaceThresholdQueryBean thresholdQueryBean =new InterfaceThresholdQueryBean();
		thresholdQueryBean.setStart(0);
		thresholdQueryBean.setLimit(100);
		List<InterfaceThresholdBean> list = interfaceThresholdDao.getThresholdResultBean(thresholdQueryBean);
		System.out.println(list.size());
		Assert.assertTrue(list.size()>0);
	}
	@Test
	public void deleteTest(){
		List<InterfaceThresholdInfo> list = null;
		List<String> idList = new ArrayList<String>();
		list =  interfaceThresholdDao.getInterfaceThreshold(new InterfaceThresholdQueryBean());
		int size = list.size();
		InterfaceThresholdInfo thresholdInfo = new InterfaceThresholdInfo();
		thresholdInfo.setChannelId("delete1");
		thresholdInfo.setSvcCode("deleteTest1");
		thresholdInfo.setThreshold(2);
		interfaceThresholdDao.addInterfacethreshold(thresholdInfo);
		idList.add(thresholdInfo.getId());
		thresholdInfo = new InterfaceThresholdInfo();
		thresholdInfo.setChannelId("delete2");
		thresholdInfo.setSvcCode("deleteTest3");
		thresholdInfo.setThreshold(2);
		interfaceThresholdDao.addInterfacethreshold(thresholdInfo);
		idList.add(thresholdInfo.getId());
		interfaceThresholdDao.deleteInterfaceThresholdById(idList);
		list = null;
		list =  interfaceThresholdDao.getInterfaceThreshold(new InterfaceThresholdQueryBean());
		Assert.assertEquals(size, list.size());
	}
	
	
	/**
	 * 查询异常监控设置记录测试
	 */
	@Test
	public void getExceptionSetTest(){
		InterfaceThresholdQueryBean bean = new InterfaceThresholdQueryBean();
		bean.setSvcProvdId("OA");
		bean.setFrntorbck("F");
		bean.setCreateTime(new Date());
		bean.setStart(0);
		bean.setLimit(20);
		bean.setSvcName("订单新增");
		bean.setSvcCode("EBM2CRm_ADDORDER");
		List<InterfaceThresholdBean> list = interfaceThresholdDao.getExceptionSet(bean);
		Assert.assertTrue(0==list.size());
		
	}
	@Test
	/**
	 * 查询异常监控设置记录总数测试
	 */
	public void getExceptionSetCountTest(){
		InterfaceThresholdQueryBean bean = new InterfaceThresholdQueryBean();
		bean.setSvcProvdId("OA");
		bean.setFrntorbck("F");
		bean.setCreateTime(new Date());
		bean.setStart(0);
		bean.setLimit(20);
		bean.setSvcName("订单新增");
		bean.setSvcCode("EBM2CRm_ADDORDER");
		Integer count = interfaceThresholdDao.getExceptionSetCount(bean);
		Assert.assertTrue(0==count);
	}
}
