package org.hbhk.aili.hibernate.server.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.hbhk.aili.hibernate.server.dao.AiliDaoCallback;
import org.hbhk.aili.hibernate.share.model.Page;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

/**
 * Hibernate的范型基类.
 * <p>
 * 可以在service类中直接创建使用.也可以继承出DAO子类
 * </p>
 * 修改自Springside SimpleHibernateTemplate
 * 
 * @param <T>
 *            DAO操作的对象类型
 * @param <PK>
 *            主键类型
 * 
 */
public  class AiliDaoSurpport<T, PK extends Serializable> {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	protected SessionFactory sessionFactory;

	protected Class<T> entityClass;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return getSessionFactory().openSession();
	}

	public T execute(AiliDaoCallback<T> action) {
		Assert.notNull(action, "Callback object 对象不能为 Null ");
		Session session = getSession();
		Transaction tr = session.beginTransaction();
		T result = action.doInHibernate(session);
		tr.commit();
		session.close();
		return result;
	}

	public void save(final T entity) {
		Assert.notNull(entity);
		execute(new AiliDaoCallback<T>() {
			@Override
			public T doInHibernate(Session session) {
				session.saveOrUpdate(entity);
				return null;
			}
		});
		logger.debug("save entity: {}", entity);
	}

	public void delete(final T entity) {
		Assert.notNull(entity);
		execute(new AiliDaoCallback<T>() {
			@Override
			public T doInHibernate(Session session) {
				session.delete(entity);
				return null;
			}
			
		});
		logger.debug("delete entity: {}", entity);
	}

	public void delete(PK id) {
		Assert.notNull(id);
		delete(get(id));
	}

	public List<T> findAll() {
		return findByCriteria();
	}

	public Page findAll(Page page) {
		return findByCriteria(page);
	}

	public T get(final PK id) {
		return (T) getSession().get(entityClass, id);
	}

	public List<T> find(String hql, Object... values) {
		return createQuery(hql, values).list();
	}

	@SuppressWarnings("unchecked")
	public Page<T> find(Page<T> page, String hql, Object... values) {
		 Assert.notNull(page);
		
		 Query q = createQuery(hql, values);
		 if (page.getCurrentPageNo() != 0) {
			 q.setFirstResult(page.getCurrentPageNo());
		 }
		 if (page.getPageSize()!=0) {
			 q.setMaxResults(page.getPageSize());
		 }
		 page.setData(q.list());
		return page;
	}

	/**
	 * 按HQL查询唯一对象.
	 */
	public Object findUnique(String hql, Object... values) {
		return createQuery(hql, values).uniqueResult();
	}

	public Integer findInt(String hql, Object... values) {
		return (Integer) findUnique(hql, values);
	}

	public Long findLong(String hql, Object... values) {
		return (Long) findUnique(hql, values);
	}

	public List<T> findByCriteria(Criterion... criterion) {
		return createCriteria(criterion).list();
	}

	public Page findByCriteria(Page page, Criterion... criterion) {
		// Assert.notNull(page);
		//
		// Criteria c = createCriteria(criterion);
		//
		// if (page.isAutoCount()) {
		// page.setTotalCount(countQueryResult(page, c));
		// }
		// if (page.isFirstSetted()) {
		// c.setFirstResult(page.getFirst());
		// }
		// if (page.isPageSizeSetted()) {
		// c.setMaxResults(page.getPageSize());
		// }
		//
		// if (page.isOrderBySetted()) {
		// if (page.getOrder().endsWith(QueryParameter.ASC)) {
		// c.addOrder(Order.asc(page.getOrderBy()));
		// } else {
		// c.addOrder(Order.desc(page.getOrderBy()));
		// }
		// }
		// page.setResult(c.list());
		return page;
	}

	/**
	 * 按属性查找对象列表.
	 */
	public List<T> findByProperty(String propertyName, Object value) {
		Assert.hasText(propertyName);
		return createCriteria(Restrictions.eq(propertyName, value)).list();
	}

	public T findUniqueByProperty(String propertyName, Object value) {
		Assert.hasText(propertyName);
		return (T) createCriteria(Restrictions.eq(propertyName, value))
				.uniqueResult();
	}

	public Query createQuery(String queryString, Object... values) {
		Assert.hasText(queryString);
		getSession().createQuery(queryString);
		Query queryObject = getSession().createQuery(queryString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				queryObject.setParameter(i, values[i]);
			}
		}
		return queryObject;
	}

	public Criteria createCriteria(Criterion... criterions) {
		Criteria criteria = getSession().createCriteria(entityClass);
		for (Criterion c : criterions) {
			criteria.add(c);
		}
		return criteria;
	}

	public boolean isPropertyUnique(String propertyName, Object newValue,
			Object orgValue) {
		if (newValue == null || newValue.equals(orgValue))
			return true;

		Object object = findUniqueByProperty(propertyName, newValue);
		return (object == null);
	}

	public int countQueryResult(Page page, Criteria c) {
		// CriteriaImpl impl = (CriteriaImpl) c;
		//
		// // 先把Projection、ResultTransformer、OrderBy取出来,清空三者后再执行Count操作
		// Projection projection = impl.getProjection();
		// ResultTransformer transformer = impl.getResultTransformer();
		//
		// List<CriteriaImpl.OrderEntry> orderEntries = null;
		// try {
		// orderEntries = (List) BeanUtils.getFieldValue(impl, "orderEntries");
		// BeanUtils.setFieldValue(impl, "orderEntries", new ArrayList());
		// } catch (Exception e) {
		// logger.error("不可能抛出的异常:{}", e.getMessage());
		// }
		//
		// // 执行Count查询
		// int totalCount = (Integer) c.setProjection(Projections.rowCount())
		// .uniqueResult();
		// if (totalCount < 1)
		// return -1;
		//
		// // 将之前的Projection和OrderBy条件重新设回去
		// c.setProjection(projection);
		//
		// if (projection == null) {
		// c.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		// }
		// if (transformer != null) {
		// c.setResultTransformer(transformer);
		// }
		//
		// try {
		// BeanUtils.setFieldValue(impl, "orderEntries", orderEntries);
		// } catch (Exception e) {
		// logger.error("不可能抛出的异常:{}", e.getMessage());
		// }

		return 0;
	}

}