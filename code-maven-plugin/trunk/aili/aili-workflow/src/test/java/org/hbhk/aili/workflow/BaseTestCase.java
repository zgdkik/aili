package org.hbhk.aili.workflow;

import org.hbhk.aili.base.server.context.UserContext;
import org.hbhk.aili.user.share.vo.CurrentUserVo;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring.xml"})
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false)
@Transactional
public class BaseTestCase extends AbstractTransactionalJUnit4SpringContextTests{
 

    @BeforeClass 
	public static void init(){
		CurrentUserVo userVo = new CurrentUserVo();
		userVo.setUserName("hbhk1");
		UserContext.setCurrentUser(userVo);
	}
}
