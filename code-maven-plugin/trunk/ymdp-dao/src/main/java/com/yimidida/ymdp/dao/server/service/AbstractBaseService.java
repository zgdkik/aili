package com.yimidida.ymdp.dao.server.service;

import java.util.List;
import java.util.Map;

import com.yimidida.ymdp.dao.server.IBaseDao;
import com.yimidida.ymdp.dao.server.support.Page;
import com.yimidida.ymdp.dao.server.support.Pagination;
import com.yimidida.ymdp.dao.server.support.Sort;

public abstract class AbstractBaseService<T, PK> {

	protected IBaseDao<T, PK> baseDao;

	AbstractBaseService(IBaseDao<T, PK> baseDao) {
		this.baseDao = baseDao;
	}

	int insert(T t) {
		return baseDao.insert(t);
	}

	/**
	 * 根据id修改数据
	 * 
	 * @param t
	 * @return
	 */
	int update(T t) {
		return baseDao.update(t);
	}

	/**
	 * 根据id获取数据
	 * 
	 * @param id
	 * @return
	 */
	T getById(PK id) {
		return baseDao.getById(id);
	}

	/**
	 * 动态查询单表数据
	 * 
	 * @param params
	 * @return
	 */
	List<T> get(Map<String, Object> params) {
		return baseDao.get(params);
	}

	List<T> getByObj(T t) {
		return baseDao.getByObj(t);
	}

	/**
	 * 
	 * @author 何波
	 * @Description: 分页单表查询对应数据
	 * @param params
	 * @param pageNum
	 * @param pageSize
	 * @return List<T>
	 * @throws
	 */
	List<T> getPage(Map<String, Object> params, int pageNum, int pageSize) {
		return baseDao.getPage(params, pageNum, pageSize);
	}

	/**
	 * 根据id删除数据
	 * 
	 * @param id
	 * @return
	 */
	int deleteById(PK id) {
		return baseDao.deleteById(id);
	}

	int deleteByObj(T obj) {
		return baseDao.deleteByObj(obj);
	}

	/**
	 * 
	 * @author 何波
	 * @Description: 注解方式,不需要编写对应mapper，且只能是本dao单表
	 * @param params
	 * @param page
	 * @return Pagination<UserInfo>
	 * @throws
	 */
	Pagination<T> getPagination(Map<String, Object> params, Page page,
			Sort... sorts) {
		return baseDao.getPagination(params, page, sorts);
	}
}
