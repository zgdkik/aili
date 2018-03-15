package org.hbhk.aili.route.jms.server.common.filter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class BlackListContainer {
	private static final Log LOG = LogFactory.getLog(BlackListContainer.class);
	private static Map<String,String> blackList = new HashMap<String,String>();
	
	public synchronized boolean add(String code,String name){
		blackList.put(code,name);
		LOG.info("add service to blacklist,[code:"+code+";name:"+name+"]");
		return true;
	}
	
	public boolean exists(String code){
		return blackList.containsKey(code);
	}
	
	public synchronized boolean remove(String code){
		String name = blackList.remove(code);
		LOG.info("remove service to blacklist,[code:"+code+";name:"+name+"]");
		return true;
	}
	public synchronized boolean clean(){
		blackList.clear();
		LOG.info("clear the blackList");
		return true;
	}
	
	public synchronized String printBlackList (){
		Set<String> collection = blackList.keySet();
		Iterator<String> iterator = collection.iterator();
		StringBuffer buffer = new StringBuffer();
		while (iterator.hasNext()) {
			String key = iterator.next();
			buffer.append("code:"+key+";name:"+blackList.get(key)+";");
			
		}
		return buffer.toString();
	}
}
