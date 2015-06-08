package org.hbhk.aili.boot.server.dao;

import org.hbhk.aili.boot.server.model.UserInfo;
import org.springframework.data.repository.CrudRepository;


public interface IUserDao extends  CrudRepository<UserInfo, Long> {



	
}
