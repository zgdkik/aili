package com.yimidida.ymdp.dao.share.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * @Description: aili妗嗘灦鏍稿績澶勭悊鍜屽熀鏈敮鎸�
 * @author 浣曟尝
 * @date 2015骞�3鏈�11鏃� 涓婂崍10:05:24
 *
 */
public class BeanToMapUtil {

	public static Map<String, Object> convert(Object bean) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (bean == null) {
			return map;
		}
		Class<?> cls = bean.getClass();
		Field[] fields = getColumnFields(cls);
		for (Field field : fields) {
			String name = field.getName();
			if (Modifier.isStatic(field.getModifiers())
					|| Modifier.isFinal(field.getModifiers())) {
				continue;
			}
			Object value = null;
			try {
				field.setAccessible(true);
				value = field.get(bean);
			} catch (Exception e) {
				throw new RuntimeException("鑾峰彇婧愬璞″睘鎬х殑鍊煎嚭閿�:", e);
			}
			if (value == null) {
				continue;
			}
			if (value instanceof String) {
				if (StringUtils.isEmpty(value.toString()))
					continue;
			}
			map.put(name, value);
		}
		return map;
	}

	public static void convert(Object bean, Map<String, Object> map) {
		if (bean == null) {
			throw new RuntimeException("bean瀵硅薄涓虹┖");
		}
		if (map == null) {
			throw new RuntimeException("map瀵硅薄涓虹┖");
		}
		Class<?> cls = bean.getClass();
		Field[] fields = getColumnFields(cls);
		for (Field field : fields) {
			String name = field.getName();
			if (Modifier.isStatic(field.getModifiers())
					|| Modifier.isFinal(field.getModifiers())) {
				continue;
			}
			Object value = null;
			try {
				field.setAccessible(true);
				value = field.get(bean);
			} catch (Exception e) {
				throw new RuntimeException("鑾峰彇婧愬璞″睘鎬х殑鍊煎嚭閿�:", e);
			}
			if (value == null) {
				continue;
			}
			map.put(name, value);
		}

	}

	public static Field[] getColumnFields(Class<?> clazz) {
		List<Field> list = new ArrayList<Field>();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			list.add(field);
		}
		if (clazz.getSuperclass() != null) {
			Class<?> superClass = clazz.getSuperclass();
			fields = superClass.getDeclaredFields();
			for (Field field : fields) {
				list.add(field);
			}
		}
		return list.toArray(new Field[] {});
	}
}
