package org.mybatis.spring.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.annotation.Column;
import org.mybatis.spring.annotation.Id;

/**
 * sql辅助为类
 */
public class SqlUtil {

	private static Map<String, List<FieldColumn>> classMappingCache = new HashMap<String, List<FieldColumn>>();

	public static Field[] getColumnFields(Class<?> clazz) {
		List<Field> list = new ArrayList<Field>();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			Column col = field.getAnnotation(Column.class);
			if (col != null) {
				list.add(field);
			}
		}
		if (clazz.getSuperclass() != null) {
			Class<?> superClass = clazz.getSuperclass();
			fields = superClass.getDeclaredFields();
			for (Field field : fields) {
				Column col = field.getAnnotation(Column.class);
				if (col != null) {
					list.add(field);
				}
			}
		}
		if (clazz.getSuperclass().getSuperclass() != null) {
			Class<?> superClass = clazz.getSuperclass().getSuperclass();
			fields = superClass.getDeclaredFields();
			for (Field field : fields) {
				Column col = field.getAnnotation(Column.class);
				if (col != null) {
					list.add(field);
				}
			}
		}
		return list.toArray(new Field[] {});
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
	public static Field getpriKeyField(Field[] fields) {
		Field pk = null;
		for (Field field : fields) {
			Id id = field.getAnnotation(Id.class);
			if (id != null) {
				return field ;
			}
		}
		return pk;

	}
	public static String getpriKeyName(Field[] fields) {
		String pk = null;
		for (Field field : fields) {
			Id id = field.getAnnotation(Id.class);
			if (id != null) {
				pk = field.getName();
			}
		}
		if (pk == null) {
			throw new RuntimeException("未找到主键");
		}
		return pk;

	}
	public static List<FieldColumn> getFieldColumnMap(Class<?> clazz) {
		String clsName = clazz.getName();
		if (classMappingCache.containsKey(clsName)) {
			return classMappingCache.get(clsName);
		}
		List<FieldColumn> fieldColumns = new ArrayList<FieldColumn>();
		Field[] fields =getColumnFields(clazz);
		for (Field field : fields) {
			if (Modifier.isFinal(field.getModifiers())
					|| Modifier.isStatic(field.getModifiers())) {
				continue;
			}
			Column col = field.getAnnotation(Column.class);
			if(col == null){
				continue;
			}
			String name = field.getName();
			FieldColumn fieldColumn = new FieldColumn();
			fieldColumn.setField(name);
			fieldColumn.setJavaType(field.getType());
			fieldColumn.setColumn(col.value());
			fieldColumns.add(fieldColumn);
		}
		classMappingCache.put(clsName, fieldColumns);
		
		return fieldColumns;
	}

}
