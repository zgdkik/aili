package com.yimidida.ows.user.server.service;

import java.util.List;
import java.util.Map;

import com.yimidida.ows.user.share.entity.PrivilegeEntity;



public interface IPrivilegeService {
	
	public List<PrivilegeEntity> getMenuService(Map<String,Object> map);
	
	public int addMenuService(PrivilegeEntity privilegeEntity);
	
    public int updateMenuService(PrivilegeEntity privilegeEntity);
	
    public int deleteMenuService(String id);
    
    public PrivilegeEntity getMenu(Map<String,Object> map);

    public int getCountByFunctionCodeService(String functionCode);
}
