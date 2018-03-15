package org.hbhk.aili.client.core.component.dataaccess;

import org.apache.ibatis.session.SqlSession;

/**
 * 回调接口，传入SqlSession的包装对象SqlSessionWrap
 *
 */
public interface TransactionCallback {

	public Object doInTransaction(SqlSession sqlSession) throws Throwable;
	
}
