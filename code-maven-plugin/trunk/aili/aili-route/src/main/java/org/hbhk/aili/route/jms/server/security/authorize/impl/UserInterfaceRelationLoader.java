package org.hbhk.aili.route.jms.server.security.authorize.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hbhk.aili.route.jms.server.security.access.JmsUtil;
import org.hbhk.aili.route.jms.server.security.authorize.IUserInterfaceRelationLoader;
import org.hbhk.aili.route.jms.server.security.authorize.UserInterfaceRelation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserInterfaceRelationLoader implements IUserInterfaceRelationLoader{
	public static final Logger LOGGER = LoggerFactory.getLogger(UserInterfaceRelationLoader.class);
	private static ObjectMapper mapper = new ObjectMapper();

	/** 用户接口配置. */
	private static Map<String,UserInterfaceRelation> relationMap;

	@Override
	public Map<String,UserInterfaceRelation>  getAll() {
		return relationMap;
	}

	@Override
	public synchronized void refresh() {
		init();
	}
	
	public synchronized static void init(){
		String source = JmsUtil.getConfig("ESB_USER_INTERFACE_RELATION");
		//如果超时拿不到配置，啥都不做
		if(source ==null){
			return ;
		}
		try {
			List<UserInterfaceRelation> list = mapper.readValue(source, new TypeReference<List<UserInterfaceRelation>>(){});
			relationMap = new HashMap<String,UserInterfaceRelation>();
//			for(UserInterfaceRelation relation:list){
//				//用户名，及服务前端编码连接字符串作为key，
//				relationMap.put(relation.getUser()+relation.getEsbServiceCode(), relation);
//			}
		} catch (JsonParseException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (JsonMappingException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	@Override
	public UserInterfaceRelation getRelation(String user, String esbServiceCode) {
		return relationMap.get(user+esbServiceCode);
	}
}
