package com.yimidida.ows.user.server.service;



import java.util.List;

import org.mybatis.spring.support.Pagination;

import com.yimidida.ows.base.server.service.IBaseService;
import com.yimidida.ows.base.share.vo.QueryPageVo;
import com.yimidida.ows.user.share.entity.UserEntity;
import com.yimidida.ows.user.share.vo.CurrentUserVo;

public interface IUserService extends IBaseService<UserEntity, String>{
	
	//分页查询用户所有信息
	Pagination<UserEntity> getPage(QueryPageVo queryPageVo);
	
	//根据用户ID查询用户信息
	UserEntity queryUserInfoById(String id);
	
	List<CurrentUserVo>  getCurrentUserVos(String userName);
	
	//校验用户名唯一性
	boolean checkUserName(String userName);
	
	//根据用户名获取用户校验密码
	List<UserEntity> getUserByUserName(String userName); 
	
	int updateUser(UserEntity user);
	
}
