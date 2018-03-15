package com.deppon.esb.management.mq.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.deppon.esb.management.common.test.DaoDBUnitSupportUnitTests;
import com.deppon.esb.management.mq.domain.QueueManagerInfo;

@ContextConfiguration(locations = { "classpath*:com/deppon/esb/management/mq/META-INF/dao/spring-test.xml"})
@TransactionConfiguration(defaultRollback = true)
public class QueueManagerDaoTest extends DaoDBUnitSupportUnitTests{
	
	@Resource
	private IMqManagerDao managerDao;
	
	@Test
	public void findAlert(){
		List<QueueManagerInfo> infos = managerDao.findAlert();
		Assert.assertNotNull(infos);
	}
	
	@Test
    public void queryManagerAdder(){
		QueueManagerInfo managerInfo = new QueueManagerInfo();
		List<QueueManagerInfo> infos = managerDao.queryManagerAdder(managerInfo);
		Assert.assertNotNull(infos);
		managerInfo.setPort(3438);
		infos = managerDao.queryManagerAdder(managerInfo);
		Assert.assertNotNull(infos);
	}

	@Test
    public void addManagerAdder(){
		QueueManagerInfo managerInfo = new QueueManagerInfo();
		managerInfo.setIp("192.168.17.141");
		managerInfo.setPort(3439);
		managerInfo.setQmgr("QM_ESBT");
		managerInfo.setQueueNameReg("QU*");
		managerInfo.setChannel("ADMIN.CHANNEL");
		int count = managerDao.addManagerAdder(managerInfo);
		Assert.assertEquals(1, count);
	}

	@Test
    public void updateManagerAdder(){
		QueueManagerInfo managerInfo = new QueueManagerInfo();
		managerInfo.setId("111");
		managerInfo.setChannel("ADMIN.CHANNEL");
		int count = managerDao.updateManagerAdder(managerInfo);
		Assert.assertEquals(1, count);
	}

	@Test
    public void deleteManagerAdder(){
		List<String> list = new ArrayList<String>();
		list.add("111");
		int count = managerDao.deleteManagerAdder(list);
		Assert.assertEquals(1, count);
	}
}
