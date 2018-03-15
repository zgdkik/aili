package org.hbhk.aili.base.share.entity;

import java.util.Set;

public interface IUser extends IEntity {

	String getUserId();

	String getCompCode();

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

	String getName();

	String getUserName();

	String getDeptCode();

	String getUserType();

	String getStatus();

	/**
	 * 用户部门
	 * 
	 * @return
	 */
	Set<String> getUserDeptList();

}
