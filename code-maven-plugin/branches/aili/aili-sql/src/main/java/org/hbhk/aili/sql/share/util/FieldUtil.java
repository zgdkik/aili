package org.hbhk.aili.sql.share.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hbhk.aili.sql.server.annotation.Column;
import org.hbhk.aili.sql.server.annotation.Id;
import org.hbhk.aili.sql.share.model.FieldColumn;

/**
 * sql辅助为类
 */
public class FieldUtil {

	private static Map<String, List<FieldColumn>> classMappingCache = new HashMap<String, List<FieldColumn>>();

	public static List<Field> getColumnFields(Class<?> clazz) {
		List<Field> list = new ArrayList<Field>();
		while (clazz != null) {
		    Field[]	fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				Column col = field.getAnnotation(Column.class);
				if (col != null) {
					list.add(field);
				}
			}
			clazz = clazz.getSuperclass();
		}
		return list;
	}
	
	
	public static List<FieldColumn> getFieldColumnMap(Class<?> clazz) {
		String clsName = clazz.getName();
		if (classMappingCache.containsKey(clsName)) {
			return classMappingCache.get(clsName);
		}
		List<FieldColumn> fieldColumns = new ArrayList<FieldColumn>();
		while (clazz != null) {
		    Field[]	fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				Column col = field.getAnnotation(Column.class);
				if (col != null) {
					String name = field.getName();
					FieldColumn fieldColumn = new FieldColumn();
					fieldColumn.setField(name);
					fieldColumn.setJavaType(field.getType());
					if (col.value()!=null && col.value().equals("")) {
						fieldColumn.setColumn(col.value());
					} else {
						fieldColumn.setColumn(name);
					}
					Id id = field.getAnnotation(Id.class);
					if (id != null) {
						fieldColumn.setPriKey(true);
					}
					fieldColumns.add(fieldColumn);
				}
			}
			clazz = clazz.getSuperclass();
		}
		classMappingCache.put(clsName, fieldColumns);
		return fieldColumns;
	}

	public static String getpriKey(Field[] fields) {
		String pk = null;
		for (Field field : fields) {
			Id id = field.getAnnotation(Id.class);
			if (id != null) {
				pk = field.getAnnotation(Column.class).value();
			}
		}
		if (pk == null) {
			throw new RuntimeException("未找到主键");
		}
		return pk;

	}

}
