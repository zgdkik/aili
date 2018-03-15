package com.feisuo.sds.user.server.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.feisuo.sds.base.server.context.UserContext;
import com.feisuo.sds.common.util.UuidUtil;
import com.feisuo.sds.user.server.dao.IUserRoleDao;
import com.feisuo.sds.user.server.service.IUserRoleService;
import com.feisuo.sds.user.share.entity.UserRoleEntity;
@Service
@Transactional
public class UserRoleService implements IUserRoleService {
	
	@Autowired IUserRoleDao roledao;
	
	@Override
	public int addRoleRe(List<String> roleids,String userName) {
		int index = 0;
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
