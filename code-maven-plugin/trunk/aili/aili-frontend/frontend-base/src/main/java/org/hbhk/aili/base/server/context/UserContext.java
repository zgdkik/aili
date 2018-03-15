package org.hbhk.aili.base.server.context;

import org.hbhk.aili.base.shared.domain.IUser;

/**
 * 系统用户信息获得的上下文管理
 * 用户信息的ID存放于应用服务器的Session中
 * 通过Session的ID通过缓存获取用户
 * 缓存中没有指定用户信息时，会自动通过Provider去获取信息
 * 用户在缓存中存在的时候受DataReloader决定
 *
 */
public final class UserContext {
    
	private static final ThreadLocal<IUser> USER_STORE = new ThreadLocal<IUser>();
	
    
    private UserContext() {
		super();
	}

	/**
     * 获取当前会话的用户
     * 如果没有用户信息返回值为NULL
     * @return
     */
	public static IUser getCurrentUser() {
        return USER_STORE.get();
    }
    
    /**
    */
    
    public static void setCurrentUser(IUser user){
    	USER_STORE.set(user);
    }
    
    
    
    /**
     * 清除ThreadLocal中的数据
     */
    public static void remove(){
    	USER_STORE.set(null);
    }
}
