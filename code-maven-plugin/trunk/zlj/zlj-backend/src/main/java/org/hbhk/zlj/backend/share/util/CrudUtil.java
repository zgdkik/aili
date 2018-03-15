package org.hbhk.zlj.backend.share.util;

import java.beans.PropertyDescriptor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

public class CrudUtil {
	protected static final Logger logger = LoggerFactory.getLogger(CrudUtil.class);
	
	public static String getNativeQuery(String queryName,
			Map<String, Object> paramsEx, List<Object> conditions) {
		StringBuffer sb = new StringBuffer();
		boolean inParamName = false;
		StringBuffer paramNameSb = new StringBuffer();
		for (char c : queryName.toCharArray()) {
			if (c == ':') {
				if (inParamName)
					throw new RuntimeException("Wrong query.");
				inParamName = true;
			} else {
				if (c == ' ' || c == '\t' || c == '\n' || c == '\r' || c == ','
						|| c == ')') {
					if (inParamName) {
						inParamName = false;
						sb.append('?');
						conditions.add(paramsEx.get(paramNameSb.toString()));
						paramNameSb = new StringBuffer();
					}
					sb.append(c);
				} else {
					if (inParamName) {
						paramNameSb.append(c);
					} else
						sb.append(c);
				}
			}
		}
		return sb.toString();
	}
	
	protected Object[] getParamValueAndType(String key, Map<String, Object[]> paramMap){
		int delim = key.indexOf('.');
		if(delim <0){
			return paramMap.get(key);
		}
		String pname = key.substring(0,delim);
		String cname = key.substring(delim+1);
		return getParamValueInternal(cname, paramMap.get(pname));
	}
	
	private Object[] getParamValueInternal(String key, Object[] valObj){
		if(key == null || key.trim().length() ==0) return valObj;
		Object val = valObj[0];
		Class<?> clazz = (Class<?>)valObj[1];
		
		if(val == null){
			PropertyDescriptor[] props = PropertyUtils.getPropertyDescriptors(clazz);
			Class<?> c = String.class;
			for(PropertyDescriptor p: props){
				if(p.getName().equals(key))
					c = p.getPropertyType();
			}
			return new Object[]{null, c};
		}else if(key.indexOf('.') < 0){
			try {
				return new Object[]{PropertyUtils.getProperty(val, key),
						PropertyUtils.getPropertyDescriptor(val, key).getPropertyType()};
			} catch (Exception e) {
				logger.error("Get Query Param Error: {} in {}", 
						key, val.getClass());
				throw new RuntimeException("Query Param Error");
			} 
		}else{
			int delim = key.indexOf('.');
			
			String pname = key.substring(0,delim);
			String cname = key.substring(delim+1);
			try{
				return getParamValueInternal(cname, new Object[]{PropertyUtils.getProperty(val, pname),
						PropertyUtils.getPropertyDescriptor(val, pname).getPropertyType()});
			} catch (Exception e) {
				logger.error("Get Query Param Error: {} in {}", 
						key, val.getClass());
				throw new RuntimeException("Query Param Error");
			} 			
		}
		
	}
	public static int getCount(JdbcTemplate jdbcTemplate,String sql, Object[] args) {
		sql = getCountQueryStringForSql(sql);
		int count = jdbcTemplate.query(sql, args,
				new ResultSetExtractor<Integer>() {
					@Override
					public Integer extractData(ResultSet rs)
							throws SQLException, DataAccessException {
						rs.next();
						int num = rs.getInt("num");
						return num;
					}
				});
		return count;
	}

	public static String getCountQueryStringForSql(String sql) {
		if (sql == null)
			return null;
		sql = sql.trim();
		String countSQL = "select count(1) as num from (" + sql + ") tmp_tbl";
		logger.debug("Count SQL:{}", countSQL);
		return countSQL;
	}

	@SuppressWarnings("unchecked")
	public static <K> K getResultFromRs(ResultSet rs, int icolumn, String clazzStr) throws SQLException{
		if(clazzStr == null)
			return (K) rs.getObject(icolumn);
		else{
			Class<K> clazz = null;
			try {
				clazz = (Class<K>) Class.forName(clazzStr);
			} catch (ClassNotFoundException e) {
				throw  new RuntimeException(e);
			}
			K value = null;
			if(clazz.equals(String.class))
				value = (K) rs.getString(icolumn);
			else if(clazz.equals(java.math.BigDecimal.class))
				value = (K) rs.getBigDecimal(icolumn);
			else if(clazz.equals(java.io.InputStream.class))
				value = (K) rs.getBinaryStream(icolumn);
			else if(clazz.equals(java.sql.Blob.class))
				value = (K) rs.getBlob(icolumn);
			else if(clazz.equals(Boolean.class) || clazz.equals(boolean.class))
				value = (K) new Boolean(rs.getBoolean(icolumn));
			else if(clazz.equals(Byte.class) || clazz.equals(byte.class))
				value = (K) new Byte(rs.getByte(icolumn));
			else if(clazz.equals(Byte[].class))
				value = (K) rs.getBytes(icolumn);
			else if(clazz.equals(java.io.Reader.class))
				value = (K) rs.getCharacterStream(icolumn);
			else if(clazz.equals(java.sql.Clob.class))
				value = (K) rs.getClob(icolumn);
			else if(clazz.equals(java.sql.Date.class))
				value = (K) rs.getDate(icolumn);
			else if(clazz.equals(Double.class) || clazz.equals(double.class))
				value = (K) new Double(rs.getDouble(icolumn));
			else if(clazz.equals(Float.class) || clazz.equals(float.class))
				value = (K) new Float(rs.getFloat(icolumn));
			else if(clazz.equals(Integer.class) || clazz.equals(int.class))
				value = (K) new Integer(rs.getInt(icolumn));
			else if(clazz.equals(Long.class) || clazz.equals(long.class))
				value = (K) new Long(rs.getLong(icolumn));
			else if(clazz.equals(Short.class) || clazz.equals(short.class))
				value = (K) new Short(rs.getShort(icolumn));
			else if(clazz.equals(java.sql.Time.class))
				value = (K) rs.getTime(icolumn);
			else if(clazz.equals(java.sql.Timestamp.class))
				value = (K) rs.getTimestamp(icolumn);
			else if(clazz.equals(java.util.Date.class))
				value = (K) (rs.getTimestamp(icolumn) == null ? null :new java.util.Date(rs.getTimestamp(icolumn).getTime()));
			else
				value = (K) rs.getObject(icolumn);
			return (value == null ||rs.wasNull()) ? null : value;
		}
	}

	public static String getOraclePageSql(String sql, int pageNum, int pageSize) {
		int start = 0;
		if (pageNum > 0) {
			start = (pageNum - 1) * pageSize;
		}
		String s = sql.trim();
		StringBuilder pagingSelect = new StringBuilder(s.length() + 100);
		pagingSelect
				.append("select * from ( select row_.*, rownum rownum_ from ( ");
		pagingSelect.append(s);
			pagingSelect.append(" ) row_  where rownum <= ")
					.append(start+pageSize).append(") where rownum_ >  "+start);


		return pagingSelect.toString();
	}
	
	public static String getMysqlPageSql(String sql, int pageNum, int pageSize) {
		int start = 0;
		if (pageNum > 0) {
			start = (pageNum - 1) * pageSize;
		}
		StringBuilder pagingSelect = new StringBuilder(sql);
		pagingSelect.append(" limit " + pageSize + " offset " + start);

		return pagingSelect.toString();
	}
}
