package org.hbhk.aili.core.server.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.aili.core.server.tx.aop.Tx;
import org.hbhk.aili.core.server.tx.context.ConnectionContext;
import org.hbhk.aili.core.server.tx.context.ConnectionManager;

/**
 * JDK动态代理类
 */
public class ProxyFactory implements InvocationHandler {

	private Log log = LogFactory.getLog(ProxyFactory.class);

	// 需要代理的目标对象
	private Object targetObject;

	@SuppressWarnings("unchecked")
	public <T> T newInstance(Class<?> targetObject) {
		// 将目标对象传入进行代理
		if (targetObject == null) {
			throw new NullPointerException("targetObject is null");
		}
		try {
			this.targetObject = targetObject.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		// 返回代理对象
		return (T) Proxy.newProxyInstance(targetObject.getClassLoader(),
				targetObject.getInterfaces(), this);
	}

	public Object invoke(Object proxy, Method method, Object[] args)// invoke方法
			throws Throwable {
		// 设置数据库连接
		Connection conn = ConnectionManager.getConnectionManager().getConn();
		ConnectionContext.setConnLocal(conn);
		// 检查是否开启事务
		Tx tx = method.getAnnotation(Tx.class);
		if (tx == null) {
			tx = proxy.getClass().getAnnotation(Tx.class);
		}
		if (tx != null) {
			conn = ConnectionContext.getConnLocal();
			conn.setAutoCommit(false);
		}
		log.info("执行代理开始");
		// 调用invoke方法，ret存储该方法的返回值
		try {
			Object ret = method.invoke(targetObject, args);
			if (conn != null && tx != null) {
				conn.commit();
			}
			log.info("执行代理结束");
			return ret;
		} catch (Exception e) {
			if (conn != null && tx != null) {
				conn.rollback();
			}
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				conn.close();
			}
		}

	}

}
