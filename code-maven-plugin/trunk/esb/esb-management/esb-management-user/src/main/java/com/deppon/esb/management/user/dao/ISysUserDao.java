package com.deppon.esb.management.user.dao;

import java.util.List;
import java.util.Map;

import com.deppon.esb.management.user.domain.SysUserInfo;

/**
 * The Interface ISysUserDao.
 * 
 * @Description 系统用户DAO层
 * @author HuangHua
 * @Date 2012-4-18
 */
public interface ISysUserDao {

	/**
	 * Query sys user count.
	 * 
	 * @param map
	 *            the map
	 * @return the integer
	 * @Description
	 */
	public Integer querySysUserCount(Map<String, Object> map);

	/**
	 * Query sys users.
	 * 
	 * @param map
	 *            the map
	 * @return the list
	 * @Description
	 */
	public List<SysUserInfo> querySysUsers(Map<String, Object> map);

	/**
	 * Delete sys users by ids.
	 * 
	 * @param ids
	 *            the ids
	 * @return the int
	 * @Description
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
	 * @Description 通过系统用户名查询密码
	 */
	public String querySysUserBySysUserName(String sysUserName);

	/**
	 * Modify pass word.
	 * 
	 * @param sysUserInfo
	 *            the sys user info
	 * @return the int
	 * @Description 通过用户名修改密码
	 */
	public int modifyPassWord(SysUserInfo sysUserInfo);

	/**
	 * Attempt login.
	 * 
	 * @param sysUserName
	 *            the sys user name
	 * @param password
	 *            the password
	 * @return the sys user info
	 */
	public SysUserInfo attemptLogin(String sysUserName, String password);
}
