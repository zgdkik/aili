package org.hbhk.hls.user.share.vo;


import java.util.List;

import org.hbhk.aili.base.share.entity.BizBaseEntity;
import org.hbhk.hls.user.share.entity.PrivilegeEntity;


public class PrivilegeResultVo extends BizBaseEntity {
	
	private static final long serialVersionUID = 8204215052602820708L;

	private PrivilegeEntity privilegeEntity;
	
	private String flag;
	
	private List<PrivilegeEntity> functionEntityList;

	public PrivilegeEntity getFunctionEntity() {
		return privilegeEntity;
	}

	public void setFunctionEntity(PrivilegeEntity privilegeEntity) {
		this.privilegeEntity = privilegeEntity;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public List<PrivilegeEntity> getFunctionEntityList() {
		return functionEntityList;
	}

	public void setFunctionEntityList(List<PrivilegeEntity> functionEntityList) {
		this.functionEntityList = functionEntityList;
	}
	
	
	

}
