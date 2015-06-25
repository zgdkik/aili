package org.hbhk.aili.core.share.consts;

/**
 * 框架常量类
 */
public interface AppConst {
	/**
	 * spring加载bean时遇到id重复 通过该标识判断 是覆盖 还是抛出异常 true 允许覆盖 false 抛出异常
	 */
	String ALLOW_BEAN_DEFINITION_OVERRIDING = "override";

	String AILI_SEESION_KEY = "AILI_SEESION_KEY";

	String AILI_CLUSTER_TOKEN = "AILI_CLUSTER_TOKEN";

}
