package org.hbhk.aili.esb.server.security.flowcontrol.container.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.hbhk.aili.esb.server.security.access.JmsUtil;
import org.hbhk.aili.esb.server.security.domain.UserInterfaceCount;
import org.hbhk.aili.esb.server.security.flowcontrol.container.ICountContainer;
import org.hbhk.aili.esb.server.security.flowcontrol.counter.ICounter;
import org.hbhk.aili.esb.server.security.flowcontrol.counter.impl.EsbDefaultCounter;

public class EsbUserInterfaceCountContainer implements ICountContainer{
	private static Logger LOG = Logger.getLogger(EsbUserInterfaceCountContainer.class);
	private static ObjectMapper mapper = new ObjectMapper();
	/*
	 * key： user+esbServiceCode,eg:
	 * 	假设有用戶FOSS_USER01 和服务编码ESB_CODE01，
	 *  key的值  FOSS_USER01ESB_CODE01
	 */
	private Map<String, ICounter>  userInterfaceCountMap  ;
	private String requestCode ="ESB_USER_INTERFACE_COUNT";
	public synchronized void init (){
		//1、加载配置；
		userInterfaceCountMap = new HashMap<String, ICounter>();
		LOG.info("EsbUserInterfaceCountContainer start init ");
		String source = JmsUtil.getConfig(requestCode);
		if(source == null){
			LOG.warn("=======>没有拿到接口并发计数限制配置信息，不能初始化相应计数器  !!!!!! ");
			return;
		}
		List<UserInterfaceCount> list =  json2Java(source);
		StringBuffer buffer = new StringBuffer();
		buffer.append("the EsbUserInterfaceCountContainer init interface contain:");
		//2、初始化计数器

		for(UserInterfaceCount info :list){
			userInterfaceCountMap.put(info.getUser()+info.getEsbServiceCode(), new EsbDefaultCounter(info.getUser()+info.getEsbServiceCode(),info.getMaxCount()));
			//LOG.info("key:"+info.getEsbServiceCode());
		}
		LOG.info(buffer.toString());
		LOG.info("EsbUserInterfaceCountContainer successfully init ");
	}
	
	@Override
	public List<ICounter> traversal() {
		Collection<ICounter> collection = userInterfaceCountMap.values();
		Iterator<ICounter> iterator = collection.iterator();
		List<ICounter> list = new ArrayList<ICounter>();
		while (iterator.hasNext()) {
			ICounter counter = iterator.next();
			list.add(counter);
		}
		return list;
	}

	@Override
	public ICounter get(String key) {
		return userInterfaceCountMap.get(key);
	}

	@Override
	public int add(String key, ICounter counter) {
		int count = 0;
		if (userInterfaceCountMap.get(key) == null && counter != null) {
			userInterfaceCountMap.put(key, counter);
			count++;
		}
		return count;
	}

	@Override
	public int delete(String key) {
		ICounter counter = userInterfaceCountMap.remove(key);
		if (counter != null) {
			return 1;
		}
		return 0;
	}

	@Override
	public void clean() {
		userInterfaceCountMap.clear();
	}

	/**
	 * 
	 */
	@Override
	public void resetAllCounter() {
		Collection<ICounter> collection = userInterfaceCountMap.values();
		if (!collection.isEmpty()) {
			Iterator<ICounter> iterator = collection.iterator();
			while (iterator.hasNext()) {
				ICounter counter = iterator.next();
				counter.reset();
			}
		}
	}

	@Override
	public boolean exists(String key) {
		return userInterfaceCountMap.containsKey(key);
	}

	public int size() {
		return userInterfaceCountMap.size();
	}	
	
	protected List<UserInterfaceCount> json2Java(String source){
		try {
			List<UserInterfaceCount> list = mapper.readValue(source, new TypeReference<List<UserInterfaceCount>>(){});
			return list;
		} catch (JsonParseException e) {
			LOG.error(e.getMessage(), e);
		} catch (JsonMappingException e) {
			LOG.error(e.getMessage(), e);
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
		}
		return null;
	}
}
