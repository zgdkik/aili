package org.hbhk.aili.client.core.core.context;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 
 *当前用户会话环境，保存了当前用户对象。可以往里面存储一些属性。
 */
public final class SessionContext {
	
	public static final String KEY_USER = "_framework_client_key_user";
	
	public static final String KEY_FUNCTIONCODES = "_framework_client_key_functionCodes";
	
	public static final String KEY_LOGIN_TYPE = "_framework_client_login_type";
	
	public static final String KEY_VERSION="_client_version";
	
	public static final String KEY_TOKEN = "_TOKEN";
	
	public static final String ACCEPT_LANGUAGE= "Accept_Language";
	
	public static final String KEY_USER_SERVER = "FRAMEWORK__KEY_USER__";
	
	private static final Map<String, Object> session = new ConcurrentHashMap<String, Object>();
	
	private SessionContext() {
		
	}
	
	/**
	 * 获取当前版本
	 * getVersion
	 * @return String
	 * @since:0.6
	 */
	static final public String getVersion() {
		return (String) session.get(KEY_VERSION);
		
	}
	
	/**
	 * 设置当前版本
	 * setCurrentUser
	 * @param version
	 * @return void
	 * @since:0.6
	 */
	static final public void setVersion(String version) {
		session.put(KEY_VERSION, version);
	}
	
	
	/**
	 * 获取当前用户
	 * setCurrentUser
	 * @param user
	 * @return void
	 * @since:0.6
	 */
	static final public IUser getCurrentUser() {
		return (IUser) session.get(KEY_USER);
		
	}
	
	/**
	 * 设置当前用户
	 * setCurrentUser
	 * @param user
	 * @return void
	 * @since:0.6
	 */
	static final public void setCurrentUser(IUser user) {
		session.put(KEY_USER, user);
	}
	
	
	
	/**
	 * 
	 * @return
	 */
	static final public Set<String> getCurrentFunctionCodes() {
		return (Set<String>) session.get(KEY_FUNCTIONCODES);
		
	}
	
	/**
	 * 设置当前用户
	 * setCurrentUser
	 * @param user
	 * @return void
	 * @since:0.6
	 */
	static final public void setCurrentFunctionCodes(Set<String> functionCodes) {
		session.put(KEY_FUNCTIONCODES, functionCodes);
	}
	
	
	/**
	 * 存储某个属性值到当前会话上下文中
	 * set
	 * @param key
	 * @param value
	 * @return void
	 * @since:0.6
	 */
	static final public void set(final String key, final Object value) {
		session.put(key, value);
	}
	
	/**
	 * 从会话上下文中获取某个指定key值的属性
	 * get
	 * @param key
	 * @return
	 * @return Object
	 * @since:0.6
	 */
	static final public Object get(final String key) {
		return session.get(key);
	}

	/**
	 * 从会话上下文中删除某个指定key
	 * get
	 * @param key
	 * @return
	 * @return Object
	 * @since:0.6
	 */
	static final public Object remove(final String key) {
		return session.remove(key);
	}
	
}
