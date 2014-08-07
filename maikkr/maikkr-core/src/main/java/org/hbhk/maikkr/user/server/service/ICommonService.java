package org.hbhk.maikkr.user.server.service;

import java.util.List;

public interface ICommonService<T> {

	T save(T model);

	T update(T model);

	List<T> get(T model);

	T getOne(T model);

}
