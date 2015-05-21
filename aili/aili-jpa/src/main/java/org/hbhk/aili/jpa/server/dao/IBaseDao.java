package org.hbhk.aili.jpa.server.dao;

import java.util.List;
import java.util.Map;

import org.hbhk.aili.jpa.server.support.Page;
import org.hbhk.aili.jpa.server.support.Pagination;
import org.hbhk.aili.jpa.server.support.Sort;
import org.hbhk.aili.jpa.share.model.BaseModel;
/**
 * 
 * @Description: mybatis增强处理
 * @author 何波
 * @date 2015年3月11日 上午10:05:24 
 *
 */
public interface IBaseDao<T extends BaseModel, PK> {

	int insert(T t);

	int update(T t);

	T getById( PK id);
	
	List<T> get(Map<String, Object> params);
	
	/**
	 * 
	* @author 何波
	* @Description: 分页查询对应数据
	* @param params
	* @param pageNum
	* @param pageSize
	* @return   
	* List<T>   
	* @throws
	 */
	List<T> getPage(Map<String, Object> params, int pageNum, int pageSize);
	
	int getPageTotalCount(Map<String, Object> params);
	
	int deleteById( PK id);

	int updateStatusById( PK id, int status);
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
	/**
	 * 
	* @author 何波
	* @Description: 编写对应mapper映射文件
	* @param params
	* @param page
	* @return   
	* Pagination<UserInfo>   
	* @throws
	 */
	<MT> Pagination<MT> getCustomPagination(Map<String, Object> params, Page page,Sort... sorts);

}
