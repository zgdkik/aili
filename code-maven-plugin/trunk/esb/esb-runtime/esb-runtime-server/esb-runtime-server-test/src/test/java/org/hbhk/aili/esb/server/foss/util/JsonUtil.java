package org.hbhk.aili.esb.server.foss.util;

import java.text.SimpleDateFormat;

import org.codehaus.jackson.map.ObjectMapper;

/**
 * The Class JsonUtil.
 */
public class JsonUtil {
	
	/** The mapper. */
	private static ObjectMapper mapper= new ObjectMapper();
	
	/**
	 * json转换.
	 * 
	 * @param obj
	 *            the obj
	 * @return the j son
	 * @throws Exception
	 *             the exception
	 */
	public static String getJSon(Object obj)throws Exception{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//mapper.getSerializationConfig().withDateFormat(dateFormat);
		mapper.getSerializationConfig().setDateFormat(dateFormat);
		return mapper.writeValueAsString(obj);
	}
}
