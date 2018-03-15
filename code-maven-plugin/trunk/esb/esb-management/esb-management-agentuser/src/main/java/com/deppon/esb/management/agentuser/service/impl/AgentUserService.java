package com.deppon.esb.management.agentuser.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.deppon.esb.management.agentuser.dao.IAgentUserDao;
import com.deppon.esb.management.agentuser.domain.AgentUserInfo;
import com.deppon.esb.management.agentuser.service.IAgentUserService;
import com.deppon.esb.management.agentuser.view.AgentUserQueryBean;
import com.deppon.esb.security.domain.UserInfo;

@Service
public class AgentUserService implements IAgentUserService{
	@Resource(name="expressAgentUserDao")
	private IAgentUserDao agentUserDao;
	@Override
	public int addAgentUser(UserInfo user) {
		return agentUserDao.addAgentUser(user);
	}



	@Override
	public List<UserInfo> queryAgenUser(AgentUserQueryBean bean) {
		return agentUserDao.queryAgentUser(bean);
	}

	public IAgentUserDao getAgentUserDao() {
		return agentUserDao;
	}

	public void setAgentUserDao(IAgentUserDao agentUserDao) {
		this.agentUserDao = agentUserDao;
	}



	@Override
	public int canelAgenUser(List<String> users) {
		return agentUserDao.canelAgenUser(users);
	}



	@Override
	public int activeAgenUser(List<String> users) {
		return agentUserDao.activeAgenUser(users);
	}

	@Override
	public List<UserInfo> queryAllAgenUser() {
		return agentUserDao.queryAllAgentUser();
	}

	@Override
	public int updateAgentUser(UserInfo user) {
		return agentUserDao.updateAgentUser(user);
	}



	@Override
	public int deleteAgentUser(List<String> ids) {
		return agentUserDao.deleteAgentUser(ids);
	}
}
