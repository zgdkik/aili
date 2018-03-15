package org.hbhk.aili.sql.server.service;

import org.hbhk.aili.sql.share.model.SqlContext;

/**
 * 
* @Description: sql生成器 接口
* @author hbhk 
* @date 2015年6月30日 上午9:19:13
 */
public interface ISqlBuilder {

	SqlContext insertBuilder(Class<?> cls);
	SqlContext updateBuilder(Class<?> cls);
	SqlContext selectBuilder(Class<?> cls);
}
