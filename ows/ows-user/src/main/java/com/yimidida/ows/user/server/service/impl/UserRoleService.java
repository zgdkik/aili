package com.yimidida.ows.user.server.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yimidida.ows.base.server.context.UserContext;
import com.yimidida.ows.common.util.UuidUtil;
import com.yimidida.ows.user.server.cache.InvalidateAuthCache;
import com.yimidida.ows.user.server.dao.IUserRoleDao;
import com.yimidida.ows.user.server.service.IUserRoleService;
import com.yimidida.ows.user.share.entity.UserRoleEntity;
@Service
@Transactional
public class UserRoleService implements IUserRoleService {
	
	@Autowired IUserRoleDao roledao;
	
	@Override
	public int addRoleRe(List<String> roleids,String userName) {
		int index = 0;
		//先删除
		deleteRoleReByUserName(userName);
		for(String roleid : roleids){
			UserRoleEntity e = new UserRoleEntity();
			e.setId(UuidUtil.getUuid());
			e.setRoleCode(roleid);
			e.setUserCode(userName);
			e.setCreateTime(new Date());
			e.setCreateUser(UserContext.getCurrentUser().getUserName());
			int count = roledao.insert(e);
			index+=count;
		}
		//清除缓存
		InvalidateAuthCache.invalidateUserCache(userName);
		return index;
	}
	
	public List<String> getRoleIdByUserName(String userName){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userCode", userName);
		List<UserRoleEntity> rolelist = roledao.get(map);
		List<String> list = new ArrayList<String>();
		for(UserRoleEntity e: rolelist){
			list.add(e.getRoleCode());
		}
		return list;
	}
	
	@Override
	public int deleteRoleRe(List<String> roleids) {
		int count = roledao.deleteByRoleId(roleids);
		return count;
	}

	@Override
	public int deleteRoleReByUserName(String userName) {
		int count = roledao.deleteRoleReByUserName(userName);
		return count;
	}

}
