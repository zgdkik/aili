package org.hbhk.aili.esb.server.security.authorize;

public interface IAuthorize {
	/**
	 * 用户、密码检查
	 * @param name
	 * @param passwd
	 * @return
	 */
	public boolean userCheck(String name,String passwd);
	
	/**
	 * 用户接口授权检查（检查用户是否授权访问接口）
	 * @param name
	 * @param esbServiceCode
	 * @return
	 */
	public boolean interfaceCheck(String name,String esbServiceCode);
}
