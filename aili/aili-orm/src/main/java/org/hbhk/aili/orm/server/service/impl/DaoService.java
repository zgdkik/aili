package org.hbhk.aili.orm.server.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hbhk.aili.orm.server.dao.Sort;
import org.hbhk.aili.orm.server.service.IDaoService;
import org.hbhk.aili.orm.share.model.Pagination;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Service;

@Service
public class DaoService implements IDaoService {

	@Resource
	protected JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public <T> T getByPrimaryKey(Class<T> clazz, Serializable pk) {
		return null;
	}

	@Override
	public <T> T save(T model) {
		return null;
	}

	@Override
	public <T> void delete(T model) {

	}

	@Override
	public <T> void deleteByPrimaryKey(Class<T> clazz, Serializable pk) {

	}

	@Override
	public <T> T findOneByNamedQuery(String queryName,
			Map<String, Object> params, Sort[] sorts) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T findOneByQuery(String queryString, Map<String, Object> params,
			Sort[] sorts) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T findOneByQueryEx(String queryString,
			Map<String, Object> params, Sort[] sorts) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> findByNamedQuery(String queryName,
			Map<String, Object> params) {
		return null;
	}

	@Override
	public <T> List<T> findByNamedQuery(String queryName,
			Map<String, Object> params, int start, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> Pagination<T> findByNamedQuery(String queryName,
			Map<String, Object> params, int start, int pageSize,
			boolean withGroupby) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> findByNamedQuery(String queryName,
			Map<String, Object> params, Sort[] sorts) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> findByNamedQuery(String queryName,
			Map<String, Object> params, Sort[] sorts, int start, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> Pagination<T> findByNamedQuery(String queryName,
			Map<String, Object> params, Sort[] sorts, int start, int pageSize,
			boolean withGroupby) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> findByQuery(String queryString,
			Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> findByQuery(String queryString,
			Map<String, Object> params, int start, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> Pagination<T> findByQuery(String queryString,
			Map<String, Object> params, int start, int pageSize,
			boolean withGroupby) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> findByQuery(String queryString,
			Map<String, Object> params, Sort[] sorts) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> findByQuery(String queryString,
			Map<String, Object> params, Sort[] sorts, int start, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> Pagination<T> findByQuery(String queryString,
			Map<String, Object> params, Sort[] sorts, int start, int pageSize,
			boolean withGroupby) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> findByQueryEx(String queryString,
			Map<String, Object> params, Sort[] sorts, int start, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> Pagination<T> findByQueryEx(String queryString,
			Map<String, Object> params, Sort[] sorts, int start, int pageSize,
			boolean withGroupby) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int batchUpdateByNamedQuery(String queryName,
			Map<String, Object> params) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int batchUpdateByQuery(String queryString, Map<String, Object> params) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <T> List<T> findByNativeQuery(String queryString, Object[] params,
			RowMapper<T> rowMapper) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> findByNativeQuery(String queryString, Object[] params,
			int start, int pageSize, RowMapper<T> rowMapper) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> Pagination<T> findByNativeQuery(String queryString,
			Object[] params, int start, int pageSize, boolean withGroupby,
			RowMapper<T> rowMapper) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> findByNativeQuery(String queryString, Object[] params,
			Sort[] sorts, RowMapper<T> rowMapper) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> findByNativeQuery(String queryString, Object[] params,
			Sort[] sorts, int start, int pageSize, RowMapper<T> rowMapper) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> Pagination<T> findByNativeQuery(String queryString,
			Object[] params, Sort[] sorts, int start, int pageSize,
			boolean withGroupby, RowMapper<T> rowMapper) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T findOneByNativeQuery(String queryString, Object[] params,
			RowMapper<T> rowMapper, Sort[] sorts) {

		return null;
	}

	@Override
	public int batchUpdateByNativeQuery(String queryString, Object[] params,
			Class<?>[] types) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void executeDDL(String queryString) {
		// TODO Auto-generated method stub

	}

	@Override
	public Map<String, Object> executeSP(String spName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> executeSp(String spName,
			SqlParameter[] sqlParameters, Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub

	}

	@Override
	public <T> void evict(T model) {
		// TODO Auto-generated method stub

	}

}
