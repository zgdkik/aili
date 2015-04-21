package org.hbhk.aili.hibernate.server.service;

import java.io.Serializable;
import java.util.Map;

import org.hbhk.aili.hibernate.share.model.Pagination;
import org.hbhk.aili.hibernate.share.model.Sort;

public interface IDaoService {
	<T> T getByPrimaryKey(Class<T> clazz, Serializable pk);
	<T> T save(T model);
	<T> T update(T model);
	<T> void delete(T model);
	<T> void deleteByPrimaryKey(Class<T> clazz, Serializable pk);


	public <T> Pagination<T> findByNamedQuery(String queryName,
			Map<String, Object> params, Sort[] sorts, int start, int pageSize, boolean withGroupby) ;

	void executeDDL(String queryString);

	void flush();

	<T> void evict(T model);
}
