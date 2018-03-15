package org.activiti.demo.service;

import java.util.List;

import org.activiti.demo.domain.SysUser;

/**
 * 
 * @Title: SysUserService.java
 * @Description: org.activiti.demo.service
 * @Package org.activiti.demo.service
 * @author hncdyj123@163.com
 * @date 2013-3-7
 * @version V1.0
 * 
 */
public interface SysUserService {
	/** 增加 **/
	public void insertSysUser(SysUser sysUser) throws Exception;

	/** 修改 **/
	public void updateSysUser(SysUser sysUser) throws Exception;

	/** 删除 **/
	public void deleteSysUser(SysUser sysUser) throws Exception;

	/** 查询所有 **/
	public List<SysUser> listSysUser(SysUser sysUser) throws Exception;

	/** 查询记录总数 **/
	public Integer listSysUserCount(SysUser sysUser) throws Exception;

	/** 根据id获取 **/
	public SysUser findSysUserByID(SysUser sysUser) throws Exception;
}
