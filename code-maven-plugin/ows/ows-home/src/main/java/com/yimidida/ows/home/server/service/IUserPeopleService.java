package com.yimidida.ows.home.server.service;

import java.util.List;
import java.util.Map;

import com.yimidida.ows.base.server.service.IBaseService;
import com.yimidida.ows.home.share.entity.UserPeople;

/**
 * 收寄货人信息地址 接口
 * @author zhangm
 *
 */
public interface IUserPeopleService extends IBaseService<UserPeople, String> {

	/**
	 * 根据类型查询寄货人还是收货人
	 * @param userType
	 * @param userId 
	 * @return
	 */
	List<UserPeople> getByType(Map<String, Object> param);
	
}
