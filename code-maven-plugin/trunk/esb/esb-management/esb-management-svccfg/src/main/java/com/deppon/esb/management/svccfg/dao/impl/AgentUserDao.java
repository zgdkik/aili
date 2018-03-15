package com.deppon.esb.management.svccfg.dao.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.deppon.esb.management.common.dataaccess.ibatis.IBatis3DaoImpl;
import com.deppon.esb.management.svccfg.dao.IAgentUserDao;
import com.deppon.esb.security.domain.UserInfo;

/**
 * 快递代理用户信息Dao
 * @author k
 *
 */
@Repository
public class AgentUserDao extends IBatis3DaoImpl implements IAgentUserDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<UserInfo> queryAllAgentUser() {
		return getSqlSession().selectList("com.deppon.esb.security.domain.UserInfo.queryAllAgentUser");
	}

}
