package org.hbhk.aili.client.core.widget.qtable.model;

import java.util.List;


public interface Data<T> {
	void add(T object);
	void insert(int index, T object);
	void remove(int i);
	int size();
	T get(int i);
	List<T> toList();
}
