package org.hbhk.aili.mybatis.server.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.hbhk.aili.mybatis.server.dao.IUserDao;
import org.hbhk.aili.mybatis.server.entity.UserInfo;
import org.hbhk.aili.mybatis.server.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class UserService implements IUserService {

	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private IUserDao userDao;

	@Autowired
	private DataSource dataSource;

	@Override
	public void insert(UserInfo t) {
		if (userDao.insert(t) == 0) {
			throw new RuntimeException();
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserInfo> query(Map<String, Object> params) {
		 return userDao.get(params);
	}

	public void insert2(UserInfo t) {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			connection.setAutoCommit(false);
			PreparedStatement pst = connection.prepareStatement("");
			pst.execute();
			// 提交事务
			connection.commit();
		} catch (Exception e) {
			try {
				// 回滚事务
				connection.rollback();
			} catch (SQLException e1) {
				log.error(e1.getMessage(), e1);
			}
			log.error(e.getMessage(), e);
		}
	}

}
