package com.feisuo.sds.user.server.dao;

import java.util.List;

import org.mybatis.spring.dao.IBaseDao;

import com.feisuo.sds.user.share.entity.UserRoleEntity;

public interface IUserRoleDao extends IBaseDao<UserRoleEntity,String>{
	
	public int deleteByRoleId(List<String> userids);
	
	public int deleteRoleReByUserName(String userName);
	
	
}
