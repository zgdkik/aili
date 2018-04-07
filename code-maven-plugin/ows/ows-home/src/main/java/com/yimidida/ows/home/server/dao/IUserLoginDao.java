package com.yimidida.ows.home.server.dao;

import org.mybatis.spring.dao.IBaseDao;

import com.yimidida.ows.home.share.entity.UserLogin;


/**
 * 收寄货人信息地址-DAO层
 * @author zhangm
 *
 */
public interface IUserLoginDao  extends IBaseDao<UserLogin, String> {

	/**
	 * 根据类型查询寄货人还是收货人
	 * @param userType
	 * @param userId 
	 * @return 
	 */
	
}