package org.hbhk.aili.route.jms.server.security.authorize;

import java.util.Map;

/**
 * 获取用户接口权限配置信息
 * @author qiancheng
 *
 */
public interface IUserInterfaceRelationLoader {
	
	/**
	 * 
	 * 
	 * @return the all
	 * @author k
	 */
	Map<String,UserInterfaceRelation>  getAll();
	
	/**
	 * 
	 */
	UserInterfaceRelation getRelation(String user,String esbServiceCode);
	/**
	 * 刷新
	 * @author k
	 */
	void refresh();
	
}
