package org.hbhk.aili.client.login.util;

import java.io.InputStream;
import java.io.OutputStream;

import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * 基于json工具对象到json字符串之间的互相转换
 */
public class BeanToJsonUtil {
	/**
	 * 
	 * 对象转换为JSON
	 */
	public static void beanToJson(OutputStream out, Object obj)
			throws Exception {

		ObjectMapper mapper = new ObjectMapper();
		JsonGenerator jsonGenerator = mapper.getJsonFactory()
				.createJsonGenerator(out, JsonEncoding.UTF8);
		if (jsonGenerator != null) {
			jsonGenerator.writeObject(obj);
			jsonGenerator.flush();
			if (!jsonGenerator.isClosed()) {
				jsonGenerator.close();
			}
		}
	}

	/**
	 * 转换为对象
	 */
	public static Object jsonToBean(InputStream in, Class clz) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(
				DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return mapper.readValue(in, clz);
	}
}