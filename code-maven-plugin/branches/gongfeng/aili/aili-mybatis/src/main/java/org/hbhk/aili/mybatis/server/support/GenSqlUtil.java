package org.hbhk.aili.mybatis.server.support;

import java.lang.reflect.Field;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.hbhk.aili.mybatis.server.annotation.Column;
import org.hbhk.aili.mybatis.server.annotation.GenerationType;
import org.hbhk.aili.mybatis.server.annotation.Id;
import org.hbhk.aili.mybatis.server.annotation.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenSqlUtil {
	private static Logger  log = LoggerFactory.getLogger(GenSqlUtil.class);

	public static String insertBatch(Object obj,int size) {
		if(obj==null){
			throw new RuntimeException("entity not null");
		}
		Class<?> cls = obj.getClass();
		Table table = cls.getAnnotation(Table.class);
		boolean dynamicInsert = table.dynamicInsert();
		StringBuilder sql = new StringBuilder();
		ModelInfo tableInfo = DynamicSqlTemplate.tabs.get(cls.getName());
		if (dynamicInsert) {
			// 鍔ㄦ�鎻掑叆
			Field[] fields = tableInfo.getColumnFields();
			sql.append("insert into ");
			sql.append(tableInfo.getTable() + " (");
			String pk = tableInfo.getPk();
		
			StringBuilder args = new StringBuilder();
			Field pkField = tableInfo.getPkField(); 
			Id id = pkField.getAnnotation(Id.class);
			String strategy = id.strategy();
			boolean insertPrefix = true;
			for (int s = 0; s < size; s++) {
				if(GenerationType.sequence.equals(strategy)){
					if(insertPrefix){
						sql.append(pk + ",");
					}
					args.append(id.sequence() + ",");
				}else if(GenerationType.auto_increment.equals(strategy)){
					log.info("auto_increment :"+ pk);
					args.append("(");
				}else{
					if(insertPrefix){
						sql.append(pk + ",");
					}
					try {
						args.append("(?,");
					} catch (Exception e) {
						throw new RuntimeException("field "+tableInfo.getPkName()+" not get method ", e);
					} 
				}
				for (int i = 0; i < fields.length; i++) {
					Field field = fields[i];
					String name = field.getName();
					Object value = null;
					try {
						value = PropertyUtils.getProperty(obj, name);
					} catch (Exception e) {
						throw new RuntimeException("field "+name+" not get method ", e);
					}
					if (value == null) {
						continue;
					}
					Column col = field.getAnnotation(Column.class);
					String columnName = col.value();
					if (StringUtils.isEmpty(columnName)) {
						continue;
					}
					if (!pk.equalsIgnoreCase(columnName)) {
						if(insertPrefix){
							sql.append(columnName);
							sql.append(",");
						}
						args.append("?");
						args.append(",");
					}
				}
				args.deleteCharAt(args.length() - 1);
				args.append("),");
				insertPrefix = false;
			}
			args.deleteCharAt(args.length() - 1);
			sql.deleteCharAt(sql.length() - 1);
			sql.append(")");
			sql.append(" values ");
			sql.append(args);
		} else {
			List<String> fieldList = tableInfo.getFieldList();
			sql.append("insert into ");
			sql.append(tableInfo.getTable() + " (");
			sql.append(tableInfo.getColumns() + ") ");
			sql.append("values(");
			for (int i = 0; i < fieldList.size(); i++) {
				String col = fieldList.get(i);
				if ((i + 1) == fieldList.size()) {
					sql.append("?");
				} else {
					sql.append("?,");
				}
			}
			sql.append(")");
		}
		return sql.toString();
	}

}
