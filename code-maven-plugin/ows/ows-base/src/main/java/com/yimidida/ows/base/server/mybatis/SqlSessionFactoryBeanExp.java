package com.yimidida.ows.base.server.mybatis;

import java.io.IOException;

import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NestedIOException;
import org.springframework.core.io.Resource;

public class SqlSessionFactoryBeanExp extends SqlSessionFactoryBean {

	private Logger log = LoggerFactory.getLogger(getClass());

	private Resource[] mapperLocations;

	@Override
	protected SqlSessionFactory buildSqlSessionFactory() throws IOException {
		try {
			return super.buildSqlSessionFactory();
		} catch (NestedIOException e) {
			log.error(e.getMessage(), e);
			throw new NestedIOException("Failed to parse mapping resource: '"
					+ mapperLocations + "'", e);
		} finally {
			ErrorContext.instance().reset();
		}
	}

}
