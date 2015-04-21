package org.hbhk.aili.hibernate.server.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.persistence.PersistenceException;

import org.hbhk.aili.hibernate.server.service.IDaoService;
import org.hbhk.aili.hibernate.share.model.Pagination;
import org.hbhk.aili.hibernate.share.model.Sort;
import org.hbhk.aili.hibernate.share.utils.StringUtil;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.engine.internal.ForeignKeys;
import org.hibernate.engine.spi.EntityEntry;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.engine.spi.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DaoService implements IDaoService {

	protected final Logger logger = LoggerFactory.getLogger(DaoService.class);
	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getByPrimaryKey(Class<T> clazz, Serializable pk) {
		Session session = getSession();
		return (T) session.get(clazz, pk);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T save(T model) {
		Session session = getSession();
		if (EntityStatus.TRANSIENT == getStatus(model)) {
			session.persist(model);
			return model;
		} else {
			return (T) session.merge(model);
		}
	}

	@Override
	public <T> T update(T model) {
		return null;
	}

	@Override
	public <T> void delete(T model) {
		Session session = getSession();
		session.delete(model);
	}

	@Override
	public <T> void deleteByPrimaryKey(Class<T> clazz, Serializable pk) {
		T entity = getByPrimaryKey(clazz, pk);
		if (entity != null) {
			Session session = getSession();
			session.delete(entity);
		} else {
			throw new PersistenceException(
					"The entity you want to delete is not existed.");
		}

	}


	@SuppressWarnings("unchecked")
	private <T> List<T> findByQueryNative(Query query, Map<String,Object> params, int start, int pageSize){
		if(params != null && !params.keySet().isEmpty()){
			logger.debug("Parameter List:");
			int i=1;
			for(String key: params.keySet()){
				logger.debug("{}) [{}] : {}", new Object[]{i++, key, params.get(key)});
				if(params.get(key) instanceof Collection<?>){
					query.setParameterList(key,(Collection<?>)params.get(key));
				}else
					query.setParameter(key, params.get(key));
			}
		}
		if(start > 0){
			query.setFirstResult(start);
		}
		if(pageSize > 0){
			query.setMaxResults(pageSize);
		}
		return query.list();
	}

	private String getCountQueryStringForHql(String hql){
		if(hql == null) return null;
		hql = hql.trim();
        String lowercaseOQL = hql.toLowerCase();
        int delim1 = lowercaseOQL.indexOf("from");        
        if(delim1 <0){
        	if(logger.isDebugEnabled()){
        		logger.debug("It seemed that current hql is not one valid one.");
        		logger.debug("HQL:{}",hql);
        	}
        	return null;
        }
        String fieldlist = hql.substring(7,delim1-1);
        int delim3 = fieldlist.indexOf(",");
        if(delim3 == -1) delim3 = fieldlist.length();
        String countOQL =  "select count(" + fieldlist.substring(0,delim3) + ") " + hql.substring(delim1,hql.length());
        logger.debug("Count OQL:" +  countOQL);
        return countOQL;
	}
	
	protected <T> Pagination<T> setPagination(Pagination<T> p, int start, int pageSize, Sort[] sorts){
		if(pageSize == 0) throw new IllegalArgumentException();
		p.setCurrentPage((start/pageSize) + 1);
		p.setTotalPages((int)p.getCount()/pageSize + (p.getCount()%pageSize == 0 ? 0 : 1));
		p.setStart(start);
		p.setPageSize(pageSize);
		StringBuilder sortStr = new StringBuilder();
		if(sorts != null){
			for(Sort sort: sorts){
				sortStr.append("," + sort.getField() + " " + sort.getType());
			}			
		}
		String s = sortStr.toString();
		p.setSortStr(s.length() == 0 ? null : s.substring(1));
		return p;
	}
	
	protected <T> Pagination<T> findByQueryNative(String query, Map<String,Object> params, 
			Sort[] sorts, int start, int pageSize, boolean withGroupby){
		Session session = getSession();
		Pagination<T> p = new Pagination<T>();
		List<T> list = findByQueryNative(query, params, sorts, start, pageSize);
		p.setDatas(list);
		String countQueryString = getCountQueryStringForHql(query);
		if(withGroupby)
			p.setCount((int)findByQueryNative(session.createQuery(countQueryString), params, -1, -1).size());
		else
			p.setCount((Integer)findByQueryNative(session.createQuery(countQueryString), params, -1, -1).iterator().next());
		return setPagination(p, start, pageSize, sorts);
	}	
	
	protected <T> List<T> findByQueryNative(String query, Map<String,Object> params, Sort[] sorts, int start, int pageSize){		
		Session session = getSession();
		if(sorts != null && sorts.length > 0){
			query += " order by " + StringUtil.join(sorts);
		}
		logger.debug("Find[{}]", query);
		return findByQueryNative(session.createQuery(query), params, start, pageSize);
	}
	
	@Override
	public <T> Pagination<T> findByNamedQuery(String queryName,
			Map<String, Object> params, Sort[] sorts, int start, int pageSize,
			boolean withGroupby) {
		Session session = getSession();
		Query query = session.getNamedQuery(queryName);		
		String queryString = query.getQueryString();		
		if(sorts == null || sorts.length == 0){
			logger.debug("Find[{}]", queryString);
			Pagination<T> p = new Pagination<T>();
			List<T> list = findByQueryNative(query, params,  start, pageSize);
			p.setDatas(list);	
			String countQueryString = getCountQueryStringForHql(queryString);
			if(withGroupby){
				p.setCount((int)findByQueryNative(session.createQuery(countQueryString), params, -1, -1).size());
			}else{
				p.setCount((Integer)findByQueryNative(session.createQuery(countQueryString), params, -1, -1).iterator().next());
			}
			return setPagination(p, start, pageSize, sorts);
		}else{
			return findByQueryNative(queryString, params, sorts, start, pageSize, withGroupby);
		}		
	}
	@Override
	public void executeDDL(String queryString) {
		Session session = getSession();
		SQLQuery query = session.createSQLQuery(queryString);
		query.executeUpdate();
	}


	@Override
	public void flush() {
		getSession().flush();
	}

	@Override
	public <T> void evict(T model) {
		if(model != null)
			getSession().evict(model);
		else
			getSession().clear();
	}

	private EntityStatus getStatus(Object model) {
		SessionImplementor simpl = (SessionImplementor) getSession();
		EntityEntry entry = simpl.getPersistenceContext().getEntry(model);
		if (entry != null) {
			// Persistent Object
			logger.debug(
					"current {} is one Entity with entry in PersistenceContext.",
					model);
			if (entry.getStatus() != Status.DELETED) {
				logger.debug("EntityStatus: {}", EntityStatus.PERSISTENT);
				return EntityStatus.PERSISTENT;
			} else {
				logger.debug("EntityStatus: {}", EntityStatus.REMOVED);
				return EntityStatus.REMOVED;
			}
		} else {
			// Detached or Transient Object
			logger.debug(
					"current {} is one Entity without entry in PersistenceContext.",
					model);
			if (ForeignKeys.isTransient(null, model, null, simpl)) {
				logger.debug("EntityStatus: {}", EntityStatus.TRANSIENT);
				return EntityStatus.TRANSIENT;
			} else {
				logger.debug("EntityStatus: {}", EntityStatus.DETACHED);
				return EntityStatus.DETACHED;
			}
		}
	}

	protected static enum EntityStatus {
		TRANSIENT, PERSISTENT, DETACHED, REMOVED
	}

}
