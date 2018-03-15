package com.deppon.esb.management.alert.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.deppon.esb.management.alert.domain.NoticeInfo;
import com.deppon.esb.management.common.test.DaoDBUnitSupportUnitTests;

//@Ignore
/**
 * @Description NoticUserDao测试类
 * @author HuangHua
 * @date 2012-4-24下午10:41:04
 * @modify 2012-04-24 22:41:24 修改为dbunit方式
 */
@ContextConfiguration(locations = { "classpath*:com/deppon/esb/management/alert/META-INF/dao/spring-test.xml" })
@TransactionConfiguration(defaultRollback = true)
public class NoticeDaoTest extends DaoDBUnitSupportUnitTests {

	@Resource
	INoticeDao noticeDao;

	@Test
	public void insertNoticesTest() {
		int f = countRowsInTable("T_ESB_NOTICE");
		logger.info(f);
		List<NoticeInfo> noticeInfos = new ArrayList<NoticeInfo>();
		NoticeInfo noticeInfo = new NoticeInfo();
		noticeInfo.setContent("content");
		noticeInfo.setCreateTime(new Date());
		noticeInfo.setSendTime(new Date());
		noticeInfo.setStatisticsTime(new Date());
		noticeInfo.setSvcCode("svcCode");
		noticeInfo.setType(1);
		noticeInfos.add(noticeInfo);
		
		for (int i = 0; i < 10; i++) {
			noticeInfos.add(noticeInfo);
		}
		
		Date start = new Date();
		Integer count = noticeDao.insertNotices(noticeInfos);
		Date end = new Date();
		logger.info(end.getTime() - start.getTime());
		logger.info(count);
		Assert.assertTrue(12 == countRowsInTable("T_ESB_NOTICE"));
	}


}
