package org.hbhk.aili.hibernate.server.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hbhk.aili.hibernate.share.model.BaseModel;
import org.hbhk.aili.hibernate.share.model.Pagination;
import org.hbhk.aili.hibernate.share.model.Sort;
import org.hbhk.aili.hibernate.share.utils.Page;

public interface IBaseDao<T extends BaseModel, PK extends Serializable> {

	
	T saveOrUpdate(T t);

	T getById(PK id);
	
	int deleteById(PK id);
	
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


	Pagination<T> findPage(Map<String, Object> params, Page page,
			Sort... sorts);
	
	

}
