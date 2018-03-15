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
import org.hbhk.aili.esb.server.security.domain.InterfaceCount;
import org.hbhk.aili.esb.server.security.flowcontrol.container.ICountContainer;
import org.hbhk.aili.esb.server.security.flowcontrol.counter.ICounter;
import org.hbhk.aili.esb.server.security.flowcontrol.counter.impl.EsbDefaultCounter;


public class EsbInterfaceConcurrentContainer implements ICountContainer{
	private static Logger LOG = Logger.getLogger(EsbInterfaceCountContainer.class);
	private static ObjectMapper mapper = new ObjectMapper();
	//存放
	private Map<String, ICounter>  interfaceCountMap  ;
	private String requestCode ="ESB_INTERFACE_COUNT";
	public synchronized void init (){
		interfaceCountMap = new HashMap<String, ICounter>();
		//1、加载配置；
		LOG.info("EsbInterfaceCountContainer start init ");
		String source = JmsUtil.getConfig(requestCode);
		if(source == null){
			LOG.warn("=======>没有获取到接口并发计数限制配置信息 ，不能初始化并发计数器啦");
			return;
		}
		List<InterfaceCount> list = new  ArrayList<InterfaceCount>();//  json2Java(source);
		//2、初始化计数器
		for(InterfaceCount info :list){
			interfaceCountMap.put(info.getEsbServiceCode(), new EsbDefaultCounter(info.getEsbServiceCode(),info.getConcurrent()));
		}
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
