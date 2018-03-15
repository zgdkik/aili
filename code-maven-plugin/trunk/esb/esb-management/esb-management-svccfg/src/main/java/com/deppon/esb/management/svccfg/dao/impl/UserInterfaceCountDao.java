package com.deppon.esb.management.svccfg.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.deppon.esb.management.common.dataaccess.ibatis.IBatis3DaoImpl;
import com.deppon.esb.management.svccfg.dao.IUserInterfaceCountDao;
import com.deppon.esb.security.domain.UserInterfaceCount;
@Repository
public class UserInterfaceCountDao extends IBatis3DaoImpl implements IUserInterfaceCountDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<UserInterfaceCount> queryAllUserInterfaceCount() {
		return getSqlSession().selectList("com.deppon.esb.security.domain.UserInterfaceCount.queryAllUserInterfaceCount");
	}
}
