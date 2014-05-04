package org.hbhk.dao.impl;

import org.hbhk.dao.IESBTest;
import org.hbhk.domain.User;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class ESBTestImpl extends HibernateDaoSupport implements IESBTest {

	public boolean insertMsg(String msg) {

		User user =  new User();
		user.setEmail(msg);
		getHibernateTemplate().save(user);
		return false;
	}

}
