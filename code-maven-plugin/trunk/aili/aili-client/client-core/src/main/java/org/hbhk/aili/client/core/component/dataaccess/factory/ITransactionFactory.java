package org.hbhk.aili.client.core.component.dataaccess.factory;

import org.hbhk.aili.client.core.component.dataaccess.ISqlSessionFactoryRegister;

/**
 * 
 *	事务工厂类
 */
public interface ITransactionFactory extends ISqlSessionFactoryRegister {
	
	/**
	 * 获取某个对象的代理。这个方法是为了获取某个访问数据库操作的类的代理对象。
	 * 并且这个类方法上需要打上com.deppon.foss.framework.client.component.dataaccess.annotation.Transaction标注，
	 * 表示这是一个事务方法。
	 * getProxyClass
	 * @param <T>
	 * @param service 类名
	 * @return
	 * @return T
	 * @since:0.6
	 */
	<T> T getProxyClass(Class<T> service);
}
