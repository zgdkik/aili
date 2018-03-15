package org.hbhk.aili.route.jms.server.security.flowcontrol.container.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hbhk.aili.route.jms.server.security.access.JmsUtil;
import org.hbhk.aili.route.jms.server.security.flowcontrol.container.ICountContainer;
import org.hbhk.aili.route.jms.server.security.flowcontrol.counter.ICounter;
import org.hbhk.aili.route.jms.server.security.flowcontrol.counter.impl.EsbDefaultCounter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 计数器容器，适用于单实例的情况，多实例的情况下，请使用集中式的缓存服务器做计数容器,eg:redis、memcached
 * 
 * @author qiancheng
 * 
 */
public class EsbInterfaceCountContainer implements ICountContainer {
	private static Logger LOG = LoggerFactory.getLogger(EsbInterfaceCountContainer.class);
	private static ObjectMapper mapper = new ObjectMapper();
	//存放
	private Map<String, ICounter>  interfaceCountMap  ;
	private String requestCode ="ESB_INTERFACE_COUNT";
	public synchronized void init (){
		//1、加载配置；
		LOG.info("EsbInterfaceCountContainer start init ");
		interfaceCountMap = new HashMap<String, ICounter>();
		String source = JmsUtil.getConfig(requestCode);
		if(source == null){
			LOG.warn("=======>没有获取到接口计数限制配置信息，不能初始化接口计数器啦  !!!!!! ");
			return;
		}
//		List<InterfaceCount> list =  json2Java(source);
//		//2、初始化计数器
//		for(InterfaceCount info :list){
//			interfaceCountMap.put(info.getEsbServiceCode(), new EsbDefaultCounter(info.getEsbServiceCode(),info.getMaxCount()));
//			//LOG.info("key:"+info.getEsbServiceCode());
//		}
		LOG.info("EsbInterfaceCountContainer successfully init ");
	}
	
	@Override
	public List<ICounter> traversal() {
	
		Collection<ICounter> collection = interfaceCountMap.values();
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
	
		return interfaceCountMap.get(key);
	}

	@Override
	public int add(String key, ICounter counter) {
	
		int count = 0;
		if (interfaceCountMap.get(key) == null && counter != null) {
			interfaceCountMap.put(key, counter);
			count++;
		}
		return count;
	}

	@Override
	public int delete(String key) {
	
		ICounter counter = interfaceCountMap.remove(key);
		if (counter != null) {
			return 1;
		}
		return 0;
	}

	@Override
	public void clean() {
	
		interfaceCountMap.clear();
	}

	/**
	 * 
	 */
	@Override
	public void resetAllCounter() {
		Collection<ICounter> collection = interfaceCountMap.values();
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
		return interfaceCountMap.containsKey(key);
	}

	public int size() {
		return interfaceCountMap.size();
	}	
	
	protected List<InterfaceCount> json2Java(String source){
		try {
			List<InterfaceCount> list = mapper.readValue(source, new TypeReference<List<InterfaceCount>>(){});
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
