package org.hbhk.aili.rpc.server.hessian.context;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public final class SessionContext {

	public static final String KEY_USER = "_framework_client_key_user";

	public static final String KEY_FUNCTIONCODES = "_framework_client_key_functionCodes";

	public static final String KEY_LOGIN_TYPE = "_framework_client_login_type";

	public static final String KEY_VERSION = "_client_version";

	public static final String KEY_TOKEN = "_TOKEN";

	public static final String ACCEPT_LANGUAGE = "Accept_Language";

	private static final Map<String, Object> session = new ConcurrentHashMap<String, Object>();

	private SessionContext() {

	}

	/**
	 * 鑾峰彇褰撳墠鐗堟湰 getVersion
	 * 
	 * @return String
	 * @since:0.6
	 */
	static final public String getVersion() {
		return (String) session.get(KEY_VERSION);

	}

	/**
	 * 璁剧疆褰撳墠鐗堟湰 setCurrentUser
	 * 
	 * @param version
	 * @return void
	 * @since:0.6
	 */
	static final public void setVersion(String version) {
		session.put(KEY_VERSION, version);
	}

	/**
	 * 鑾峰彇褰撳墠鐢ㄦ埛 setCurrentUser
	 * 
	 * @param user
	 * @return void
	 * @since:0.6
	 */

	/**
	 * 璁剧疆褰撳墠鐢ㄦ埛 setCurrentUser
	 * 
	 * @param user
	 * @return void
	 * @since:0.6
	 */

	/**
	 * 
	 * @return
	 */
	static final public Set<String> getCurrentFunctionCodes() {
		return (Set<String>) session.get(KEY_FUNCTIONCODES);

	}

	/**
	 * 璁剧疆褰撳墠鐢ㄦ埛 setCurrentUser
	 * 
	 * @param user
	 * @return void
	 * @since:0.6
	 */
	static final public void setCurrentFunctionCodes(Set<String> functionCodes) {
		session.put(KEY_FUNCTIONCODES, functionCodes);
	}

	/**
	 * 瀛樺偍鏌愪釜灞炴�鍊煎埌褰撳墠浼氳瘽涓婁笅鏂囦腑 set
	 * 
	 * @param key
	 * @param value
	 * @return void
	 * @since:0.6
	 */
	static final public void set(final String key, final Object value) {
		session.put(key, value);
	}

	/**
	 * 浠庝細璇濅笂涓嬫枃涓幏鍙栨煇涓寚瀹歬ey鍊肩殑灞炴� get
	 * 
	 * @param key
	 * @return
	 * @return Object
	 * @since:0.6
	 */
	static final public Object get(final String key) {
		return session.get(key);
	}

	/**
	 * 浠庝細璇濅笂涓嬫枃涓垹闄ゆ煇涓寚瀹歬ey get
	 * 
	 * @param key
	 * @return
	 * @return Object
	 * @since:0.6
	 */
	static final public Object remove(final String key) {
		return session.remove(key);
	}

}
