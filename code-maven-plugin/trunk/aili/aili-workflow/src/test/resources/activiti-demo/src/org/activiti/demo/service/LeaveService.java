package org.activiti.demo.service;

import java.util.List;

import org.activiti.demo.domain.Leave;

/**
 * 
 * @Title: LeaveService.java
 * @Description: org.activiti.demo.service
 * @Package org.activiti.demo.service
 * @author hncdyj123@163.com
 * @date 2013-3-15
 * @version V1.0
 * 
 */
public interface LeaveService {

	/**
	 * 查询所有
	 * 
	 * @param Leave
	 * @return List<Leave>
	 * @throws Exception
	 */
	public List<Leave> listLeave(Leave leave) throws Exception;

	/**
	 * 获取总记录条数
	 * 
	 * @param Leave
	 * @return count
	 * @throws Exception
	 */
	public int listLeaveCount(Leave leave) throws Exception;

	/**
	 * 删除
	 * 
	 * @param Leave
	 * @throws Exception
	 */
	public void deleteLeave(Leave leave) throws Exception;

	/**
	 * 新增
	 * 
	 * @param Leave
	 * @throws Exception
	 */
	public String insertLeave(Leave leave) throws Exception;

	/**
	 * 修改
	 * 
	 * @param Leave
	 * @return
	 * @throws Exception
	 */
	public String updateLeave(Leave leave) throws Exception;

	/**
	 * 根据ID获取
	 * 
	 * @param leave
	 * @throws Exception
	 */
	public Leave getByIDLeave(Leave leave) throws Exception;
}
