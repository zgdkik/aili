package com.deppon.esb.management.common.test;

import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

/**
 * 一期移植
 * 
 * 结合dbunit的测试基类；
 * 使用DBUnitTestHelper来初始化测试数据；
 * 
 * 测试数据文件如果有待替换的值，则需要覆盖getDatasetReplacements方法；
 * 
 * 实例请参考：
 * 	/esb-management-dao/src/test/java/com/deppon/esb/management/dao/SvcPointDaoTest.java
 * 用于该测试的数据文件为：
 * 	/esb-management-dao/src/test/resources/com/deppon/esb/management/dao/META-INF/dao/data/svcpoint.xml
 * 配置文件为：
 * 	/esb-management-dao/src/test/resources/com/deppon/esb/management/dao/META-INF/dao/spring-test.xml
 * 
 * @author zhengwl
 *
 */
public abstract class DaoDBUnitSupportUnitTests extends AbstractTransactionalJUnit4SpringContextTests {
	@Autowired
	private DBUnitTestHelper dbunitHelper;
	
	@Before
	public void onSetUpInTransaction() throws Exception {
		dbunitHelper.setReplacements(this.getDatasetReplacements());
		dbunitHelper.setUp();
	}

	@After
	public void onTearDownInTransaction() throws Exception {
		dbunitHelper.tearDown();
	}

	public DBUnitTestHelper getDbunitHelper() {
		return dbunitHelper;
	}

	public void setDbunitHelper(DBUnitTestHelper dbunitHelper) {
		this.dbunitHelper = dbunitHelper;
	}

	/**
	 * 返回用户测试数据文件中的替换值；可以由子类实现；
	 * 
	 * 返回一个map对象，其中的key是待替换的值，value是用于替换的值；
	 * @return
	 */
	protected Map getDatasetReplacements() {
		return null;
	}
}
