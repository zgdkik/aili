package com.feisuo.sds.user.server.service;

import java.util.List;
import java.util.Map;

import com.feisuo.sds.user.share.entity.PrivilegeEntity;



public interface IPrivilegeService {
	
	public List<PrivilegeEntity> getMenuService(Map<String,Object> map);
	
	public int addMenuService(PrivilegeEntity privilegeEntity);
	
    public int updateMenuService(PrivilegeEntity privilegeEntity);
	
    public int deleteMenuService(String id);
    
    public PrivilegeEntity getMenuByIdService(Map<String,Object> map);

    public int getCountByFunctionCodeService(String functionCode);
}
