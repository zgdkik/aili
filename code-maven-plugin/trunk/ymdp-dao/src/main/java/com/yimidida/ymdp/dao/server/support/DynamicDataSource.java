package com.yimidida.ymdp.dao.server.support;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.yimidida.ymdp.dao.server.aop.DataSourceContextHolder;

/**
 * 
 * @Description: aili框架核心处理和基本支持
 * @author 何波
 * @date 2015年3月11日 上午10:05:24 
 *
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		return DataSourceContextHolder.getDataSourceType();
	}
}
