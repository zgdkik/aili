package org.hbhk.aili.hibernate.server.dao;

import java.io.Serializable;
import java.util.List;

import org.hbhk.aili.hibernate.share.model.Page;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;

public interface ICommonDao<T, PK extends Serializable> {

	public void save(T entity);

	public void delete(T entity);

	public void delete(PK id);

	public List<T> findAll();

	public Page findAll(Page page);

	/**
	 * 按id获取对象.
	 */
	public T get(final PK id);

	/**
	 * 按HQL查询对象列表.
	 * 
	 * @param hql
	 *            hql语句
	 * @param values
	 *            数量可变的参数
	 */
	public List<T> find(String hql, Object... values);

	/**
	 * 按HQL分页查询. 暂不支持自动获取总结果数,需用户另行执行查询.
	 * 
	 * @param page
	 *            分页参数.包括pageSize 和firstResult.
	 * @param hql
	 *            hql语句.
	 * @param values
	 *            数量可变的参数.
	 * 
	 * @return 分页查询结果,附带结果列表及所有查询时的参数.
	 */
	public Page find(Page page, String hql, Object... values);

	/**
	 * 按HQL查询唯一对象.
	 */
	public Object findUnique(String hql, Object... values);

	/**
	 * 按HQL查询Intger类形结果.
	 */
	public Integer findInt(String hql, Object... values);

	/**
	 * 按HQL查询Long类型结果.
	 */
	public Long findLong(String hql, Object... values);

	/**
	 * 按Criterion查询对象列表.
	 * 
	 * @param criterion
	 *            数量可变的Criterion.
	 */
	public List<T> findByCriteria(Criterion... criterion);

	/**
	 * 按Criterion分页查询.
	 * 
	 * @param page
	 *            分页参数.包括pageSize、firstResult、orderBy、asc、autoCount.
	 *            其中firstResult可直接指定,也可以指定pageNo. autoCount指定是否动态获取总结果数.
	 * 
	 * @param criterion
	 *            数量可变的Criterion.
	 * @return 分页查询结果.附带结果列表及所有查询时的参数.
	 */
	public Page findByCriteria(Page page, Criterion... criterion);

	/**
	 * 按属性查找对象列表.
	 */
	public List<T> findByProperty(String propertyName, Object value);

	/**
	 * 按属性查找唯一对象.
	 */
	public T findUniqueByProperty(String propertyName, Object value);

	/**
	 * 根据查询函数与参数列表创建Query对象,后续可进行更多处理,辅助函数.
	 */
	public Query createQuery(String queryString, Object... values);

	/**
	 * 根据Criterion条件创建Criteria,后续可进行更多处理,辅助函数.
	 */
	public Criteria createCriteria(Criterion... criterions);

	/**
	 * 判断对象的属性值在数据库内是否唯一.
	 * 
	 */
	public boolean isPropertyUnique(String propertyName, Object newValue,
			Object orgValue);

	/**
	 * 通过count查询获得本次查询所能获得的对象总数.
	 * 
	 * @return page对象中的totalCount属性将赋值.
	 */
	public int countQueryResult(Page page, Criteria c);
}
