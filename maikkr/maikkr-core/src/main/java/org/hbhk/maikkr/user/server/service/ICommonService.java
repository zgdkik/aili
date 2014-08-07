package org.hbhk.maikkr.user.server.service;

import java.util.List;

public interface ICommonService<T> {

	T save(T o);

	T update(T o);

	List<T> get(T o);

	T getOne(T o);

}
