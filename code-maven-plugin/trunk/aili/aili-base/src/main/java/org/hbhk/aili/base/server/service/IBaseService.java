package org.hbhk.aili.base.server.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.hbhk.aili.mybatis.server.support.Page;
import org.hbhk.aili.mybatis.server.support.Pagination;
import org.hbhk.aili.mybatis.server.support.Sort;

public interface IBaseService<T, PK> {

	int insert(T t);

	int update(T t);

	T getById(PK id);

	List<T> get(Map<String, Object> params);

	/**
	 * 
	 * @author 何波
	 * @Description: 分页查询对应数据
	 * @param params
	 * @param pageNum
	 * @param pageSize
	 * @return List<T>
	 * @throws
	 */
	List<T> getPage(Map<String, Object> params, int pageNum, int pageSize);

	int getPageTotalCount(Map<String, Object> params);

	int deleteById(@Param("id") PK id);

	int updateStatusById(PK id, int status);

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
			Sort... sorts);
}
