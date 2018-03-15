package com.deppon.esb.management.user.service;

import java.util.Map;

import com.deppon.esb.management.user.domain.SysUserInfo;

/**
 * The Interface ISysUserService.
 */
public interface ISysUserService {

	/**
	 * Query sys users.
	 * 
	 * @param sysUserInfo
	 *            the sys user info
	 * @param start
	 *            the start
	 * @param limit
	 *            the limit
	 * @return the map
	 */
	public Map<String, Object> querySysUsers(SysUserInfo sysUserInfo, int start, int limit);
	
	/**
	 * Delete sys users by ids.
	 * 
	 * @param ids
	 *            the ids
	 * @return the int
	 */
	public int deleteSysUsersByIds(String[] ids);

	/**
	 * Update sys user.
	 * 
	 * @param sysUserInfo
	 *            the sys user info
	 * @return the int
	 */
	public int updateSysUser(SysUserInfo sysUserInfo);

	/**
	 * 添加 sys user.
	 * 
	 * @param sysUserInfo
	 *            the sys user info
	 * @return the int
	 */
	public int addSysUser(SysUserInfo sysUserInfo);

	/**
	 * Query sys user by sys user name.
	 * 
	 * @param sysUserName
	 *            the sys user name
	 * @return the string
	 */
	public String querySysUserBySysUserName(String sysUserName);

	/**
	 * Modify pass word.
	 * 
	 * @param sysUserInfo
	 *            the sys user info
	 * @return true, if successful
	 */
	public boolean modifyPassWord(SysUserInfo sysUserInfo);

	/**
	 * Attempt login.
	 * 
	 * @param userName
	 *            the user name
	 * @param password
	 *            the password
	 * @return the sys user info
	 */
	public SysUserInfo attemptLogin(String userName, String password);

}
