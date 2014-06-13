package org.hbhk.aili.hibernate.server.dao;

import org.hibernate.Session;

public interface AiliDaoCallback<T> {
	
	T doInHibernate(Session session);
}
