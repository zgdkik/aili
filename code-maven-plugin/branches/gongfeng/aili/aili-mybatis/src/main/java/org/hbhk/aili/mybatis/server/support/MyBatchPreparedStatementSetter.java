package org.hbhk.aili.mybatis.server.support;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.hbhk.aili.mybatis.server.annotation.Column;
import org.hbhk.aili.mybatis.server.annotation.GenerationType;
import org.hbhk.aili.mybatis.server.annotation.Id;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;

public class MyBatchPreparedStatementSetter implements
		BatchPreparedStatementSetter {
	protected static final Logger logger = LoggerFactory
			.getLogger(MyBatchPreparedStatementSetter.class);
	final List<Object> temList;

	public MyBatchPreparedStatementSetter(List<Object> list) {
		temList = list;
		//设置初始化值
	}

	public int getBatchSize() {
		return temList.size();
	}

	public void setValues(PreparedStatement ps, int num) throws SQLException {
		Object sqlObj = temList.get(num);
		ModelInfo model = DynamicSqlTemplate.tabs.get(sqlObj.getClass().getName());
		Field[] fields = model.getColumnFields();
		Field pkField = model.getPkField();
		Id id = pkField.getAnnotation(Id.class);
		String strategy = id.strategy();
		String pk = model.getPk();
		int index  = 1;
		StringBuilder sqlVal = new StringBuilder();
		if (GenerationType.sequence.equals(strategy)) {

		} else if (GenerationType.auto_increment.equals(strategy)) {

		} else {
			try {
				Object value = PropertyUtils.getProperty(sqlObj, model.getPkName());
				sqlType2JavaType(ps, pkField, value, index,sqlVal);
				index++;
			} catch (Exception e) {
				new RuntimeException(e);
			}
		}
		for (int i = 0; i < fields.length; i++) {
			//将主键放在第一例
			Field field = fields[i];
			String name = field.getName();
			Object value = null;
			try {
				value = PropertyUtils.getProperty(sqlObj, name);
			} catch (Exception e) {
				throw new RuntimeException(
						"field " + name + " not get method ", e);
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
				try {
					sqlType2JavaType(ps, field, value, index,sqlVal);
					index++;
				} catch (Exception e) {
					new RuntimeException(e);
				}
			}
		}
		logger.info("sqlValue:"+sqlVal);
	}

	private String sqlType2JavaType(PreparedStatement ps, Field field,
			Object value, int parameterIndex,StringBuilder sqlVal) throws Exception {
		String sqlType = field.getType().getName();
		if (sqlType.equalsIgnoreCase(boolean.class.getName())
				|| sqlType.equalsIgnoreCase(Boolean.class.getName())) {
			ps.setBoolean(parameterIndex, Boolean.valueOf(String.valueOf(value)));
			sqlVal.append(value+",");
		} else if (sqlType.equalsIgnoreCase(byte.class.getName())
				|| sqlType.equalsIgnoreCase(Byte.class.getName())) {
			ps.setByte(parameterIndex, Byte.parseByte(String.valueOf(value)));
			sqlVal.append(value+",");
		} else if (sqlType.equalsIgnoreCase(short.class.getName())
				|| sqlType.equalsIgnoreCase(Short.class.getName())) {
			ps.setShort(parameterIndex, Short.parseShort(String.valueOf(value)));
			sqlVal.append(value+",");
		} else if (sqlType.equalsIgnoreCase(int.class.getName()) || sqlType.equalsIgnoreCase(Integer.class.getName())) {
			ps.setInt(parameterIndex, Integer.parseInt(String.valueOf(value)));
			sqlVal.append(value+",");
			
		} else if (sqlType.equalsIgnoreCase(long.class.getName()) || sqlType.equalsIgnoreCase(Long.class.getName())) {
			ps.setLong(parameterIndex, Long.parseLong(String.valueOf(value)));
			sqlVal.append(value+",");
		} else if (sqlType.equalsIgnoreCase(float.class.getName()) || sqlType.equalsIgnoreCase(Float.class.getName())) {
			ps.setFloat(parameterIndex, Float.parseFloat(String.valueOf(value)));
			sqlVal.append(value+",");
		} else if (sqlType.equalsIgnoreCase(double.class.getName()) ||sqlType.equalsIgnoreCase(Double.class.getName()) ) {
			ps.setDouble(parameterIndex, Double.parseDouble(String.valueOf(value)));
			sqlVal.append(value+",");
		} else if (sqlType.equalsIgnoreCase(String.class.getName()) || value instanceof String) {
			
			ps.setString(parameterIndex, String.valueOf(value));
			sqlVal.append(value+",");
			
		} else if (sqlType.equalsIgnoreCase(Date.class.getName())) {
			
			Date d = (Date) value;
			java.sql.Date sqlDate = new java.sql.Date(d.getTime());
			ps.setDate(parameterIndex, sqlDate);
			sqlVal.append(value+",");
		}
		return null;
	}
}