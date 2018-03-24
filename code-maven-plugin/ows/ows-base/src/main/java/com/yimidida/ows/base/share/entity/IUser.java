package com.yimidida.ows.base.share.entity;

import java.util.Set;

public interface IUser extends IEntity {

	String getUserId();

	String getCompCode();

	String getCompName();

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

	String getDeptName();

	String getDeptTypeCode();

	String getUserType();

	String getStatus();

	/**
	 * 用户部门
	 * 
	 * @return
	 */
	Set<String> getUserDeptList();

}
