package com.feisuo.sds.user.share.vo;

import java.io.Serializable;
import java.util.Set;

import com.feisuo.sds.base.share.entity.IUser;

/**
 * 
 * 用户实体对象
 */
public class CurrentUserVo  implements IUser,Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2139967734709001695L;

	/**
	 * 
	 */

	private String userId;
	
	// 用户登录名
	private String userName;
	
	private String deptCode;
	
	private String userType;
	
	// 用户所拥有的功能信息ID集合
	private Set<String> functionCodes;
	
	// 存入用户uri地址信息
	private Set<String> functionUris;

	// 用户所拥有的角色信息ID集合
	private Set<String> roleCodes;
	
	
	private String userCity;
	
	private String name;
	
	private String status;
	
	public Set<String> getFunctionCodes() {
		return functionCodes;
	}

	public String getUserName() {
		return this.userName;
	}
	
	

	public Set<String> getRoleCodes() {
		return roleCodes;
	}

	public void setRoleCodes(Set<String> roleCodes) {
		this.roleCodes = roleCodes;
	}

	public Set<String> queryAccessUris() {
		return this.functionUris;
	}

	public Set<String> getFunctionUris() {
		return functionUris;
	}

	public void setFunctionUris(Set<String> functionUris) {
		this.functionUris = functionUris;
	}


	public void setFunctionCodes(Set<String> functionCodes) {
		this.functionCodes = functionCodes;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}


	@Override
	public Set<String> accessCodes() {
		return this.functionCodes;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getUserCity() {
		return userCity;
	}


	public void setUserCity(String userCity) {
		this.userCity = userCity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String getId() {
		return null;
	}

	@Override
	public void setId(String id) {
		
	}
	
	
}
