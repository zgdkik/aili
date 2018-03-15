package com.deppon.esb.management.agentuser.dao.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.deppon.esb.management.agentuser.dao.IAgentUserDao;
import com.deppon.esb.management.agentuser.view.AgentUserQueryBean;
import com.deppon.esb.management.common.dataaccess.ibatis.IBatis3DaoImpl;
import com.deppon.esb.security.domain.UserInfo;

/**
 * 快递代理用户信息Dao
 * @author qiancheng
 *
 */
@Repository("expressAgentUserDao")
public class AgentUserDao extends IBatis3DaoImpl implements IAgentUserDao{

	@Override
	public int addAgentUser(UserInfo user) {
		return getSqlSession().insert("com.deppon.esb.security.domain.UserInfoOperation.addAgentUser", user);
	}

	@Override
	public int canelAgenUser(List<String> usernames) {
		return getSqlSession().update("com.deppon.esb.security.domain.UserInfoOperation.canelAgenUser", usernames);
	}

	@Override
	public int activeAgenUser(List<String> user) {
		return getSqlSession().update("com.deppon.esb.security.domain.UserInfoOperation.activeAgenUser", user);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserInfo> queryAllAgentUser() {
		return getSqlSession().selectList("com.deppon.esb.security.domain.UserInfoOperation.queryAllAgentUser");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserInfo> queryAgentUser(AgentUserQueryBean bean) {
		RowBounds rowBounds = new RowBounds();
		return getSqlSession().selectList("com.deppon.esb.security.domain.UserInfoOperation.queryAgentUser",bean , rowBounds);
	}

	@Override
	public int updateAgentUser(UserInfo info) {
		return getSqlSession().update("com.deppon.esb.security.domain.UserInfoOperation.updateAgentUser", info);
	}

	@Override
	public int deleteAgentUser(List<String> ids) {
		return getSqlSession().delete("com.deppon.esb.security.domain.UserInfoOperation.deleteAgentUser", ids);
	}

}
