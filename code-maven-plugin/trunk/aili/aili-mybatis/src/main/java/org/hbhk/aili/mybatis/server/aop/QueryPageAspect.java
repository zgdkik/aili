package org.hbhk.aili.mybatis.server.aop;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

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
import org.hbhk.aili.mybatis.server.support.DynamicSqlTemplate;
import org.hbhk.aili.mybatis.server.support.GnericInterfaceTypeContext;
import org.hbhk.aili.mybatis.server.support.Page;
import org.hbhk.aili.mybatis.server.support.Pagination;
import org.hbhk.aili.mybatis.server.support.Sort;
import org.hbhk.aili.mybatis.share.util.AopTargetUtil;
import org.hbhk.aili.mybatis.share.util.MybatisSqlHelper;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

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

	@PostConstruct
	public void init() {
		session = new SqlSessionTemplate(sqlSessionFactory);
	}

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Around("this(org.hbhk.aili.mybatis.server.dao.IBaseDao)")
	public Object doQuery(ProceedingJoinPoint pjp) throws Throwable {
		MethodSignature ms = (MethodSignature) pjp.getSignature();
		Method method = ms.getMethod();
		Class<?> rt = method.getReturnType();
		Object currentObj = pjp.getThis();
		Class<?> currentObjItf = AopTargetUtil
				.getJdkDynamicProxyTargeClass(currentObj);
		String mapperIdPrefix = AopTargetUtil
				.getJdkDynamicProxyTargeInterface(currentObj);
		SelectProvider selectProvider = method
				.getAnnotation(SelectProvider.class);
		UpdateProvider updateProvider = method
				.getAnnotation(UpdateProvider.class);
		DeleteProvider deleteProvider = method
				.getAnnotation(DeleteProvider.class);

		try {
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
				DyncSqlSessionFactory dyncSqlSessionFactory = currentObjItf
						.getAnnotation(DyncSqlSessionFactory.class);
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
				String methodName = method.getName();
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
			} else {
				Object obj = pjp.proceed(pjp.getArgs());
				return obj;
			}
		} finally {
			if (selectProvider != null || updateProvider != null
					|| deleteProvider != null) {
				GnericInterfaceTypeContext.remove();
			}
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
