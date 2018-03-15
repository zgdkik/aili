package org.hbhk.aili.esb.server.security.authorize.impl;

import org.hbhk.aili.esb.server.security.authorize.IAuthorize;
import org.hbhk.aili.esb.server.security.authorize.IUserConfigLoader;
import org.hbhk.aili.esb.server.security.authorize.IUserInterfaceRelationLoader;
import org.hbhk.aili.esb.server.security.domain.UserInfo;

public class AuthorizeImpl implements IAuthorize{
	private IUserConfigLoader userConfigLoader;
	private IUserInterfaceRelationLoader userInterfaceRelationLoader;
	
	/**
	 * 验证用户名，密码
	 */
	@Override	public boolean userCheck(String name, String passwd) {
		UserInfo user = userConfigLoader.getUser(name);
		if(user !=null && user.getPasswd().equals(passwd)){
			return true;
		}
		else{
			return false;
		}
	}
	/**
	 * 验证用户与接口服务编码对应关系是否存在
	 */
	@Override
	public boolean interfaceCheck(String name, String esbServiceCode) { 
		if(null != userInterfaceRelationLoader.getRelation(name,esbServiceCode)){
			return true;
		}
		else{
			return false;			
		}
	}
	public IUserConfigLoader getUserConfigLoader() {
		return userConfigLoader;
	}
	public void setUserConfigLoader(IUserConfigLoader userConfigLoader) {
		this.userConfigLoader = userConfigLoader;
	}
	public IUserInterfaceRelationLoader getUserInterfaceRelationLoader() {
		return userInterfaceRelationLoader;
	}
	public void setUserInterfaceRelationLoader(
			IUserInterfaceRelationLoader userInterfaceRelationLoader) {
		this.userInterfaceRelationLoader = userInterfaceRelationLoader;
	}

}
