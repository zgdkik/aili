package org.hbhk.aili.support.server.excel.poi;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class PropertyTypeUtil {

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

	public static void getPropertyType(Map<String, Object> map, Class<?> cls) {
		List<Field> fields = new ArrayList<Field>();
		getFields(cls, fields);
		if (fields != null && !fields.isEmpty()) {
			for (Field field : fields) {
				map.put(field.getName(), field.getType());
			}
		}
	}
}
