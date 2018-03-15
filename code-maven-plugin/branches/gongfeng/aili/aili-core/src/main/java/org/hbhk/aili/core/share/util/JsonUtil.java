package org.hbhk.aili.core.share.util;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * 
 * @Description: 整合第三方框架支持
 * @author 何波
 * @date 2015年3月11日 上午10:05:24
 * 
 */
public abstract class JsonUtil {

	private static ObjectMapper mapper = new ObjectMapper();

	private static ObjectMapper mapperInclusion = new ObjectMapper();
	static {
		mapperInclusion.setSerializationInclusion(Include.NON_NULL);
	}

	/**
	 * 把json字符串转换为对象
	 * 
	 * @param json
	 * @param parametrized
	 * @param parameterClasses
	 * @return 如果为空串，将返回null
	 * @throws ClientException
	 * @since
	 */
	public static <T> T parseJson(String json, Class<?> parametrized,
			Class<?>... parameterClasses) {
		if (StringUtils.isBlank(json)) {
			return null;
		}
		JsonRootName jsonRootName = parametrized
				.getAnnotation(JsonRootName.class);
		if (jsonRootName != null) {
			mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, true);
		} else {
			mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		}
		try {
			JavaType jt = mapper.getTypeFactory().constructParametricType(
					parametrized, parameterClasses);
			return mapper.readValue(json, jt);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T parseJson(String json, Class<?> parametrized) {
		if (StringUtils.isBlank(json)) {
			return null;
		}
		JsonRootName jsonRootName = parametrized
				.getAnnotation(JsonRootName.class);
		if (jsonRootName != null) {
			mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, true);
		} else {
			mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		}
		try {
			return (T) mapper.readValue(json, parametrized);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public static String toJson(Object obj) {
		if (obj == null) {
			return null;
		}
		JsonRootName jsonRootName = obj.getClass().getAnnotation(
				JsonRootName.class);
		if (jsonRootName != null) {
			mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, true);
		} else {
			mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		}
		try {
			return mapper.writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public static String toJsonInclusion(Object obj) {
		if (obj == null) {
			return null;
		}
		JsonRootName jsonRootName = obj.getClass().getAnnotation(
				JsonRootName.class);
		if (jsonRootName != null) {
			mapperInclusion.configure(SerializationFeature.WRAP_ROOT_VALUE,
					true);
		} else {
			mapperInclusion.configure(SerializationFeature.WRAP_ROOT_VALUE,
					false);
		}
		try {
			return mapperInclusion.writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
}
