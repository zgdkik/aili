package org.hbhk.aili.client.core.component.dataaccess.factory;

import java.io.File;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.sf.cglib.proxy.Enhancer;

import org.apache.ibatis.session.SqlSessionFactory;
import org.hbhk.aili.client.core.component.dataaccess.transaction.TransactionInterceptor;

public abstract class AbstractTransactionProxyFactory implements ITransactionFactory  {

	private  Map<String , SqlSessionFactory> factorys
		= new ConcurrentHashMap<String, SqlSessionFactory>();
	protected AbstractTransactionProxyFactory() {
		this.registerDefaultSqlSessionFactory();
	}
	/**
	 * 
	 * @see org.hbhk.aili.client.core.component.dataaccess.ISqlSessionFactoryRegister#register(java.lang.String, org.apache.ibatis.session.SqlSessionFactory)
	 * register
	 * @param factoryName
	 * @param factorys
	 * @since:
	 */
	@Override
	public void register(String factoryName, SqlSessionFactory factorys) {
		
	}
	/**
	 * 
	 * @see org.hbhk.aili.client.core.component.dataaccess.ISqlSessionFactoryRegister#register(java.lang.String, java.io.InputStream)
	 * register
	 * @param factoryName
	 * @param inStream
	 * @since:
	 */
	@Override
	public void register(String factoryName, InputStream inStream) {
		
	}
	/**
	 * 
	 * @see org.hbhk.aili.client.core.component.dataaccess.ISqlSessionFactoryRegister#register(java.lang.String, java.io.File)
	 * register
	 * @param factoryName
	 * @param config
	 * @since:
	 */
	@Override
	public void register(String factoryName, File config) {
		
	}

	@Override
	public void register(String factoryName, String configPath) {
		
	}
	/**
	 * 
	 * @see org.hbhk.aili.client.core.component.dataaccess.ISqlSessionFactoryRegister#getSqlSessionFactory(java.lang.String)
	 * getSqlSessionFactory
	 * @param factoryName
	 * @return
	 * @since:
	 */
	@Override
	public SqlSessionFactory getSqlSessionFactory(String factoryName) {
		return null;
	}
	/**
	 * 
	 * @see org.hbhk.aili.client.core.component.dataaccess.factory.ITransactionFactory#getProxyClass(java.lang.Class)
	 * getProxyClass
	 * @param <T>
	 * @param service
	 * @return
	 * @since:
	 */
	@SuppressWarnings("unchecked")
	public <T> T getProxyClass(Class<T> service){
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(service);
		enhancer.setCallback(new TransactionInterceptor(service,this.getDefaultSqlSessionFactory()));
		T t = (T) enhancer.create();
		return t;
	}
	public  Map<String, SqlSessionFactory> getFactorys() {
		return factorys;
	}
	
}
