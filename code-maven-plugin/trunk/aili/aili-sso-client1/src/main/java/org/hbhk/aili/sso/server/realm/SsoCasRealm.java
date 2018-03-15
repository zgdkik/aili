package org.hbhk.aili.sso.server.realm;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class SsoCasRealm extends CasRealm {

	// 获取授权信息
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		// ModularRealmAuthenticator
		// CentralAuthenticationServiceImpl
		// DefaultFilter
		System.out.println("===============================已经授权了添加授权信息");
		// ... 与前面 MyShiroRealm 相同
		Set<String> roleNames = new HashSet<String>();
		Set<String> permissions = new HashSet<String>();
		roleNames.add("admin");
		permissions.add("index.jsp");
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roleNames);
		info.setStringPermissions(permissions);
		return info;
	}
	

}