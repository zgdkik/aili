package org.hbhk.aili.gen.server.foss;

import java.util.HashMap;
import java.util.Map;


public enum ViewDataType {
	
	textfield(ViewType.textfield),
	datetimefield_date97(ViewType.datetimefield_date97);
	
	public final String VIEW_TYPE;
	private static Map<String,String> typeLookup = new HashMap<String,String>();
	
	static {
		for (ViewDataType type : ViewDataType.values()) {
			typeLookup.put(type.toString().toLowerCase(),type.VIEW_TYPE);
		}
	}

	private ViewDataType(String type) {
		this.VIEW_TYPE = type;
	}

	public static String forType(String veiwType)  {
		veiwType = veiwType.toLowerCase();
		String extType = typeLookup.get(veiwType);
		if(extType == null  || "".equals(extType)){
			extType = ViewType.textfield;
		}
		return extType;
	}

}
