package org.hbhk.dao.impl;

import java.util.List;

import org.hbhk.dao.UserDAO;
import org.hbhk.domain.User;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class UserDAOImpl  implements UserDAO {

	public User findByEmail(String email) {
		String hql = "from User where email=?";
		/*List<User> list = this.getHibernateTemplate().find(hql,
				new Object[] { email });
		User user = null;
		if (!list.isEmpty()) {
			user = list.get(0);
		}*/
		System.out.println("222222222222222");
		return null;
	}

	public boolean saveUser(String user) {

		User user1 = new User();

		user1.setEmail(user);

		//getHibernateTemplate().save(user1);

		return false;
	}

}
