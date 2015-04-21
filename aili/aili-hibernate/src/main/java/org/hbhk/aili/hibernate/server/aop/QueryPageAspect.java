package org.hbhk.aili.hibernate.server.aop;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.persistence.NamedQuery;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.hbhk.aili.hibernate.server.service.IDaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @Description: mybatis增强处理
 * @author 何波
 * @date 2015年3月11日 上午10:05:24 
 *
 */
@Aspect
public class QueryPageAspect {

	protected static final Logger logger = LoggerFactory.getLogger(QueryPageAspect.class);
	
	@Autowired
	private IDaoService daoService;
	
	@Around("this(org.hbhk.aili.hibernate.server.dao.IBaseDao)")
	public Object doQuery(ProceedingJoinPoint pjp) throws Throwable {
		MethodSignature ms = (MethodSignature) pjp.getSignature();
		Method method = ms.getMethod();
		NamedQuery namedQuery = method.getAnnotation(NamedQuery.class);
		if(namedQuery!=null){
		}else {
			return daoService.save(pjp.getArgs()[0]);
		}
		Class<?> rt = method.getReturnType();
		Object obj = pjp.proceed(pjp.getArgs());
		return obj;
		
	}
	
	private Class<?> getGenericInterfaces(Class<?> clazz) throws Exception {
		Type[] types = clazz.getGenericInterfaces();
		ParameterizedType pType = null;
		if(types.length==0){
			//pType = clazz.;
			return clazz;
		}else{
			pType = (ParameterizedType) types[0];
		}
	
		Class<?> cls = (Class<?>) pType.getActualTypeArguments()[0];
		return cls;
	}
	

}
