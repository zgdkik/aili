package com.feisuo.sds.user.server.service;



import java.util.List;

import org.mybatis.spring.support.Pagination;

import com.feisuo.sds.base.server.service.IBaseService;
import com.feisuo.sds.base.share.vo.QueryPageVo;
import com.feisuo.sds.user.share.entity.UserEntity;
import com.feisuo.sds.user.share.vo.CurrentUserVo;

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
	
	//冻结用户
	int frozenByNames(List<String> userNames);
	
	//恢复被冻结用户
	int unFrozenByNames(List<String> userNames);
	
	//禁用用户
	int lockByName(String userName);
	
	//恢复被禁用用户
	int unLockByName(String userName);
	
	int updateUser(UserEntity user);
	
}
