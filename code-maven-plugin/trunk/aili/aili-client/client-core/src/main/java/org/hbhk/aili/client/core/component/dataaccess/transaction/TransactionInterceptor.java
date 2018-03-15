package org.hbhk.aili.client.core.component.dataaccess.transaction;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.hbhk.aili.client.core.component.dataaccess.annotation.Mapper;


@SuppressWarnings("rawtypes")
public class TransactionInterceptor implements MethodInterceptor {
	
	private static Log        log = LogFactory.getLog(TransactionInterceptor.class);
	
	private SqlSessionFactory factory;
	private Class             type;
	
	public TransactionInterceptor(Class type, SqlSessionFactory factory) {
		if (type == null || factory==null) {
			throw new NullPointerException(
					"TransactionInterceptor construction args is null...");
		}
		this.type        = type;
		this.factory = factory;
	}

	public Object intercept(final Object obj, Method method,
			final Object[] args, final MethodProxy proxy) throws Throwable {
		
		Annotation[] annotations = method.getDeclaredAnnotations();
		
		for (Annotation annotation : annotations) {
			
			if (annotation instanceof org.hbhk.aili.client.core.component.dataaccess.annotation.Transaction) {
				return transactionMethod(obj, proxy, args);
			}
		}

		SqlSession sqlSession = factory.openSession(false);
		fillField(obj, sqlSession);
		
		
		return proxy.invokeSuper(obj, args);
	}

	private Object transactionMethod(final Object obj, final MethodProxy proxy,
			final Object[] args) {
		/*Transaction transaction = new Transaction(factory.openSession(false));
		fillField(obj, transaction.getSqlSession());
		return transaction.execute(new TransactionCallback() {
			@Override
			public Object doInTransaction(SqlSession sqlSession)
					throws Throwable {
				return proxy.invokeSuper(obj, args);
			}
		});*/
		return null;
	}

	/**
	 * 当前服务层方法每一次方法调用之后，映射所对应的底层链接都会断开
	 * @param obj
	 * @param sessionWrap
	 */
	@SuppressWarnings({ "unchecked"})
	private void fillField(Object obj,SqlSession sqlSession) {
		Field[] fields = type.getDeclaredFields();
		for (Field field : fields) {
			Annotation[] fieldAnnotations = field.getDeclaredAnnotations();
			for (Annotation annotation : fieldAnnotations) {
				if (annotation instanceof Mapper) {
					Class fieldType = field.getType();
					boolean oldAccessible = field.isAccessible();
					if (!oldAccessible) {
						field.setAccessible(true);
					}
					try {
						field.set(obj, sqlSession.getMapper(fieldType));
					} catch (IllegalArgumentException e) {
						log.error("映射参数违法", e);
					} catch (IllegalAccessException e) {
						log.error("类字段私有，无法访问", e);
					} finally {
						field.setAccessible(oldAccessible);
					}
				}
			}
		}
	}
}
