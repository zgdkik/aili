package org.hbhk.aili.mybatis.server.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.hbhk.aili.mybatis.server.support.Page;
import org.hbhk.aili.mybatis.server.support.Pagination;
import org.hbhk.aili.mybatis.server.support.Sort;

public interface IBaseService<T,PK> {
	T insert(T t);
	/**
	 * 插入数据并返回数据库生成id 目前只支持mysql
	 * @param t
	 * @return
	 */
	T insertWithId(T t);

	/**
	 * 根据id修改数据
	 * @param t
	 * @return
	 */
	T update(T t);
	/**
	 * 根据id获取数据
	 * @param id
	 * @return
	 */
	T getById(@Param("id") PK id);
	
	/**
	 * 动态查询单表数据
	 * @param params
	 * @return
	 */
	List<T> get(Map<String, Object> params);
	
	List<T> getByObj(T t);
	/**
	 * 
	* @author 何波
	* @Description: 分页单表查询对应数据
	* @param params
	* @param pageNum
	* @param pageSize
	* @return   
	* List<T>   
	* @throws
	 */
	List<T> getPage(Map<String, Object> params, int pageNum, int pageSize);
	
	/**
	 * 根据id删除数据
	 * @param id
	 * @return
	 */
	int deleteById(PK id);
	
	int deleteByObj(T obj);
	/**
	 * 
	* @author 何波
	* @Description: 注解方式,不需要编写对应mapper，且只能是本dao单表
	* @param params
	* @param page
	* @return   
	* Pagination<UserInfo>   
	* @throws
	 */
	Pagination<T> getPagination(Map<String, Object> params, Page page,Sort... sorts);
}
