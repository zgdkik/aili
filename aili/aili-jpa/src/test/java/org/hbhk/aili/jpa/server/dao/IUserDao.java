package org.hbhk.aili.jpa.server.dao;

import org.hbhk.aili.jpa.server.model.UserInfo;
import org.springframework.data.repository.CrudRepository;


public interface IUserDao extends  CrudRepository<UserInfo, Long> {



	
}
