package com.feisuo.sds.base.server.context;

import com.feisuo.sds.base.share.entity.IUser;

public final class UserContext {

	private static final ThreadLocal<IUser> USER_STORE = new ThreadLocal<IUser>();

	private UserContext() {
		super();
	}

	/**
	 * 获取当前会话的用户 如果没有用户信息返回值为NULL
	 * 
	 * @return
	 */
	public static IUser getCurrentUser() {
		return USER_STORE.get();
	}

	/**
    */

	public static void setCurrentUser(IUser user) {
		USER_STORE.set(user);
	}

	/**
	 * 清除ThreadLocal中的数据
	 */
	public static void remove() {
		USER_STORE.set(null);
	}
}
