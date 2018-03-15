package org.hbhk.aili.client.core.component.dataaccess;

import java.io.File;
import java.io.InputStream;

import org.apache.ibatis.session.SqlSessionFactory;

/**
 * 
 *	这是SqlSessionFactory注册器接口定义。用来注册指定名字的SqlSessionFactory类。
 *此注册器应该包含了一个默认的SqlSessionFactory对象。当不指定名字获取SqlSessionFactory
 *的时候那么代码返回的SqlSessionFactor对象就是默认的SqlSessionFactory
 */
public interface ISqlSessionFactoryRegister {
	public static final String DEFAULT_SQLSESSIONFACTORY="default.sqlsessionFactory";
	
	/**
	 * 
	 * <p>Title: register</p>
	 * <p>Description: 直接注册一个SqlSessionFactory</p>
	 * @param factoryName 工厂名称
	 * @param factorys SQL session 工厂
	 */
	void register(String factoryName, SqlSessionFactory factorys);
	
	/**
	 * 通过输入流来注册一个SqlSessionFactory
	 * register
	 * @param factoryName 工厂名称
	 * @param inStream 输入流
	 * @return void
	 * @since:0.6
	 */
	void register(String factoryName, InputStream inStream);
	
	/**
	 * 传入Mybatis的配置文件来向SqlSessionFactory注册器注册一个SqlSessionFactory对象
	 * register
	 * @param factoryName 工厂名称
	 * @param config 配置文件
	 * @return void
	 * @since:0.6
	 */
	void register(String factoryName, File config);
	
	/**
	 * 通过传入Mybatis配置文件路径来向SqlSessionFactory注册器注册一个SqlSessionFactory对象
	 * register
	 * @param factoryName 工厂名称
	 * @param configPath 配置路径
	 * @return void
	 * @since:0.6
	 */
	void register(String factoryName, String configPath);
	
	/**
	 * 
	 * 注册如默认的SqlSessionFactory只能注册一次，
	 * 一般都是应用启动后注册一个应用级别的SqlSessionFactory
	 * @param      
	 * @return      
	 * @exception
	 */
	void registerDefaultSqlSessionFactory();
	
	/**
	 * 从注册器中获取指定名称的SqlSessionFactory对象
	 * getSqlSessionFactory
	 * @param factoryName 工厂名称
	 * @return
	 * @return SqlSessionFactory
	 * @since:0.6
	 */
	SqlSessionFactory getSqlSessionFactory(String factoryName);
	
	/**
	 * 获取SqlSessionFactory注册默认的SqlSessionFactory对象
	 * getDefaultSqlSessionFactory
	 * @return
	 * @return SqlSessionFactory
	 * @since:0.6
	 */
	SqlSessionFactory getDefaultSqlSessionFactory();
}