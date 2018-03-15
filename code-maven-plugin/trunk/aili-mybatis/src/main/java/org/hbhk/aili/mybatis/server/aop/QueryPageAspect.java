package org.hbhk.aili.mybatis.server.aop;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.hbhk.aili.mybatis.server.annotation.DyncSqlSessionFactory;
import org.hbhk.aili.mybatis.server.annotation.IndexTable;
import org.hbhk.aili.mybatis.server.handler.DefaultCommonValueHandler;
import org.hbhk.aili.mybatis.server.handler.ICommonValueHandler;
import org.hbhk.aili.mybatis.server.support.DynamicSqlTemplate;
import org.hbhk.aili.mybatis.server.support.GenSqlUtil;
import org.hbhk.aili.mybatis.server.support.GnericInterfaceTypeContext;
import org.hbhk.aili.mybatis.server.support.ModelInfo;
import org.hbhk.aili.mybatis.server.support.MyBatchPreparedStatementSetter;
import org.hbhk.aili.mybatis.server.support.Page;
import org.hbhk.aili.mybatis.server.support.Pagination;
import org.hbhk.aili.mybatis.server.support.Sort;
import org.hbhk.aili.mybatis.share.BaseEntity;
import org.hbhk.aili.mybatis.share.util.AopTargetUtil;
import org.hbhk.aili.mybatis.share.util.MybatisSqlHelper;
import org.hbhk.aili.mybatis.share.util.PropertiesHelper;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.util.CollectionUtils;

/**
 * 
 * @Description: mybatis增强处理
 * @author 何波
 * @date 2015年3月11日 上午10:05:24
 *
 */
@Aspect
public class QueryPageAspect {
	protected static final Logger logger = LoggerFactory
			.getLogger(QueryPageAspect.class);
	@Autowired
	@Qualifier("sqlSessionFactory")
	private SqlSessionFactory sqlSessionFactory;

	private JdbcTemplate jdbcTemplate;

	private SqlSession session;

	private Map<String, SqlSessionFactory> sqlSessionMap = new ConcurrentHashMap<String, SqlSessionFactory>();

	private Map<String, SqlSession> sqlSessionCache = new ConcurrentHashMap<String, SqlSession>();

	private Map<String, DataSource> dataSourceMap = new ConcurrentHashMap<String, DataSource>();

	private Map<String, JdbcTemplate> jdbcTemplateCache = new ConcurrentHashMap<String, JdbcTemplate>();
	
	public static final String INSERT_BATCH = "insertBatch";
	
	public static final String getByIndex = "getByIndex";

	
	private ICommonValueHandler commonValueHandler;
	
	
	@PostConstruct
	public void init() {
		session = new SqlSessionTemplate(sqlSessionFactory);
		commonValueHandler = new DefaultCommonValueHandler();
	}

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}


	@SuppressWarnings("unchecked")
	@Around("this(org.hbhk.aili.mybatis.server.dao.IBaseDao)")
	public Object doQuery(ProceedingJoinPoint pjp) throws Throwable {
		MethodSignature ms = (MethodSignature) pjp.getSignature();
		Method method = ms.getMethod();
		Class<?> rt = method.getReturnType();
		Object currentObj = pjp.getThis();
		String mapperIdPrefix = AopTargetUtil
				.getJdkDynamicProxyTargeInterface(currentObj);
		SelectProvider selectProvider = method
				.getAnnotation(SelectProvider.class);
		UpdateProvider updateProvider = method
				.getAnnotation(UpdateProvider.class);
		DeleteProvider deleteProvider = method
				.getAnnotation(DeleteProvider.class);
		try {
			confirmDataSource(pjp);
			String methodName = method.getName();
			if(INSERT_BATCH.equals(methodName)){
				Object[] args = pjp.getArgs();
				List<Object>  list = (List<Object>) args[0];
				initInsertVal(list);
				String sql = GenSqlUtil.insertBatch(list.get(0),1);
				return jdbcTemplate.batchUpdate(sql,new MyBatchPreparedStatementSetter(list));
			}
			Class<?> type = null;
			if (selectProvider != null) {
				type = selectProvider.type();
			}
			if (type == null && updateProvider != null) {
				type = updateProvider.type();
			}
			if (type == null && deleteProvider != null) {
				type = deleteProvider.type();
			}
			if (type != null && type.equals(DynamicSqlTemplate.class)) {
				Class<?> cls = AopTargetUtil.getJdkDynamicProxyTargeClass(pjp
						.getThis());
				Class<?> gnericInterfaceType = getGenericInterfaces(cls);
				GnericInterfaceTypeContext.setType(gnericInterfaceType);
			}
			if (rt != null && rt.equals(Pagination.class)) {
				String statement = mapperIdPrefix + "." + methodName;
				Object[] args = pjp.getArgs();
				Map<String, Object> params = getParams(args);
				String sql = MybatisSqlHelper
						.getSql(session, statement, params);
				sql = getCountQueryStringForSql(sql);
				Object[] qargs = MybatisSqlHelper.getParams(session, statement,
						params);
				int count = getCount(sql, qargs);
				Pagination<Object> pagination = new Pagination<Object>();
				if (count == 0) {
					return pagination;
				}
				Page page = getPage(args);
				Sort[] sorts = getSorts(args);
				params.put("page.sorts", sorts);
				RowBounds rowBounds = new RowBounds(page.getStart(),
						page.getPageSize());
				List<Object> list = session.selectList(statement, params,
						rowBounds);
				
				pagination.setDataList(list);
				pagination.setCount(count);
				int totalPages = count / page.getPageSize();
				if (count % page.getPageSize() != 0) {
					totalPages = totalPages + 1;
				}
				pagination.setTotalPages(totalPages);
				pagination.setPageNum(page.getPageNum());
				pagination.setPageSize(page.getPageSize());
				return pagination;
			}
			if(getByIndex.equals(methodName)){
				String statement = mapperIdPrefix + "." + methodName;
				Object[] args = pjp.getArgs();
				List<Object> indexData =  getIndexTable(args[0]);
				List<Object> list = session.selectList(statement, indexData);
				return list;
			}
			Object obj = pjp.proceed(pjp.getArgs());
			return obj;
			
		} finally {
			if (selectProvider != null || updateProvider != null
					|| deleteProvider != null) {
				GnericInterfaceTypeContext.remove();
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private List<Object> getIndexTable(Object arg) {
		ModelInfo model =  DynamicSqlTemplate.tabs.get(arg.getClass().getName());
		Map<String, Field> indexTabs =  model.getIndexTable();
		List<Object> list = null;
		if(!CollectionUtils.isEmpty(indexTabs)){
			Set<String> keys =  indexTabs.keySet();
			for (String key : keys) {
				Field field =  indexTabs.get(key);
				IndexTable indexTable = field.getAnnotation(IndexTable.class);
				Class<?> cls =   indexTable.cls();
				final String indexCol = indexTable.col();
				ModelInfo indexModel =  DynamicSqlTemplate.tabs.get(cls.getName());
				List<Object> params = new ArrayList<Object>();
				String sql = getIndexTableSql(indexModel, arg, indexCol,params);
				list = (List<Object>) jdbcTemplate.query(sql,params.toArray(),new ResultSetExtractor<Object>() {
					@Override
					public Object extractData(ResultSet rs)
							throws SQLException, DataAccessException {
						List<Object> list = new ArrayList<Object>();  
						while (rs.next()) {
							Object obj = rs.getObject(indexCol);
							list.add(obj);
						}
						return list;
					}
				} );
			}
		}
		return list;
	}
	public String getIndexTableSql(ModelInfo tableInfo,Object obj,String indexCol,List<Object> params) {
		Map<String, Object> map = PropertiesHelper.beanToMap(obj) ;
		StringBuilder sql = new StringBuilder();
		sql.append("select "+indexCol+" from ");
		sql.append(tableInfo.getTable() + " ");
		if (map == null) {
			return sql.toString();
		}
		Set<String> keys = map.keySet();
		if (keys.size() > 0) {
			String pk = tableInfo.getPk();
			Map<String, String> fieldColumn = tableInfo.getFieldColumnMap();
			Map<String, Boolean> fieldLike = tableInfo.getFieldLikeMap();
			sql.append("where ");
			int num = 0;
			for (int i = 0; i < keys.size(); i++) {
				String field = keys.toArray(new String[] {})[i];
				Object value = null;
				try {
					value = PropertyUtils.getProperty(obj, field);
					if(value == null){
						continue;
					}
				} catch (Exception e) {
					throw new RuntimeException("field "+field+" not get method ", e);
				}
				String col = fieldColumn.get(field);
				if (StringUtils.isEmpty(col)) {
					throw new RuntimeException(" entity  not "+field+" field");
				}
				if (field.equals(pk)) {
					sql.append(pk + "=?");
					num++;
				} else {
					boolean like = fieldLike.get(field);
					String opt = " = ";
					if (like) {
						opt = " like ";
					}
					if (num == 0) {
						sql.append(col + opt + "?");
					} else {
						sql.append(" and " + col + opt + "?");
					}
					num++;
				}
				params.add(value);

			}
		}

		return sql.toString();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initInsertVal(List<Object> list){
		for (Object obj : list) {
			BaseEntity baseObj = (BaseEntity) obj;
			baseObj.setId(commonValueHandler.getId());
			baseObj.setCreater(commonValueHandler.getCreater());
			baseObj.setCreateTime(new Date());
			baseObj.setVersion(commonValueHandler.getRecordVersion());
			baseObj.setCompCode(commonValueHandler.getCompCode());
			baseObj.setIsDelete(commonValueHandler.getIsDelete());
		}
	}

	private Class<?> getGenericInterfaces(Class<?> clazz) throws Exception {
		Type[] types = clazz.getGenericInterfaces();
		ParameterizedType pType = null;
		if (types.length == 0) {
			// pType = clazz.;
			return clazz;
		} else {
			pType = (ParameterizedType) types[0];
		}

		Class<?> cls = (Class<?>) pType.getActualTypeArguments()[0];
		return cls;
	}

	private int getCount(String sql, Object[] args) {
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

	private String getCountQueryStringForSql(String sql) {
		if (sql == null)
			return null;
		sql = sql.trim();
		String countSQL = "select count(1) as num from (" + sql + ") tmp_tbl";
		logger.debug("Count SQL:{}", countSQL);
		return countSQL;
	}

	@SuppressWarnings("unchecked")
	private Map<String, Object> getParams(Object[] args) {
		Map<String, Object> params = new HashMap<String, Object>();
		if (args != null) {
			for (Object arg : args) {
				if (arg instanceof Map) {
					return (Map<String, Object>) arg;
				}
			}
		}
		return params;
	}

	private Page getPage(Object[] args) {
		if (args != null) {
			for (Object arg : args) {
				if (arg instanceof Page) {
					return (Page) arg;
				}
			}
		}
		Page page = new Page();
		page.setPageNum(0);
		page.setPageSize(10);
		return page;
	}

	private Sort[] getSorts(Object[] args) {
		if (args != null) {
			for (Object arg : args) {
				if (arg instanceof Sort[]) {
					return (Sort[]) arg;
				}
			}
		}
		return null;
	}
	private  void confirmDataSource(ProceedingJoinPoint pjp) throws Exception{
		Object currentObj = pjp.getThis();
		Class<?> currentObjItf = AopTargetUtil
				.getJdkDynamicProxyTargeClass(currentObj);
		DyncSqlSessionFactory dyncSqlSessionFactory = currentObjItf
				.getAnnotation(DyncSqlSessionFactory.class);
		String mapperIdPrefix = AopTargetUtil
				.getJdkDynamicProxyTargeInterface(currentObj);
		if (dyncSqlSessionFactory != null) {
			String dyncSqlSessionKey = dyncSqlSessionFactory.value();
			if (StringUtils.isEmpty(dyncSqlSessionKey)) {
				throw new RuntimeException("多数据源接口 " + mapperIdPrefix
						+ "未指定执行的SqlSessionFactory");
			}
			session = sqlSessionCache.get(dyncSqlSessionKey);
			jdbcTemplate = jdbcTemplateCache.get(dyncSqlSessionKey);
			if (session == null) {
				SqlSessionFactory ssf = sqlSessionMap
						.get(dyncSqlSessionKey);
				DataSource ds = dataSourceMap.get(dyncSqlSessionKey);
				if (ssf == null || ds == null) {
					throw new RuntimeException("多数据源接口接口 "
							+ mapperIdPrefix
							+ "设置SqlSessionFactory与配置不一致");
				}
				session = new SqlSessionTemplate(ssf);
				jdbcTemplate = new JdbcTemplate(ds);
				sqlSessionCache.put(dyncSqlSessionKey, session);
				jdbcTemplateCache.put(dyncSqlSessionKey, jdbcTemplate);
			}
		}
	}
	

	public Map<String, SqlSessionFactory> getSqlSessionMap() {
		return sqlSessionMap;
	}

	public void setSqlSessionMap(Map<String, SqlSessionFactory> sqlSessionMap) {
		this.sqlSessionMap = sqlSessionMap;
	}

	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public Map<String, DataSource> getDataSourceMap() {
		return dataSourceMap;
	}

	public void setDataSourceMap(Map<String, DataSource> dataSourceMap) {
		this.dataSourceMap = dataSourceMap;
	}

}
