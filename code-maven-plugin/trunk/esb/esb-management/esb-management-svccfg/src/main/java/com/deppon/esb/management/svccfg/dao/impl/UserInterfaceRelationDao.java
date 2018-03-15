package com.deppon.esb.management.svccfg.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.deppon.esb.management.common.dataaccess.ibatis.IBatis3DaoImpl;
import com.deppon.esb.management.svccfg.dao.IUserInterfaceRelationDao;
import com.deppon.esb.security.domain.UserInterfaceRelation;
@Repository
public class UserInterfaceRelationDao extends IBatis3DaoImpl implements IUserInterfaceRelationDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<UserInterfaceRelation> queryAllUserInterfaceRelation() {
		return getSqlSession().selectList("com.deppon.esb.security.domain.UserInterfaceRelation.queryAllUserInterfaceRelation");
	}
}
