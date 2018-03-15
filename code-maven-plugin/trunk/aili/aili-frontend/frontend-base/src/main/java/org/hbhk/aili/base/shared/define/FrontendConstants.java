package org.hbhk.aili.base.shared.define;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.hbhk.aili.core.share.consts.AppConst;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

public class FrontendConstants {
	
	/**
	 * 数据初始版本，用于需要版本控制的实体；
	 */
	public static final short INIT_VERSION_NO = 1;

	/**
	 * 布尔类型
	 */
	public static final String YES = "Y";
	public static final String NO = "N";

	/** 生效 */
	public static final String ACTIVE = "Y";
	/** 未生效 */
	public static final String INACTIVE = "N";
	/** 生效的最大时间 */
	public static final Date ACTIVE_TIME = new GregorianCalendar(3000, Calendar.DECEMBER, 31).getTime();
	
	public static ResourcePatternResolver RESOURCE_RESOLVER = new PathMatchingResourcePatternResolver();

	public static final String ISLEAF = "Y";
	public static final String NOLEAF = "N";
	/**
	 * 语言类型
	 * 国际化功能中语言切换
	 * @author 曾宪涛
	 */
	public static final String CN_S = "zh_CN";
	public static final String CN_T = "zh_TW";
	public static final String EN = "en";
	
	/**
	 * 成功/失败
	 * 对于过程性的方法，返回整型来代表成功或者失败；
	 */
	public static final int SUCCESS = 1;
	public static final int FAILURE = -1;
	
	/**
	 * ORACLE最大IN数量
	 */
	public static final int ORACLE_MAX_IN_SIZE = 1000;

	/**
	 * 集团ORG CODE
	 */
	public static final String ROOT_ORG_CODE= "DIP";

	/**
	 * 总公司标杆编码
	 */
	public static final String ROOT_ORG_UNIFIED_CODE= "DP00002";
	
	/**
	 * 总公司名称
	 */
	public static final String ROOT_ORG_NAME= "总公司";
	
	/***************mybatis前缀**************************/
	/**
	 * DPAP公共登录模块mybatis前缀
	 */
	public static final String MYBATIS_NAMESPACE_LOGIN_PREFIX = "dpap.login";
	
	/**
	 * DPAP权限模块mybatis前缀
	 */
	public static final String MYBATIS_NAMESPACE_AUTH_PREFIX = "dpap.auth";	
	
	/**
	 * DPAP组织架构模块mybatis前缀
	 */
	public static final String MYBATIS_NAMESPACE_ORG_PREFIX = "dpap.org";	
	
	/**
	 * DPAP数据字典模块mybatis前缀
	 */
	public static final String MYBATIS_NAMESPACE_DICT_PREFIX = "dpap.dict.";	
	/*****************************************/
	
	/* *******************缓存UUID****************************** */
	/** 功能实体缓存前缀 */
	public static final String FUNCTION_ENTITY_CACHE_UUID = "FUNCTION_";
	/** 菜单缓存前缀 */
	public static final String MENU_CACHE_UUID = "MENU";
	
	public static final String FUNCTION_ALL_CACHE_UUID = "MENU_ALL";
	/** 用户缓存前缀 */
	public static final String USER_CACHE_UUID = "USER_";
	/**部门子部门集合缓存前缀*/
	public static final String DEPARTMENT_DIRECT_CHILD_CACHE_UUID = "DEPARTMENT_DIRECT_CHILD";
	/**部门缓存前缀*/
	public static final String DEPARTMENT_CACHE_UUID = "DEPARTMENT";
	/** 系统参数缓存前缀 */
	public static final String SYSCONFIG_CACHE_UUID = "SYSCONFIG";
	/** 数据字典实体缓存前缀*/
	public static final String DICT_CACHE_UUID = "DICT_ENTITY";
	/** 数据字典缓存前缀*/
	public static final String DICT_DATA_CACHE_UUID = "DICT_DATA";
	
	public static final String USER_DEPT_AUTH="USER_DEPT_AUTH";
	
	public static final String DEFAULT_TITLE="HBHK";
	
	public static final String DEFAULT_MAIN_LOGO_IMAGE_PATH = "classpath:org/hbhk/aili/config/server/META-INF/images/maintoplogo.png";
	
	public static final String DEFAULT_LOGIN_LOGO_IMAGE_PATH = "classpath:org/hbhk/aili/config/server/META-INF/images/logintoplog.png";
	
	public static final String DEFAULT_LOGIN_BACKGROUND_IMAGE_PATH = "classpath:org/hbhk/aili/config/server/META-INF/images/backgroundimage.png";
	
	public static final String DEFAULT_CSS_SOURCE = "classpath:org/hbhk/aili/config/server/META-INF/styles/login.css";
	
	public static final String LOGIN_CSS_TEMPLATE = "logintemplate.css";
	
	public static final String LOGIN_CSS_STRING_CACHE = "loginCssStringCache";

	public static final String Main_CONFIG_STRING_CACHE = "mainConfigStringCache";
	
	public static final String SSO_COOKIE_KEY = "CAS_TGC";

	public static final String CAS_SSO_TOKEN_KEY = "ticket";
	
	public static final String USER_SESSION_KEY = AppConst.AILI_SEESION_KEY;
}
