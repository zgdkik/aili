package org.hbhk.aili.esb.server.security.authorize;

import java.util.Map;

import org.hbhk.aili.esb.server.security.domain.UserInfo;
/**
 * 获取用户信息
 * @author Administrator
 *
 */
public interface IUserConfigLoader {
	
	/**
	 * 获取所有的用户信息.
	 * 
	 * @return the all
	 * @author k
	 */
	Map<String,UserInfo> getAll();
	
	/**
	 * 根据用户名获取用户信息
	 * @param name
	 * @return
	 */	
	UserInfo getUser(String name);
	
	/**
	 * 刷新用户信息
	 * @author k
	 */
	void refresh();
	
}
