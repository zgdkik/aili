package com.yimidida.ows.home.server.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.dao.IBaseDao;

import com.yimidida.ows.home.share.entity.UserPeople;


/**
 * 收寄货人信息地址-DAO层
 * @author zhangm
 *
 */
public interface IUserPeopleDao  extends IBaseDao<UserPeople, String> {

	/**
	 * 根据类型查询寄货人还是收货人
	 * @param userType
	 * @param userId 
	 * @return 
	 */
	List<UserPeople> getByType(Map<String, Object> param);
}
