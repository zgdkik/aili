package org.hbhk.aili.client.core.component.dataaccess;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.sql.DataSource;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.hbhk.aili.client.core.component.dataaccess.factory.AbstractTransactionProxyFactory;
import org.hbhk.aili.client.core.core.context.ApplicationContext;

/**
 * 
 *	事务工厂，获取指定的数据访问操作类的代理对象。比如一个DAO类对象。
 */
public final class DefaultTransactionFactory extends AbstractTransactionProxyFactory implements HsqldbDataSource {
	
	private static DefaultTransactionFactory instance = new DefaultTransactionFactory();
	
	// ibatis SQL映射文件名称
	private static final String CONFIG_LOCATION = "mybatis.xml";
	
	private DefaultTransactionFactory() {
		// super call registerDefault to register default SqlSessionFactory
	}
	
	public static DefaultTransactionFactory getInstance() {
		return instance;
	}
	
	public static SqlSessionFactory getSqlSessionFactory(){
		return instance.getDefaultSqlSessionFactory();
	}
	
/*	public static <T> T getProxy(Class<T> service) {
		return instance.getProxyClass(service);
	}
	*/
	@Override
	public void registerDefaultSqlSessionFactory() {
		// session工厂对象
		SqlSessionFactory sessionFactory = getFactorys().get(DEFAULT_SQLSESSIONFACTORY);
		synchronized (getFactorys()) {
			if (sessionFactory != null) {
				return;
			} else if (getFactorys().get(DEFAULT_SQLSESSIONFACTORY) == null) {
				// 如果在第一个线程put之前第二线程已经取出sessionFactory,所以同步之后需要再次判断是否
				getFactorys().put(DEFAULT_SQLSESSIONFACTORY, buildFactory());
			}
		}
	}
	
	@Override
	public SqlSessionFactory getDefaultSqlSessionFactory() {
		if (getFactorys().get(DEFAULT_SQLSESSIONFACTORY) == null) {
			registerDefaultSqlSessionFactory();
		}
		return getFactorys().get(DEFAULT_SQLSESSIONFACTORY);
	}
	
	/**
	 * 创建SqlSessionFactory对象。首相是通过查找主应用目录下的mybatis配置文件，
	 * 来创建SqlsessionFactory对象，如果此文件不存在或者创建失败，那么就创建默认的SqlsessionFactory对象。
	 * buildFactory
	 * @return SqlSessionFactory
	 * @since:0.6
	 */
	private SqlSessionFactory buildFactory() {
		File mybatis = null;
		try {
			mybatis = new File(ApplicationContext.getAppConfigHome() + File.separator + CONFIG_LOCATION);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		InputStream inStream = null;
		SqlSessionFactory sessionFactory = null;
		try {
			inStream = new FileInputStream(mybatis);
			SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
			sessionFactory = builder.build(inStream);
			return sessionFactory;
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		DataSource dataSource = new PooledDataSource(DB_DRIVER, DB_URL,DB_USERNAME,DB_PASSWORD);
		TransactionFactory transactionFactory = new JdbcTransactionFactory();
		Environment environment = new Environment("development", transactionFactory, dataSource);
		Configuration configuration = new Configuration(environment);
		sessionFactory = new SqlSessionFactoryBuilder().build(configuration);
		
		return sessionFactory;
	}

}
