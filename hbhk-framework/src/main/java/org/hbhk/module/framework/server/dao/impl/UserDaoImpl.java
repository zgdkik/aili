package org.hbhk.module.framework.server.dao.impl;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.hbhk.module.framework.server.dao.IUserDao;
import org.hbhk.module.framework.shared.domain.security.ResourcesEntity;
import org.hbhk.module.framework.shared.domain.security.RolesEntity;
import org.hbhk.module.framework.shared.domain.security.UsersEntity;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class UserDaoImpl extends SqlSessionDaoSupport implements IUserDao {

	public void userTest() {

		System.out.println("user test:" + getSqlSession());

	}

	@Override
	public UsersEntity queryUsers(String username) {
		// 数据库拿用户权限 TODO
		UsersEntity usersEntity = new UsersEntity();
		usersEntity.setUsername("hbhk");
		usersEntity.setPassword("135246");
		usersEntity.setId(1);
		usersEntity.setEnable(1);
		Set<RolesEntity> roles = new HashSet<RolesEntity>();
		
		RolesEntity rol = new RolesEntity();
		rol.setEnable(1);
		rol.setId(1);
		rol.setName("ROLE_USER");
		Set<ResourcesEntity> resources = new HashSet<ResourcesEntity>();
		// 权限1
		ResourcesEntity res = new ResourcesEntity();
		// res.setUrl("/user/welcome");
		res.setId(1);
		res.setName("欢迎界面");
		res.setUrl("/framework/welcome");
		// 权限2
		ResourcesEntity res1 = new ResourcesEntity();
		// res1.setUrl("/user/empList");
		res1.setId(2);
		res1.setName("文件上传");
		res1.setUrl("/framework/uploadFile");
		// 权限3
		ResourcesEntity res5 = new ResourcesEntity();
		// res1.setUrl("/user/empList");
		res5.setId(2);
		res5.setName("文件上传界面");
		res5.setUrl("/framework/fileupload");

		resources.add(res);
		resources.add(res1);
		resources.add(res5);
		rol.setResources(resources);

		roles.add(rol);

		RolesEntity rol1 = new RolesEntity();
		rol1.setEnable(1);
		rol1.setId(1);
		rol1.setName("NONE");
		Set<ResourcesEntity> resources1 = new HashSet<ResourcesEntity>();
		ResourcesEntity res2 = new ResourcesEntity();
		res2.setId(3);
		res2.setName("登录界面");
		res2.setUrl("/framework/login");
		ResourcesEntity res3 = new ResourcesEntity();
		res3.setId(4);
		res3.setName("验证码");
		res3.setUrl("/framework/validateCode");
		ResourcesEntity res4 = new ResourcesEntity();
		res4.setId(5);
		res4.setName("登录请求url");
		res4.setUrl("/framework/userLogin");
		resources1.add(res2);
		resources1.add(res3);
		resources1.add(res4);
		rol1.setResources(resources1);
		roles.add(rol1);
		
		// 给用户添加角色
		usersEntity.setRoles(roles);

		return usersEntity;
	}

	public static void main(String[] args) {
		UserDaoImpl uu = new UserDaoImpl();
		UsersEntity users = uu.queryUsers("");
		Set<RolesEntity> roles = users.getRoles();
		for (Iterator<RolesEntity> iterator = roles.iterator(); iterator
				.hasNext();) {
			RolesEntity rolesEntity = (RolesEntity) iterator.next();
			
			Set<ResourcesEntity> resources = rolesEntity.getResources();
			System.out.println(resources.size());
			for (Iterator<ResourcesEntity> iterator2 = resources.iterator(); iterator2
					.hasNext();) {
				ResourcesEntity resourcesEntity = (ResourcesEntity) iterator2
						.next();
				String surl = resourcesEntity.getUrl();
				System.out.println(surl);
			}
		}
	}
}
