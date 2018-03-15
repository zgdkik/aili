package com.feisuo.sds.user.share.entity;

import java.util.Collection;

import org.mybatis.spring.annotation.Column;
import org.mybatis.spring.annotation.Table;

import com.feisuo.sds.base.share.entity.BizBaseEntity;

/**
 * 角色对象实体
 * @author fanhoutao
 */
@Table(value="t_auth_role",dynamicUpdate=true)
public class RoleEntity extends BizBaseEntity{
	
	private static final long serialVersionUID = -3091688011204362825L;

	//角色编码
	@Column("role_code")
	private String roleCode;
	
	//角色名称
	@Column("role_name")
	private String roleName;

	//角色描述
	@Column("role_desc")
	private String roleDesc;
	
	//部门编码
	@Column("dept_code")
	private String deptCode;
	//角色类型
	@Column("role_type")
	private String roleType;
		
	//新增的FunctionCode
    private String[] resourceCodes;
    
	//删除的FunctionCode
    private String[] deleteResourceCodes;
	
	//功能对象ID
	private Collection<String> functionIds;
	
	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}


	public String getRoleName() {
		return this.roleName;
	}
		 	
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
		
	public String getRoleDesc() {
		return this.roleDesc;
	}
		 	
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
	public String getDeptCode(String deptCode) {
		return this.deptCode;
	}
		 	
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public Collection<String> getFunctionIds() {
		return this.functionIds;
	}
	
	public void setFunctionIds(Collection<String> functionIds){
		this.functionIds = functionIds;
	}
	
	public String[] getResourceCodes() {
			return resourceCodes;
	}

	public void setResourceCodes(String[] resourceCodes) {
			this.resourceCodes = resourceCodes;
	}

	public String[] getDeleteResourceCodes() {
			return deleteResourceCodes;
	}

	public void setDeleteResourceCodes(String[] deleteResourceCodes) {
			this.deleteResourceCodes = deleteResourceCodes;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public String getDeptCode() {
		return deptCode;
	}
	
}
