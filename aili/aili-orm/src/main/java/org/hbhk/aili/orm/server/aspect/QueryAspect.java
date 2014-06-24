package org.hbhk.aili.orm.server.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.hbhk.aili.orm.server.annotation.NativeQuery;
import org.hbhk.aili.orm.server.handler.NativeQueryHandler;
import org.hbhk.aili.orm.server.service.IDaoService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;

@Aspect
public class QueryAspect implements Ordered, InitializingBean {

	protected  final String pointcut = "execution(* org.hbhk.*.*.server.dao.*.*(..))";
	@Autowired
	private IDaoService daoService;

	private NativeQueryHandler nativeQueryHandler;

	public void afterPropertiesSet() throws Exception {
		nativeQueryHandler = new NativeQueryHandler(daoService);
	}

	public int getOrder() {
		return 20;
	}

	@Around("this(org.hbhk.aili.orm.server.dao.GenericEntityDao)")
	public Object doQuery(ProceedingJoinPoint pjp) throws Throwable {
		MethodSignature ms = (MethodSignature) pjp.getSignature();
		NativeQuery nativeQuery = ms.getMethod().getAnnotation(NativeQuery.class);
		if (nativeQuery != null) {
			return  nativeQueryHandler.handleNativeQuery(nativeQuery, pjp);
		} else {
			return pjp.proceed(pjp.getArgs());
		}
	}
}
