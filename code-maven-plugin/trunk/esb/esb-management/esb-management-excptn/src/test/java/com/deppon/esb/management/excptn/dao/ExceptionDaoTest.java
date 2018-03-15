package com.deppon.esb.management.excptn.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.deppon.esb.management.common.exception.ExceptionType;
import com.deppon.esb.management.common.test.DaoDBUnitSupportUnitTests;
import com.deppon.esb.management.excptn.dao.impl.ExceptionDao;
import com.deppon.esb.management.excptn.domain.ExceptionInfo;
import com.deppon.esb.management.excptn.domain.view.ExceptionBean;
import com.deppon.esb.management.excptn.domain.view.ExceptionQueryBean;

/**
 * @Description ExceptionDao测试类
 * @author HuangHua
 * @date 2012-4-24下午10:40:23
 */
@ContextConfiguration(locations={"classpath*:com/deppon/esb/management/excptn/META-INF/dao/spring-test.xml"})
@TransactionConfiguration(defaultRollback = true)
public class ExceptionDaoTest extends DaoDBUnitSupportUnitTests {
	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionDaoTest.class);
	@Autowired
	ExceptionDao exceptionDao;
	
	@Test
	public void saveTest(){
		int count = countRowsInTable("T_ESB_EXCEPTION");
		ExceptionInfo exceptionInfo = new ExceptionInfo();
		exceptionInfo.setBiz1("biz1");
		exceptionInfo.setBiz2("biz2");
		exceptionInfo.setCreateTime(new Date());
		exceptionInfo.setDtlExcptn("dtlExcptn");
		exceptionInfo.setExceptionCode("exceptionCode");
		exceptionInfo.setMsgId("msgId");
		exceptionInfo.setOrgnMsg(new byte[]{});
		exceptionInfo.setRetryCount(5);
		exceptionInfo.setStatstcFlg(1);
		exceptionInfo.setSvcCode("svcCode");
		exceptionInfo.setExceptionType(ExceptionType.IRRECOVERABLE);
		exceptionInfo.setRequestId("requestId1");
		
		exceptionDao.storeException(exceptionInfo);
		LOGGER.info("saveException end!!!");
		
		int count1 = countRowsInTable("T_ESB_EXCEPTION");
		LOGGER.info(String.valueOf(count));
		Assert.assertEquals(1, count1-count);
		
		exceptionInfo.setRequestId("");
		exceptionDao.storeException(exceptionInfo);
		int count2 = countRowsInTable("T_ESB_EXCEPTION");
		Assert.assertEquals(1, count2-count1);
	}
	
	/**
	 * 根据查询条件查询异常信息
	 */
	@Test
	public void queryTest(){
		ExceptionQueryBean bean = new ExceptionQueryBean();
		bean.setStart(0);
		bean.setLimit(20);
		List<ExceptionBean> list = exceptionDao.queryExceptionBean(bean);
		int count = exceptionDao.queryExcpetionBeanCount(bean);
		Assert.assertTrue(list.size()==2);
		
		Assert.assertEquals(2, count);
	}
	
	public static  Date getDate(int year ,int month ,int day){
		Calendar cal = Calendar.getInstance();
		cal.set(year, month, day);
		return cal.getTime();
	}
	
	/**
	 * 查询异常堆栈信息
	 */
	@Test
	public void queryExceptionStaceTrace(){
		String str = exceptionDao.queryExceptionStrace("36985");
		Assert.assertNotNull(str);
	}
	
	
	/**
	 * 查询重发信息测试
	 */
	@Test
	public void queryRedoTest(){
		Long strTime = System.currentTimeMillis();
		List<ExceptionInfo> list = exceptionDao.queryRedo();
		Long endTime = System.currentTimeMillis();
		LOGGER.info("执行时间:" + String.valueOf(endTime-strTime));
		LOGGER.info(String.valueOf(list.size()));
		Assert.assertEquals(2, list.size());
	}
	/**
	 * 更新状态测试
	 */
    @Test
    public void updateStatusTest(){
    	List<String> ids =  new ArrayList<String>();
    	ids.add("36985");
    	ids.add("36986");
    	Integer count = exceptionDao.updateRedoSuccess(ids);
    	Assert.assertEquals(2, count.intValue());
    }
    /**
     * 更新重发次数测试
     */
    @Test
    public void updateRedoCountTest(){
    	List<String> ids =  new ArrayList<String>();
    	ids.add("36985");
    	ids.add("36986");
    	Integer count = exceptionDao.updateRedoCount(ids);
    	Assert.assertEquals(2, count.intValue());
    	
    }
    
    /**
     * 保存exceptionInfo测试
     */
    @Test
    public void test(){
    	List<ExceptionInfo> list = new ArrayList<ExceptionInfo>();
    	ExceptionInfo info= createExcepionInfo();
    	list.add(info);

    	int count1 = countRowsInTable("T_ESB_EXCEPTION");
    	LOGGER.info(String.valueOf(count1));
    	exceptionDao.storeExceptionList(list);
    	int count2 = countRowsInTable("T_ESB_EXCEPTION");
    	LOGGER.info(String.valueOf(count2));
    	Assert.assertTrue(count1==(count2-1));
    }
    
    public static ExceptionInfo createExcepionInfo(){
    	ExceptionInfo info= new ExceptionInfo ();
    	info.setEsbHostName("KF150");
    	info.setEsbIp("192.168.11.28");
    	info.setFromEndpointURI("http://localhost:8080/webservice/recgoodbill");
    	info.setMsgId("ID-KF150-1641-1339060045859-4-9");
    	info.setSvcCode("ESB2ERP_ADDRECGOODSBILL");
    	info.setCreateTime(new Date());
    	info.setExceptionCode("com.deppon.esb.common.exception.ESBServerSystemExeption");
    	info.setMethod("process");
    	info.setRedoType("webservice");
    	info.setDtlExcptn("dtlExcptn");
    	info.setOrgnMsg(new byte[]{123});
    	return info;
    }
}
