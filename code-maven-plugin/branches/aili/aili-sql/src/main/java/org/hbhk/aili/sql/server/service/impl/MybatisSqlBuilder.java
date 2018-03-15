package org.hbhk.aili.sql.server.service.impl;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hbhk.aili.sql.server.annotation.Column;
import org.hbhk.aili.sql.server.annotation.Table;
import org.hbhk.aili.sql.server.service.ISqlBuilder;
import org.hbhk.aili.sql.share.model.ModelInfo;
import org.hbhk.aili.sql.share.model.SqlContext;

/**
 * 
 * @Description: mybatis sql生成器
 * @author hbhk
 * @date 2015年6月30日 上午9:18:49
 */
public class MybatisSqlBuilder implements ISqlBuilder {

	private static Map<String, ModelInfo> tabs = new HashMap<String, ModelInfo>();
	
	@Override
	public SqlContext insertBuilder(Class<?> cls) {
		String clsName = cls.getName();
		Table table = cls.getAnnotation(Table.class);
		boolean dynamicInsert = table.dynamicInsert();
		StringBuilder sql = new StringBuilder();
		ModelInfo tableInfo = tabs.get(clsName);
		if(dynamicInsert){
			//动态插入
			Field[] fields = tableInfo.getColumnFields();
			sql.append("insert into ");
			sql.append(tableInfo.getTable() + " (");
			String pk = tableInfo.getPk();
			sql.append(pk+",");
			StringBuilder args = new StringBuilder();
			args.append("(");
			args.append("#{"+pk+"},");
			for (int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				String name = field.getName();
				Object value = null;
				try {
					//value = PropertyUtils.getProperty(obj, name);
				} catch (Exception e) {
					throw new RuntimeException("获取属性值错误:", e);
				}
				if (value == null) {
					continue;
				}
				Column col = field.getAnnotation(Column.class);
				String columnName = col.value();
				if (columnName==null || columnName.equals("")) {
					continue;
				}
				sql.append(columnName);
				args.append("#{"+columnName+"}");
				sql.append(",");
				args.append(",");
			}
			sql.deleteCharAt(sql.length() - 1);
			args.deleteCharAt(args.length() - 1);
			args.append(")");
			sql.append(")");
			sql.append(" values ");
			sql.append(args);
		}else{
			List<String> fieldList = tableInfo.getFieldList();
			sql.append("insert into ");
			sql.append(tableInfo.getTable() + " (");
			sql.append(tableInfo.getColumns() + ") ");
			sql.append("values(");
			for (int i = 0; i < fieldList.size(); i++) {
				String col = fieldList.get(i);
				if ((i + 1) == fieldList.size()) {
					sql.append("#{"+col+"}");
				} else {
					sql.append("#{"+col+"},");
				}
			}
			sql.append(")");
		}
		return null;
	}

	@Override
	public SqlContext updateBuilder(Class<?> cls) {
		return null;
	}

	@Override
	public SqlContext selectBuilder(Class<?> cls) {
		return null;
	}
}
