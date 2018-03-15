package org.hbhk.aili.mybatis.server.support;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.hbhk.aili.mybatis.server.annotation.Column;
import org.hbhk.aili.mybatis.server.annotation.GenerationType;
import org.hbhk.aili.mybatis.server.annotation.Id;
import org.hbhk.aili.mybatis.server.annotation.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @Description: mybatis增强处理
 * @author 何波
 * @date 2015年3月11日 上午10:05:24 
 *
 */
public class DynamicSqlTemplate {

	public static String dialect = "mysql";
	
	public static Map<String, ModelInfo> tabs = new HashMap<String, ModelInfo>();

	private Logger  log = LoggerFactory.getLogger(getClass());
	
	public String insert(Object obj) {
		Class<?> cls = obj.getClass();
		String clsName = cls.getName();
		Table table = cls.getAnnotation(Table.class);
		boolean dynamicInsert = table.dynamicInsert();
		StringBuilder sql = new StringBuilder();
		ModelInfo tableInfo = tabs.get(clsName);
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
			if(GenerationType.sequence.equals(strategy)){
				sql.append(pk + ",");
				args.append("(");
				args.append(id.sequence() + ",");
			}else if(GenerationType.auto_increment.equals(strategy)){
				log.info("auto_increment :"+ pk);
			}else{
				sql.append(pk + ",");
				args.append("(");
				args.append("#{" + tableInfo.getPkName() + "},");
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
					sql.append(columnName);
					args.append("#{" + name + "}");
					sql.append(",");
					args.append(",");
				}
				
			}
			sql.deleteCharAt(sql.length() - 1);
			args.deleteCharAt(args.length() - 1);
			args.append(")");
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
					sql.append("#{" + col + "}");
				} else {
					sql.append("#{" + col + "},");
				}
			}
			sql.append(")");
		}
		return sql.toString();
	}

	public String update(Object obj) {
		Class<?> cls = obj.getClass();
		String clsName = cls.getName();
		ModelInfo tableInfo = tabs.get(clsName);
		StringBuilder sql = new StringBuilder();
		Table table = cls.getAnnotation(Table.class);
		boolean dynamicUpdate = table.dynamicUpdate();
		if (dynamicUpdate) {
			// 鍔ㄦ�鏇存柊
			sql.append("update ");
			sql.append(tableInfo.getTable() + " set ");
			Field[] fields = tableInfo.getColumnFields();
			String pk = tableInfo.getPk();
			String pkName = tableInfo.getPkName();
			for (Field field : fields) {
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
					sql.append(columnName);
					sql.append(" = ");
					sql.append("#{" + name + "}");
					sql.append(",");
				}
			}
			sql.deleteCharAt(sql.length() - 1);
			sql.append(" where " + pk + "=#{" + pkName + "}");
		} else {
			sql.append("update ");
			sql.append(tableInfo.getTable() + " set ");
			List<String> fieldList = tableInfo.getFieldList();
			List<String> columnList = tableInfo.getColumnList();
			String pk = tableInfo.getPk();
			String pkName = tableInfo.getPkName();
			for (int i = 0; i < fieldList.size(); i++) {
				String field = fieldList.get(i);
				String col = columnList.get(i);
				if ((i + 1) == fieldList.size()) {
					sql.append(col + "=#{" + field + "}");
				} else {
					sql.append(col + "=#{" + field + "},");
				}
			}
			sql.append(" where " + pk + "=#{" + pkName + "}");
		}
		return sql.toString();
	}

	public String getById(Map<String, Object> params) {
		StringBuilder sql = new StringBuilder();
		sql.append("select *from ");
		sql.append(getTableName());
		sql.append(" where "+getPk()+" = #{"+getPkName()+"}");
		return sql.toString();
	}

	public String get(Map<String, Object> params) {
		StringBuilder sql = new StringBuilder();
		sql.append("select *from ");
		sql.append(getTableName() + " ");
		if (params == null) {
			return sql.toString();
		}
		Set<String> keys = params.keySet();
		if (keys.size() > 0) {
			ModelInfo tableInfo = tabs.get(getKey());
			String pk = tableInfo.getPk();
			Map<String, String> fieldColumn = tableInfo.getFieldColumnMap();
			Map<String, Boolean> fieldLike = tableInfo.getFieldLikeMap();
			sql.append("where ");
			int num = 0;
			for (int i = 0; i < keys.size(); i++) {
				String field = keys.toArray(new String[] {})[i];
				String col = fieldColumn.get(field);
				if (StringUtils.isEmpty(col)) {
					throw new RuntimeException(getKey() +" entity  not "+field+" field");
				}
				if (field.equals(pk)) {
					sql.append(pk + "=#{" + pk + "}");
				} else {
					boolean like = fieldLike.get(field);
					String opt = " = ";
					if (like) {
						opt = " like ";
					}
					if (num == 0) {
						sql.append(col + opt + "#{" + field + "}");
					} else {
						sql.append(" and " + col + opt + "#{" + field + "}");
					}
					num++;
				}

			}
		}

		return sql.toString();
	}

	@SuppressWarnings("unchecked")
	public String getPage(Map<String, Object> params) {
		StringBuilder sql = new StringBuilder();
		sql.append("select *from ");
		sql.append(getTableName() + " ");
		if (params == null) {
			return sql.toString();
		}
		Set<String> keys = params.keySet();
		Map<String, Object> newParams = new HashMap<String, Object>();
		for (String key : keys) {
			if (key.startsWith("param")) {
				continue;
			}
			Object obj = params.get(key);
			if (obj instanceof Map) {
				Map<String, Object> map = (Map<String, Object>) obj;
				for (String interKey : map.keySet()) {
					newParams.put(interKey, map.get(interKey));
				}
			} else {
				newParams.put(key, obj);
			}
		}
		Set<String> newKeys = newParams.keySet();
		params.putAll(newParams);
		if (newKeys.size() > 0) {
			ModelInfo tableInfo = tabs.get(getKey());
			Map<String, String> fieldColumn = tableInfo.getFieldColumnMap();
			Map<String, Boolean> fieldLike = tableInfo.getFieldLikeMap();
			if (!((newKeys.size() == 1 && newKeys.contains("page.sorts"))
					||(newKeys.size() == 2 && newKeys.contains("pageNum") && newKeys.contains("pageSize"))
					||(newKeys.size() == 3 && newKeys.contains("pageNum") && newKeys.contains("pageSize")&& newKeys.contains("page.sorts"))
					)) {
				sql.append("where ");
			}
			int num = 0;
			for (int i = 0; i < newKeys.size(); i++) {
				String field = newKeys.toArray(new String[] {})[i];
				if ("pageNum".equals(field) || "pageSize".equals(field)
						|| "page.sorts".equals(field)) {
					continue;
				}
				String col = fieldColumn.get(field);
				if (StringUtils.isEmpty(col)) {
					throw new RuntimeException(getKey() +" entity  not "+field+" field");
				}
				boolean like = fieldLike.get(field);
				String opt = " = ";
				if (like) {
					opt = " like ";
				}
				if (num == 0) {
					sql.append(col + opt + "#{" + field + "}");
					num++;
				} else {
					sql.append(" and " + col + opt + "#{" + field + "}");
					num++;
				}
			}
		}
		Integer pageNum = (Integer) newParams.get("pageNum");
		if (pageNum == null || pageNum <= 0) {
			pageNum = 1;
		}
		Integer pageSize = (Integer) newParams.get("pageSize");
		if (pageSize == null) {
			pageSize = 10;
		}
		int start = 0;
		if (pageNum > 0) {
			start = (pageNum - 1) * pageSize;
		}
		switch (dialect) {
			case "mysql":
				sql.append(" limit " + pageSize + " offset " + start);
				break;
			case "oracle":
				sql = getOraclePageSql(sql.toString(), start, pageSize);
				break;
			case "postgreSQL":
				sql.append(" limit " + pageSize + " offset " + start);
				break;
			default:
				sql.append(" limit " + pageSize + " offset " + start);
				break;
		}
		return sql.toString();
	}
	
	public static StringBuilder getOraclePageSql(String sql, int start, int limit) {
		String s = sql.trim();
		StringBuilder pagingSelect = new StringBuilder(s.length() + 100);
		pagingSelect
				.append("select * from ( select row_.*, rownum rownum_ from ( ");
		pagingSelect.append(s);
			pagingSelect.append(" ) row_  where rownum <= ")
					.append(start+limit).append(") where rownum_ >  "+start);


		return pagingSelect;
	}


	@SuppressWarnings("unchecked")
	public String getPagination(Map<String, Object> params) {
		StringBuilder sql = new StringBuilder();
		sql.append("select *from ");
		sql.append(getTableName() + " ");
		Set<String> keys = params.keySet();
		Map<String, Object> newParams = new HashMap<String, Object>();
		for (String key : keys) {
			if (key.startsWith("param")) {
				continue;
			}
			Object obj = params.get(key);
			if (obj instanceof Map) {
				Map<String, Object> map = (Map<String, Object>) obj;
				for (String interKey : map.keySet()) {
					newParams.put(interKey, map.get(interKey));
				}
			} else {
				newParams.put(key, obj);
			}
		}
		Set<String> newKeys = newParams.keySet();
		params.putAll(newParams);
		if (newKeys.size() > 0) {
			ModelInfo tableInfo = tabs.get(getKey());
			Map<String, String> fieldColumn = tableInfo.getFieldColumnMap();
			Map<String, Boolean> fieldLike = tableInfo.getFieldLikeMap();
			if (!((newKeys.size() == 1 && newKeys.contains("page.sorts"))
					||(newKeys.size() == 2 && newKeys.contains("pageNum") && newKeys.contains("pageSize"))
					||(newKeys.size() == 3 && newKeys.contains("pageNum") && newKeys.contains("pageSize")&& newKeys.contains("page.sorts"))
					)) {
				sql.append("where ");
			}
			int num = 0;
			for (int i = 0; i < newKeys.size(); i++) {
				String field = newKeys.toArray(new String[] {})[i];
				if ("start".equals(field) || "size".equals(field)
						|| "page.sorts".equals(field)) {
					continue;
				}
				String col = fieldColumn.get(field);
				if (StringUtils.isEmpty(col)) {
					throw new RuntimeException(getKey() +" entity  not "+field+" field");
				}
				boolean like = fieldLike.get(field);
				String opt = " = ";
				if (like) {
					opt = " like ";
				}
				if (num == 0) {
					sql.append(col + opt + "#{" + field + "}");
					num++;
				} else {
					sql.append(" and " + col + opt + "#{" + field + "}");
					num++;
				}

			}
		}
		return sql.toString();
	}

	@SuppressWarnings("unchecked")
	public String getPageTotalCount(Map<String, Object> params) {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(*) from ");
		sql.append(getTableName() + " ");
		Set<String> keys = params.keySet();
		Map<String, Object> newParams = new HashMap<String, Object>();
		for (String key : keys) {
			if (key.startsWith("param")) {
				continue;
			}
			Object obj = params.get(key);
			if (obj instanceof Map) {
				Map<String, Object> map = (Map<String, Object>) obj;
				for (String interKey : map.keySet()) {
					newParams.put(interKey, map.get(interKey));
				}
			} else {
				newParams.put(key, obj);
			}
		}
		Set<String> newKeys = newParams.keySet();
		params.putAll(newParams);
		if (newKeys.size() > 0) {
			ModelInfo tableInfo = tabs.get(getKey());
			Map<String, String> fieldColumn = tableInfo.getFieldColumnMap();
			Map<String, Boolean> fieldLike = tableInfo.getFieldLikeMap();
			if (!((newKeys.size() == 1 && newKeys.contains("page.sorts"))
					||(newKeys.size() == 2 && newKeys.contains("pageNum") && newKeys.contains("pageSize"))
					||(newKeys.size() == 3 && newKeys.contains("pageNum") && newKeys.contains("pageSize")&& newKeys.contains("page.sorts"))
					)) {
				sql.append("where ");
			}
			int num = 0;
			for (int i = 0; i < keys.size(); i++) {
				String field = keys.toArray(new String[] {})[i];
				String col = fieldColumn.get(field);
				if (StringUtils.isEmpty(col)) {
					throw new RuntimeException(getKey() +" entity  not "+field+" field");
				}
				boolean like = fieldLike.get(field);
				String opt = " = ";
				if (like) {
					opt = " like ";
				}
				if (num == 0) {
					sql.append(col + opt + "#{" + field + "}");
					num++;
				} else {
					sql.append(" and " + col + opt + "#{" + field + "}");
					num++;
				}

			}
		}
		return sql.toString();
	}

	public String deleteById(Map<String, Object> params) {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from ");
		sql.append(getTableName());
		sql.append(" where "+getPk()+" = #{"+getPkName()+"}");
		return sql.toString();
	}
	public String deleteByObj(Object obj) {
		if(obj==null){
			throw new RuntimeException("entity not null");
		}
		StringBuilder sql = new StringBuilder();
		sql.append("delete from ");
		sql.append(getTableName());
		String clsName = obj.getClass().getName();
		ModelInfo tableInfo = tabs.get(clsName);
		Field[] fields = tableInfo.getColumnFields();
		sql.append(" where ");
		String and =" and,";
		boolean  condition = true;
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
			sql.append(columnName +" = #{" + name + "}");
			sql.append(and);
			condition = false;
		}
		if(condition){
			throw new RuntimeException("update params not null");
		}
		String newSql = sql.substring(0,sql.length() - and.length());
		return newSql;
	}
	
	public String updateStatusById(Map<String, Object> params) {
		StringBuilder sql = new StringBuilder();
		sql.append("update ");
		sql.append(getTableName());
		sql.append(" set status = #{status} ");
		sql.append("where "+getPk()+" = #{"+getPkName()+"}");
		return sql.toString();
	}
	private String getTableName() {
		ModelInfo modelInfo = tabs.get(getKey());
		if (modelInfo == null) {
			throw new RuntimeException("not myatis table object:" + getKey());
		}
		return modelInfo.getTable();
	}
	private String getPkName() {
		ModelInfo modelInfo = tabs.get(getKey());
		if (modelInfo == null) {
			throw new RuntimeException("not myatis table object:" + getKey());
		}
		return modelInfo.getPkName();
	}
	private String getPk() {
		ModelInfo modelInfo = tabs.get(getKey());
		if (modelInfo == null) {
			throw new RuntimeException("not myatis table object:" + getKey());
		}
		return modelInfo.getPk();
	}
	private String getKey() {
		return GnericInterfaceTypeContext.getType().getName();
	}
	
}
