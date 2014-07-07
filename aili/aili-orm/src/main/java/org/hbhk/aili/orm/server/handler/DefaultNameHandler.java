package org.hbhk.aili.orm.server.handler;

import java.lang.reflect.Field;

import org.hbhk.aili.orm.server.annotation.Column;
import org.hbhk.aili.orm.server.annotation.PrimaryKey;
import org.hbhk.aili.orm.server.annotation.Tabel;

/**
 * 默认名称处理handler
 */
public class DefaultNameHandler implements INameHandler {

	/**
	 * 根据实体名获取表名
	 */
	@Override
	public String getTableName(Class<?> cls) {
		Tabel tbl = cls.getAnnotation(Tabel.class);
		String tblName = null;
		if (tbl != null) {
			tblName = tbl.value();
		} else {
			tblName = cls.getSimpleName();
		}
		return tblName;
	}

	/**
	 * 根据表名获取主键名
	 */
	@Override
	public String getPrimaryName(Class<?> cls) {
		Field[] fields = cls.getDeclaredFields();
		PrimaryKey primaryKey = null;
		boolean brk = true;
		Field pri_field = null;
		for (int i = 0; i < fields.length && brk; i++) {
			pri_field = fields[i];
			primaryKey = pri_field.getAnnotation(PrimaryKey.class);
			if (primaryKey != null) {
				brk = false;
			}
		}
		String pri_id = null;
		Column column = null;
		if (primaryKey != null) {
			column = pri_field.getAnnotation(Column.class);
			if (column != null) {
				pri_id = column.value();
			}else{
				pri_id = pri_field.getName();
			}
		} else{
			throw new RuntimeException("Entity primarykey must have");
		}
		return pri_id;
	}

	/**
	 * 根据属性名获取列名
	 */
	@Override
	public String getColumnName(Class<?> cls, String fieldName) {
		Field[] fields = cls.getDeclaredFields();
		Column column = null;
		boolean brk = true;
		for (int i = 0; i < fields.length && brk; i++) {
			Field field = fields[i];
			if (field.getName().equals(fieldName)) {
				column = field.getAnnotation(Column.class);
			}
		}
		String columnName = null;
		if (column != null) {
			columnName = column.value();
		} else {
			columnName = fieldName;
		}
		return columnName;
	}
}
