package org.hbhk.aili.hibernate.server.support;

import java.io.Serializable;
import java.util.List;

import org.hbhk.aili.hibernate.server.service.IDaoService;
import org.hbhk.aili.hibernate.server.service.impl.DaoService;
import org.hbhk.aili.hibernate.share.model.BaseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseDaoWrapper {
	protected static final Logger logger = LoggerFactory.getLogger(BaseDaoWrapper.class);

	protected IDaoService daoService;
	
	public BaseDaoWrapper(){}
	
	public BaseDaoWrapper(IDaoService daoService){this.daoService = daoService;}
	
	public Object saveOrUpdate(BaseModel model) {
		return daoService.save(model);
	}
	
	public void delete(BaseModel model) {
		daoService.delete(model);
	}

	public void deleteAll(List<BaseModel> models) {
		for (BaseModel model : models) {
			delete(model);
		}
	}
	
	public void deleteByPrimaryKey(Class<?> modelClass, Serializable id) {
		daoService.deleteByPrimaryKey(modelClass, id);
	}

	public void deleteAllByPrimaryKey(Class<?> modelClass, List<Serializable> ids) {
		for(Serializable id: ids)
			deleteByPrimaryKey(modelClass, id);
	}

	public Object getById(Class<?> modelClass, Serializable id) {
		return daoService.getByPrimaryKey(modelClass, id);
	}

	public void setDaoService(DaoService daoService) {
		this.daoService = daoService;
	}

	public void executeDDL(String ddl) {
		daoService.executeDDL(ddl);
	}

	
	public void flush(){
		daoService.flush();
	}
	
	public void evict(Object model){
		daoService.evict(model);
	}
}