package org.hbhk.aili.security.server.service;

import java.util.List;
import java.util.Map;

import org.hbhk.aili.core.server.service.IBaseService;

public interface IBizBaseService<PK,T> extends IBaseService{

	T save(T t);

	int update(T t);

	T getById(PK id);
	
	List<T> get(Map<String, Object> params);
	
	List<T> get(T obj);


}
