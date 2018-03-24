package com.yimidida.ows.user.server.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.dao.IBaseDao;
import org.mybatis.spring.support.Page;
import org.mybatis.spring.support.Pagination;

import com.yimidida.ows.user.share.entity.UserEntity;
import com.yimidida.ows.user.share.vo.CurrentUserVo;

public interface IUserDao extends IBaseDao<UserEntity, String> {

	Pagination<UserEntity> queryUserInfoByPage(Page page,
			Map<String, Object> map);


	UserEntity getUserInfoById(Map<String, Object> map);

	List<CurrentUserVo> getCurrentUserList(Map<String, Object> params);




	
	
}
