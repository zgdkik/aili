package org.activiti.demo.domain.result;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("rawtypes")
public class Dataflow {
	/** 数组 */
	private Map map;

	public Dataflow() {
		map = new HashMap();
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	@SuppressWarnings("unchecked")
	public void putMap(String parm, Object o) {
		map.put(parm, o);
	}

}
