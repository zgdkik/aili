package com.deppon.esb.management.svccfg.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.deppon.esb.management.common.dataaccess.ibatis.IBatis3DaoImpl;
import com.deppon.esb.management.svccfg.dao.IInterfaceCountDao;
import com.deppon.esb.security.domain.InterfaceCount;
@Repository
public class InterfaceCountDao extends IBatis3DaoImpl implements IInterfaceCountDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<InterfaceCount> queryAllInterfaceCount() {
		return getSqlSession().selectList("com.deppon.esb.security.domain.InterfaceCount.queryAllInterfaceCount");
	}
}
