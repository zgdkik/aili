package org.hbhk.aili.hibernate.server.aop;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.NamedQuery;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.hbhk.aili.hibernate.server.dao.IBaseDao;
import org.hbhk.aili.hibernate.server.service.IDaoService;
import org.hbhk.aili.hibernate.server.spring.ModelClassSupport;
import org.hbhk.aili.hibernate.server.support.BaseDaoWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

@Aspect
public class HibernateAspect implements InitializingBean{

	protected static final Logger logger = LoggerFactory.getLogger(HibernateAspect.class);
	
	@Autowired
	private IDaoService daoService;
	
	private BaseDaoWrapper baseDaoWrapper;
	
	@Around("this(org.hbhk.aili.hibernate.server.dao.IBaseDao)")
	public Object doQuery(ProceedingJoinPoint pjp) throws Throwable {
		MethodSignature ms = (MethodSignature) pjp.getSignature();
		Method m = ms.getMethod();
		NamedQuery namedQuery = m.getAnnotation(NamedQuery.class);
		if(namedQuery!=null){
			
		}else if(IBaseDao.class.isAssignableFrom(m.getDeclaringClass())){
				String  mName = m.getName();
				if( mName.equals("getById") || 
						 mName.equals("deleteById")){
					ModelClassSupport mcs = (ModelClassSupport)pjp.getTarget();
					Class<?> modelClass = mcs.getModelClass();
					List<Class<?>> list = new ArrayList<Class<?>>();
					List<Object> args = new ArrayList<Object>();
					list.add(Class.class);
					list.addAll(Arrays.asList(m.getParameterTypes()));
					args.add(modelClass);
					args.addAll(Arrays.asList(pjp.getArgs()));
					return BaseDaoWrapper.class.getMethod(mName, list.toArray(new Class<?>[]{})).
					invoke(baseDaoWrapper, args.toArray());
				}else{
					return BaseDaoWrapper.class.getMethod(mName, m.getParameterTypes()).
							invoke(baseDaoWrapper, pjp.getArgs());
				}
		}
		return  pjp.proceed(pjp.getArgs());
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

	@Override
	public void afterPropertiesSet() throws Exception {
		baseDaoWrapper =  new BaseDaoWrapper(daoService);
	}
	

}
