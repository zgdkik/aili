package org.hbhk.aili.jpa.server.dao;

import java.util.Map;

import org.hbhk.aili.jpa.server.dao.IBaseDao;
import org.hbhk.aili.jpa.server.model.UserInfo;
import org.hbhk.aili.jpa.server.support.Page;
import org.hbhk.aili.jpa.server.support.Pagination;


public interface IUserDao1 extends  IBaseDao<UserInfo, Long> {

	/**
	 * 
	* @author 何波
	* @Description: 注解方式
	* @param params
	* @param page
	* @return   
	* Pagination<UserInfo>   
	* @throws
	 */
	Pagination<UserInfo> getPagination1(Map<String, Object> params, Page page);
	
	


}
