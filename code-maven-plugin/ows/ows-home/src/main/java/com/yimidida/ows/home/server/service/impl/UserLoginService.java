package com.yimidida.ows.home.server.service.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.Page;
import org.mybatis.spring.support.Pagination;
import org.mybatis.spring.support.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yimidida.ows.home.server.dao.IUserLoginDao;
import com.yimidida.ows.home.server.dao.IUserPeopleDao;
import com.yimidida.ows.home.server.service.IUserLoginService;
import com.yimidida.ows.home.server.service.IUserPeopleService;
import com.yimidida.ows.home.share.entity.UserLogin;
import com.yimidida.ows.home.share.entity.UserPeople;

/**
 * 收寄货人信息地址  业务层
 * @author zhangm
 *
 */
@Service
public class UserLoginService implements IUserLoginService{

	@Autowired
	private IUserLoginDao userLoginDao;

	@Override
	public int insert(UserLogin t) {
		return userLoginDao.insert(t);
	}

	@Override
	public int update(UserLogin t) {
		return userLoginDao.update(t);
	}

	@Override
	public UserLogin getById(String id) {
		return userLoginDao.getById(id);
	}

	@Override
	public List<UserLogin> get(Map<String, Object> params) {
		
		return userLoginDao.get(params);
	}

	@Override
	public List<UserLogin> getPage(Map<String, Object> params, int pageNum,
			int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPageTotalCount(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteById(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateStatusById(String id, int status) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Pagination<UserLogin> getPagination(Map<String, Object> params,
			Page page, Sort... sorts) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
