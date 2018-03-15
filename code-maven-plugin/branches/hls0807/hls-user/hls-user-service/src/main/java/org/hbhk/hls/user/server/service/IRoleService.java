package org.hbhk.hls.user.server.service;

import java.util.List;
import java.util.Map;

import org.hbhk.aili.base.share.vo.QueryPageVo;
import org.hbhk.aili.mybatis.server.support.Pagination;
import org.hbhk.hls.user.share.entity.RoleEntity;
import org.hbhk.hls.user.share.entity.RolePrivilegeEntity;

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
