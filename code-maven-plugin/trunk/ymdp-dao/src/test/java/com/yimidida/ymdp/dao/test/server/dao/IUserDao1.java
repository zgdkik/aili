package com.yimidida.ymdp.dao.test.server.dao;

import java.util.Map;

import com.yimidida.ymdp.dao.server.IBaseDao;
import com.yimidida.ymdp.dao.server.support.Page;
import com.yimidida.ymdp.dao.server.support.Pagination;
import com.yimidida.ymdp.dao.test.server.entity.UserInfo;


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
