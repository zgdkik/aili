package com.feisuo.sds.user.server.service;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.Pagination;

import com.feisuo.sds.base.share.vo.QueryPageVo;
import com.feisuo.sds.user.share.entity.RoleEntity;
import com.feisuo.sds.user.share.entity.RolePrivilegeEntity;

public interface IRoleService {
	
	public Pagination<RoleEntity> findRoleList(QueryPageVo queryPageVo);

	public int addRole(RoleEntity roleEntity, String funCodes);

	public int updateRole(RoleEntity roleEntity);

	// public int deleteRole( String id);

	public RoleEntity getRoleById(String id);

	public List<RoleEntity> getRoleList(Map<String, Object> paraMap );

	public int deleteRoleById(List<String> id);

	List<RolePrivilegeEntity> getRoleFunctionList(String roleCode);

	boolean checkRoleCode(String roleCode);

}
