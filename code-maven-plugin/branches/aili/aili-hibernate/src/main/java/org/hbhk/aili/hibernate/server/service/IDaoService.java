package org.hbhk.aili.hibernate.server.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hbhk.aili.hibernate.share.model.Pagination;
import org.hbhk.aili.hibernate.share.model.Sort;
import org.springframework.transaction.annotation.Transactional;


public interface IDaoService {
	<T> T getByPrimaryKey(Class<T> clazz, Serializable pk);
	@Transactional
	<T> T saveOrUpdate(T model);
	@Transactional
	<T> void delete(T model);
	@Transactional
	<T> void deleteByPrimaryKey(Class<T> clazz, Serializable pk);

	<T> Pagination<T> findByNamedQuery(String queryName,Map<String, Object> params, Sort[] sorts, 
			int start, int pageSize, boolean withGroupby) ;
	
    <T> Pagination<T> findPage(Map<String, Object> params, Sort[] sorts,
			int pageNum, int pageSize, boolean withGroupby, Class<T> entityClass) ;
    
	<T> List<T> getList(Map<String, Object> params, Sort[] sorts,
			int start, int pageSize, boolean withGroupby,Class<T> entityClass);
	@Transactional
	void executeDDL(String ddl);

	void flush();

	<T> void evict(T model);
}
