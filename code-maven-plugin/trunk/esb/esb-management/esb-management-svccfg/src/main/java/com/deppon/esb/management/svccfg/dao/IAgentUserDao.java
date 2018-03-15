package com.deppon.esb.management.svccfg.dao;

import java.util.List;

import com.deppon.esb.security.domain.UserInfo;


/**
 * 快递代理用户信息Dao
 * @author k
 *
 */
public interface IAgentUserDao {
	
	/**
	 * 查询代理用户信息列表
	 * @return
	 */
	public List<UserInfo> queryAllAgentUser();
	
}
