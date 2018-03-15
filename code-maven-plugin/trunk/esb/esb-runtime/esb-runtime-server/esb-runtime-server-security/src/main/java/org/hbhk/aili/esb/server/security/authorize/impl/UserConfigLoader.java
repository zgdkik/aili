package org.hbhk.aili.esb.server.security.authorize.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.hbhk.aili.esb.server.security.access.JmsUtil;
import org.hbhk.aili.esb.server.security.authorize.IUserConfigLoader;
import org.hbhk.aili.esb.server.security.domain.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 通过JMS加载用户信息
 * 
 * @author k
 */
public class UserConfigLoader implements IUserConfigLoader{

	/** LOGGER. */
	public static final Logger LOGGER = LoggerFactory.getLogger(UserConfigLoader.class);
	
	public static final ObjectMapper  mapper = new ObjectMapper();
	
	/** 用户信息缓存. */
	private static Map<String,UserInfo> esbUserConfig;
	
	/**
	 * 调用此方法可以加载用户信息
	 * @author k
	 */
	public void start() {
		LOGGER.info("UserConfigLoader load user configuration starting!");
	}
	
	/**
	 * 获得所有的用户信息
	 * @author k
	 */
	public Map<String ,UserInfo> getAll() {
		return esbUserConfig;
	}

	/**
	 * 刷新
	 * @author k
	 */
	public synchronized void refresh() {
		init();	
	}
	
	public static synchronized void init() {
		String source = JmsUtil.getConfig("ESBUSER");
		//
		if(source ==null){
			return;
		}
		try {
			List<UserInfo> list = new  ArrayList<UserInfo>();//mapper.readValue(source, new TypeReference<List<UserInfo>>(){});
			esbUserConfig = new HashMap<String,UserInfo>();
			for(UserInfo user:list){
				//用户名作为key
				esbUserConfig.put(user.getUser(), user);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
	@Override
	public UserInfo getUser(String name) {
		return esbUserConfig.get(name);
	}
	
}
