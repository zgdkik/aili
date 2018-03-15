package org.hbhk.aili.base.shared.domain;

import java.util.List;
import java.util.Set;

public interface IUser  extends IEntity{
	
	@Deprecated
	List<IRole> getRoles();
	
	@Deprecated
	void setRoles(List<IRole> roles);
	
	/**
	 * 获取用户的所有角色id
	 * getRoleids
	 * @return
	 * @return Set<String>
	 * @since:0.9
	 */
	Set<String> getRoleids();
	
	/**
	 * 获取用户所能访问的URI
	 * queryAccessUris
	 * @return
	 * @return Set<String>
	 * @since:0.9
	 */
	Set<String> queryAccessUris();
	
	void setRoleids(Set<String> roleids);
	
	void setUserName(String userName);
	
	String getUserName();
}
