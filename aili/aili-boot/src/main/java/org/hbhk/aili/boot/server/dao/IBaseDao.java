package org.hbhk.aili.boot.server.dao;

import java.io.Serializable;
import java.util.Map;

import org.hbhk.aili.boot.server.support.Page;
import org.hbhk.aili.boot.server.support.Pagination;
import org.hbhk.aili.boot.server.support.Sort;
import org.springframework.data.repository.CrudRepository;
/**
 * 
 * @Description: mybatis增强处理
 * @author 何波
 * @date 2015年3月11日 上午10:05:24 
 *
 */
public interface IBaseDao<T, PK extends Serializable> extends CrudRepository<T, PK> {

	

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
