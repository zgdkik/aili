package com.yimidida.ows.user.share.entity;

import org.hibernate.validator.constraints.NotEmpty;
import org.mybatis.spring.annotation.Column;
import org.mybatis.spring.annotation.Table;

import com.yimidida.ows.base.share.entity.BizBaseEntity;

/**
 * 角色对象实体
 */
@Table(value="t_auth_role")
public class RoleEntity extends BizBaseEntity{
	
	private static final long serialVersionUID = -3091688011204362825L;

	//角色编码
	@Column("role_code")
	@NotEmpty(message="角色编码不能为空")
	private String roleCode;
	
	//角色名称
	@Column("role_name")
	@NotEmpty(message="角色名称不能为空")
	private String roleName;

	//角色描述
	@Column("role_desc")
	private String roleDesc;
	
	//企业编码
	@Column("comp_code")
	@NotEmpty(message="角色公司不能为空")
	private String compCode;
	
	//部门编码
	@Column("dept_code")
	private String deptCode;
	//角色类型
	@Column("role_type")
	private String roleType;
		
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


	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public String getCompCode() {
		return compCode;
	}

	public void setCompCode(String compCode) {
		this.compCode = compCode;
	}
	
	
	
}
