package org.hbhk.aili.crud.server.service.impl;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hbhk.aili.base.share.util.FreemarkerUtil;
import org.hbhk.aili.cache.server.CacheManager;
import org.hbhk.aili.core.share.ex.BusinessException;
import org.hbhk.aili.crud.server.cache.CrudCache;
import org.hbhk.aili.crud.server.dao.ICrudDao;
import org.hbhk.aili.crud.server.service.IReportService;
import org.hbhk.aili.crud.share.entity.CrudEntity;
import org.hbhk.aili.crud.share.util.CrudUtil;
import org.hbhk.aili.crud.share.vo.CrudVo;
import org.hbhk.aili.mybatis.server.support.Page;
import org.hbhk.aili.mybatis.server.support.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service
public class ReportService implements IReportService {
	protected static final Logger logger = LoggerFactory.getLogger(ReportService.class);
	
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private ICrudDao crudDao;
	
	
	private String dialect = "mysql";
	
	
	@Override
	public Pagination<Map<String, Object>> getReportData(String code,Map<String, Object> params ,Page page) {
		Pagination<Map<String, Object>>  pagination = new Pagination<Map<String,Object>>();
		pagination.setPageNum(page.getPageNum());
		pagination.setPageSize(page.getPageSize());
		
		CrudEntity report = CacheManager.getInstance().getCache(CrudCache.REPORT_CACHE_ID,code);
		if(report == null){
			throw new BusinessException("动态报表错误:"+code);
		}
		
		String sql = report.getSql();
		sql = FreemarkerUtil.parseStr(sql, params);
		List<Object> conditions = new ArrayList<Object>();
		sql = CrudUtil.getNativeQuery(sql, params, conditions);
		int count =  CrudUtil.getCount(jdbcTemplate,sql, conditions.toArray());
		pagination.setCount(count);
		if(count>0){
			int totalPages = count / page.getPageSize();
			if (count % page.getPageSize() != 0) {
				totalPages = totalPages + 1;
			}
			pagination.setTotalPages(totalPages);
			if("oracle".equals(dialect)){
				sql = CrudUtil.getOraclePageSql(sql, page.getPageNum(), page.getPageSize());
			}else{
				sql = CrudUtil.getMysqlPageSql(sql, page.getPageNum(), page.getPageSize());
			}
			List<Map<String, Object>>  dataList = jdbcTemplate.query(sql, conditions.toArray(),new RowMapper<Map<String, Object>>(){
				@Override
				public Map<String, Object> mapRow(ResultSet rs, int rowNum)
						throws SQLException {
					ResultSetMetaData rsm = rs.getMetaData();
					Map<String, Object> data = new HashMap<String, Object>();
					for (int i = 1; i <= rsm.getColumnCount(); i++) {
						String name = rsm.getColumnLabel(i).toLowerCase();
						data.put(name,  CrudUtil.getResultFromRs(rs, i, rsm.getColumnClassName(i)));
					}
					return data;
				}
			});
			pagination.setDataList(dataList);
		}
		return pagination;
	}

	
	@Override
	public List<String> getColumns(String code) {
		CrudVo crudVo = CacheManager.getInstance().getCache(CrudCache.REPORT_CACHE_ID,code);
		if(crudVo == null){
			throw new BusinessException("动态CRUD错误:"+code);
		}
		CrudEntity crud = crudVo.getCrud();
		String sql = crud.getSql();
		Map<String, Object> params = new HashMap<String, Object>();
		sql = FreemarkerUtil.parseStr(sql, params);
		List<Object> conditions = new ArrayList<Object>();
		sql = CrudUtil.getNativeQuery(sql, params, conditions);
		final List<String> columns = new ArrayList<String>();
		final StringBuilder  f = new StringBuilder("1");
		if("oracle".equals(dialect)){
			sql = CrudUtil.getOraclePageSql(sql, 1, 1);
		}else{
			sql = CrudUtil.getMysqlPageSql(sql, 1, 1);
		}
		jdbcTemplate.query(sql, conditions.toArray(),new RowMapper<Map<String, Object>>(){
			@Override
			public Map<String, Object> mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				ResultSetMetaData rsm = rs.getMetaData();
				Map<String, Object> data = new HashMap<String, Object>();
				for (int i = 1; i <= rsm.getColumnCount() && "1".equals(f.toString()); i++) {
					String name = rsm.getColumnLabel(i).toLowerCase();
					columns.add(name);
				}
				f.append("1");
				return data;
			}
		});
		
		return columns;
	}


	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}


	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}


	public ICrudDao getReportDao() {
		return crudDao;
	}


	public void setReportDao(ICrudDao reportDao) {
		this.crudDao = reportDao;
	}


	public String getDialect() {
		return dialect;
	}


	public void setDialect(String dialect) {
		this.dialect = dialect;
	}


	@Override
	public CrudEntity getByCode(String code) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("code", code);
		params.put("status", 1);
		List<CrudEntity> list = crudDao.get(params);
		if (list == null || list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}


	@Override
	public List<String> validateSql(String sql) {
		Map<String, Object> params = new HashMap<String, Object>();
		sql = FreemarkerUtil.parseStr(sql, params);
		List<Object> conditions = new ArrayList<Object>();
		sql = CrudUtil.getNativeQuery(sql, params, conditions);
		final List<String> columns = new ArrayList<String>();
		final StringBuilder  f = new StringBuilder("1");
		if("oracle".equals(dialect)){
			sql = CrudUtil.getOraclePageSql(sql, 1, 1);
		}else{
			sql = CrudUtil.getMysqlPageSql(sql, 1, 1);
		}
		jdbcTemplate.query(sql, conditions.toArray(),new RowMapper<Map<String, Object>>(){
			@Override
			public Map<String, Object> mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				ResultSetMetaData rsm = rs.getMetaData();
				Map<String, Object> data = new HashMap<String, Object>();
				for (int i = 1; i <= rsm.getColumnCount() && "1".equals(f.toString()); i++) {
					String name = rsm.getColumnLabel(i).toLowerCase();
					columns.add(name);
				}
				f.append("1");
				return data;
			}
		});
		
		return columns;
		
	}
	
	
}
