package org.hbhk.module.framework.server.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hbhk.module.framework.shared.domain.security.ResourcesEntity;
import org.hbhk.module.framework.shared.domain.security.RolesEntity;
import org.hbhk.module.framework.shared.domain.security.UsersEntity;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MyUserDetailService implements UserDetailsService {

	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		Collection<GrantedAuthority> auths = new HashSet<GrantedAuthority>();

	//	GrantedAuthority auth = new SimpleGrantedAuthority("ROLE_ADMIN");
		// auths.add(auth2);
		//数据库拿用户权限  TODO
		UsersEntity   usereEntity = new UsersEntity();
		usereEntity.setUsername("hbhk");
		usereEntity.setPassword("135246");
		usereEntity.setId(1);
		usereEntity.setEnable(1);
		Set<RolesEntity>  roles = new HashSet<RolesEntity>();
		RolesEntity  rol = new RolesEntity();
		rol.setEnable(1);
		rol.setId(1);
		rol.setName("ROLE_USER");
		ResourcesEntity res=  new ResourcesEntity();
		//res.setUrl("/user/welcome");
		res.setName("ROLE_USER");
		Set<ResourcesEntity> resources = new HashSet<ResourcesEntity>();
		RolesEntity  rol1 = new RolesEntity();
		rol1.setEnable(1);
		rol1.setId(1);
		rol1.setName("ROLE_NO");
		ResourcesEntity res1=  new ResourcesEntity();
		//res1.setUrl("/user/empList");
		res1.setName("ROLE_NO");
		resources.add(res);
		resources.add(res1);
		rol.setResources(resources);
		resources.add(res1);
		rol1.setResources(resources);
		
		
		roles.add(rol1);
		roles.add(rol);
		usereEntity.setRoles(roles);
		
		
//		if (username.equals("hbhk")) {
//			auths = new ArrayList<GrantedAuthority>();
//			GrantedAuthority auth1 = new SimpleGrantedAuthority("ROLE_USER");
//			GrantedAuthority auth2 = new SimpleGrantedAuthority("ROLE_NO");
//			//auths.add(auth);
//			auths.add(auth1);
//			auths.add(auth2);
//		}
		
		auths = obtionGrantedAuthorities(usereEntity);

		// User(String username, String password, boolean enabled,
		// boolean accountNonExpired,
		// boolean credentialsNonExpired, boolean accountNonLocked,
		// Collection<GrantedAuthority> authorities) {
		User user = new User(username, usereEntity.getPassword(), true, true, true, true, auths);
		return user;
	}

	// 取得用户的权限
	private Set<GrantedAuthority> obtionGrantedAuthorities(UsersEntity user) {

		List<ResourcesEntity> resources = new ArrayList<ResourcesEntity>();
		Set<RolesEntity> roles = user.getRoles();
		for (RolesEntity role : roles) {
			Set<ResourcesEntity> tempRes = role.getResources();
			for (ResourcesEntity res : tempRes) {
				resources.add(res);
			}
		}

		Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();
		for (ResourcesEntity res : resources) {
			// 用户可以访问的资源名称（或者说用户所拥有的权限） 注意：必须"ROLE_"开头
			// 关联代码：applicationContext-security.xml
			// 关联代码：com.huaxin.security.MySecurityMetadataSource#loadResourceDefine
			authSet.add(new SimpleGrantedAuthority(res.getName()));
		}

		return authSet;
	}

}
