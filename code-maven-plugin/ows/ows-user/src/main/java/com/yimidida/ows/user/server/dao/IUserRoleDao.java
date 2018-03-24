package com.yimidida.ows.user.server.dao;

import java.util.List;

import org.mybatis.spring.dao.IBaseDao;

import com.yimidida.ows.user.share.entity.UserRoleEntity;

public interface IUserRoleDao extends IBaseDao<UserRoleEntity,String>{
	
	public int deleteByRoleId(List<String> userids);
	
	public int deleteRoleReByUserName(String userName);
	
	
}
