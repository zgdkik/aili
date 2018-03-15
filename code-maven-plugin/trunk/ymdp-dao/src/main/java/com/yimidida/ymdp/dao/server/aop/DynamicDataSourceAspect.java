package com.yimidida.ymdp.dao.server.aop;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.AnnotationTransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionAttribute;
import org.springframework.transaction.interceptor.TransactionAttributeSource;

@Aspect
public class DynamicDataSourceAspect implements Ordered {
	protected static final Logger logger = LoggerFactory
			.getLogger(DynamicDataSourceAspect.class);

	private TransactionAttributeSource transactionAttributeSouce;

	@Pointcut("execution(* com.yimidida.*.*.service.*.*(..))")
	public void pointcut() {
	}
	@Around("pointcut()")
	public Object doExecute(ProceedingJoinPoint pjp) throws Throwable {
		String strategy = getDataSourceStrategy(pjp);
		if (StringUtils.isEmpty(strategy)) {
			logger.info("数据源当前 Read/Write Status: {}",
					DataSourceContextHolder.getDataSourceType());
			return pjp.proceed(pjp.getArgs());
		}
		try {
			logger.info("数据源当前 Read/Write Status: {}", strategy);
			DataSourceContextHolder.setDataSourceType(strategy);
			Object rtn = pjp.proceed(pjp.getArgs());
			return rtn;
		} finally {
			DataSourceContextHolder.remove();
		}
	}

	@Override
	public int getOrder() {
		return 0;
	}

	public String getDataSourceStrategy(ProceedingJoinPoint pjp)
			throws Throwable {
		if (transactionAttributeSouce == null) {
			transactionAttributeSouce = new AnnotationTransactionAttributeSource();
		}
		MethodSignature ms = (MethodSignature) pjp.getSignature();
		TransactionAttribute ta = transactionAttributeSouce
				.getTransactionAttribute(ms.getMethod(), pjp.getTarget()
						.getClass());
		logger.debug("determine datasource for query:{}.{}", ms
				.getDeclaringType().getName(), ms.getMethod().getName());
		logger.debug("Current operation's transaction status: {}",
				ta == null ? "null" : ta.toString());

		String currentStatus = DataSourceContextHolder.getDataSourceType();
		if (ta != null) {
			if (ta.getPropagationBehavior() == TransactionDefinition.PROPAGATION_REQUIRES_NEW) {
				logger.debug("New writable connection is required for new transaction.");
				currentStatus = DataSourceConst.WRITE;
			} else {
				currentStatus = (ta != null && ta.isReadOnly()) ? DataSourceConst.READ
						: DataSourceConst.WRITE;
			}
		}
		return currentStatus;
	}

}
