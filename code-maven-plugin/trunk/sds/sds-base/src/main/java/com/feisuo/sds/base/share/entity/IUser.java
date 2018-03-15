package com.feisuo.sds.base.share.entity;

import java.util.Set;

public interface IUser extends IEntity {
	
	
	String getUserId();

	/**
	 * 获取用户的所有角色id getRoleids
	 * 
	 * @return
	 * @return Set<String>
	 * @since:0.9
	 */
	Set<String> getRoleCodes();

	/**
	 * 获取用户所能访问的URI queryAccessUris
	 * 
	 * @return
	 * @return Set<String>
	 * @since:0.9
	 */
	Set<String> queryAccessUris();
	
	Set<String> accessCodes();

	void setRoleCodes(Set<String> roleCodes);

	void setUserName(String userName);
	String getName();

	String getUserName();

	void setDeptCode(String deptCode);

	String getDeptCode();
	
	String getUserType();
	/**
	 * 
	* @Title: getCity 
	* @Description:用户所在城市
	* @param @return 
	* @return String    返回类型 
	* @author hbhk  
	* @throws
	 */
	String getUserCity();
	
	String getStatus();

}
