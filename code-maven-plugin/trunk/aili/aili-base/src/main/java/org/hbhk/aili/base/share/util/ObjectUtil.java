package org.hbhk.aili.base.share.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ObjectUtil {

	public static List<Method> getMethods(Class<?> cls, List<Method> methods) {
		if (cls != null) {
			Method[] f = cls.getDeclaredMethods();
			if (f != null) {
				methods.addAll(Arrays.asList(f));
			}
		}
		if (cls.getSuperclass() != null) {
			getMethods(cls.getSuperclass(), methods);
		}
		return methods;
	}
	
	public static List<Field> getFields(Class<?> cls, List<Field> fields) {
		if (cls != null) {
			Field[] f = cls.getDeclaredFields();
			if (f != null) {
				fields.addAll(Arrays.asList(f));
			}
		}
		if (cls.getSuperclass() != null) {
			getFields(cls.getSuperclass(), fields);
		}
		return fields;
	}

	public static void copyProperties(Object from, Object to){
		try {
			List<Method> fromMethods = new ArrayList<>();
			fromMethods = getMethods(from.getClass(), fromMethods);
			List<Method> toMethods = new ArrayList<>();
			toMethods = getMethods(to.getClass(), toMethods);
			Method fromMethod = null, toMethod = null;
			String fromMethodName = null, toMethodName = null;
			for (int i = 0; i < fromMethods.size(); i++) {
				fromMethod = fromMethods.get(i);
				fromMethodName = fromMethod.getName();
				if (!fromMethodName.contains("get")){
					continue;
				}
				// 排除列表检测
				toMethodName = "set" + fromMethodName.substring(3);
				toMethod = findMethodByName(toMethods, toMethodName);
				if (toMethod == null) {
					continue;
				}
				Object value = fromMethod.invoke(from, new Object[0]);
				if (value == null){
					continue;
				}
				// 集合类判空处理
//				if (value instanceof Collection) {
//					Collection<?> newValue = (Collection<?>) value;
//					if (newValue.size() <= 0){
//						continue;
//					}
//				}
				toMethod.invoke(to, new Object[] { value });
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}

	public static Method findMethodByName(List<Method> methods, String name) {
		for (int j = 0; j < methods.size(); j++) {
			if (methods.get(j).getName().equals(name))
				return methods.get(j);
		}
		return null;
	}

}
