package org.hbhk.aili.hibernate.server.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import org.hbhk.aili.hibernate.server.dao.IBaseDao;
import org.hbhk.aili.hibernate.server.service.IDaoService;
import org.hbhk.aili.hibernate.share.model.BaseModel;
import org.hbhk.aili.hibernate.share.model.Pagination;
import org.hbhk.aili.hibernate.share.model.Sort;
import org.hbhk.aili.hibernate.share.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseDao<T extends BaseModel, PK extends Serializable>  implements IBaseDao<T, PK>{

	private Class<T> modelClass;
	
	@Autowired
	protected IDaoService daoService;
	
	@SuppressWarnings("unchecked")
	public BaseDao() {
		Class<?> c = this.getClass();  
        Type t = c.getGenericSuperclass();  
        if (t instanceof ParameterizedType) {  
            this.modelClass = (Class<T>) ((ParameterizedType) t)  
                    .getActualTypeArguments()[0];  
        }  
	}
	@Override
	public T insert(T t) {
		return daoService.save(t);
	}

	@Override
	public T update(T t) {
		return daoService.update(t);
	}

	@Override
	public T getById(PK id) {
		return daoService.getByPrimaryKey(modelClass, id);
	}

	@Override
	public List<T> get(Map<String, Object> params) {
		return daoService.getList(params, null, 0, 0, false,modelClass);
	}

	@Override
	public List<T> getPage(Map<String, Object> params, int pageNum, int pageSize) {
		return daoService.getList(params, null, 0, 0, false,modelClass);
	}

	@Override
	public int getPageTotalCount(Map<String, Object> params) {
		return 0;
	}

	@Override
	public int deleteById(PK id) {
		return 0;
	}

	@Override
	public int updateStatusById(PK id, int status) {
		return 0;
	}

	@Override
	public Pagination<T> findPage(Map<String, Object> params, Page page,
			Sort... sorts) {
		return daoService.findPage(params, sorts, page.getStart(), page.getPageSize(), false,modelClass);
	}

}
