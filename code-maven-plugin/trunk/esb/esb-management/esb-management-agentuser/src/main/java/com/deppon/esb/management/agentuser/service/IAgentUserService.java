package com.deppon.esb.management.agentuser.service;

import java.util.List;

import com.deppon.esb.management.agentuser.domain.AgentUserInfo;
import com.deppon.esb.management.agentuser.view.AgentUserQueryBean;
import com.deppon.esb.security.domain.UserInfo;

public interface IAgentUserService {
	/**
	 * 新增代理用户
	 * @param user
	 * @return
	 */
	public int addAgentUser(UserInfo user);
	/**
	 * 作废代理用户(只作废不删除)
	 * @param user
	 * @return
	 */
	public int canelAgenUser(List<String> users);
	/**
	 * 激活代理用户(将作废的用户激活)
	 * @param user
	 * @return
	 */
	public int activeAgenUser(List<String> users);
	/**
	 * 查询代理用户信息列表
	 * @return
	 */
	public List<UserInfo> queryAllAgenUser();
	
	/**
	 * 查询代理用户信息列表
	 * @param bean
	 * @return
	 */
	public List<UserInfo> queryAgenUser(AgentUserQueryBean bean);
	
	/**
	 * 
	 * @param user
	 * @return
	 */
	public int updateAgentUser(UserInfo user);
	
	/**
	 * 删除代理用户
	 */
	 public int deleteAgentUser(List<String> ids);
	
}
