package com.yimidida.ows.user.server.service;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.Pagination;

import com.yimidida.ows.base.share.vo.QueryPageVo;
import com.yimidida.ows.user.share.entity.RoleEntity;
import com.yimidida.ows.user.share.entity.RolePrivilegeEntity;

public interface IRoleService {
	
	public Pagination<RoleEntity> findRoleList(QueryPageVo queryPageVo);

	public int addRole(RoleEntity roleEntity, String funCodes);

	public int updateRole(String roleCode,String[] privilegeCodes);

	// public int deleteRole( String id);

	public RoleEntity getRoleById(String id);

	public List<RoleEntity> getRoleList(Map<String, Object> paraMap );

	public int deleteRoleById(List<String> id);

	List<RolePrivilegeEntity> getRoleFunctionList(String roleCode);

	boolean checkRoleCode(String roleCode);

}
