package org.hbhk.aili.client.core.core.security;

/**
 * 
 *权限控制代表接口。实现check方法返回是否有权。
 */
public interface IAuthorizationDelegate {
	/**
	 * 
	 * <p>Title: check</p>
	 * <p>Description: 权限检查</p>
	 * @param permission 权限
	 * @return
	 */
	boolean check(Object permission);
}
