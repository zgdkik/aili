package org.hbhk.aili.client.login.service;

import java.util.Set;


/**
 * 登录功能本地实现
 */
public class UserService {

	public static void saveOrUpdateUser(UserEntity user, Set<String> functionCodes) {

//		UserServiceFactory.getUserService().insertOrUpdateUser(user,
//				functionCodes);

	}

	/**
	 * 
	 * 功能：login
	 * 
	 * @param:
	 * @return LoginInfo
	 * @since:1.6
	 */
	public static LoginInfo login(String loginName, String password) {

//		LoginInfo loginInfo = UserServiceFactory.getUserService()
//				.queryUserByName(loginName);
//
//		if (loginInfo != null
//				&& loginInfo.getUser().getPassword().equals(password)) {
//			return loginInfo;
//
//		}

		return null;

	}

}