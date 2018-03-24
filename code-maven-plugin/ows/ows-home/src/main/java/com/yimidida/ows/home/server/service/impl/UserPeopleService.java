package com.yimidida.ows.home.server.service.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.Page;
import org.mybatis.spring.support.Pagination;
import org.mybatis.spring.support.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yimidida.ows.home.server.dao.IUserPeopleDao;
import com.yimidida.ows.home.server.service.IUserPeopleService;
import com.yimidida.ows.home.share.entity.UserPeople;

/**
 * 收寄货人信息地址  业务层
 * @author zhangm
 *
 */
@Service
public class UserPeopleService implements IUserPeopleService{

	@Autowired
	private IUserPeopleDao userPeopleDao;
	
	
	@Override
	public int insert(UserPeople t) {
		userPeopleDao.insert(t);
		return 0;
	}

	@Override
	public int update(UserPeople t) {
		userPeopleDao.update(t);
		return 0;
	}

	@Override
	public UserPeople getById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserPeople> get(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserPeople> getPage(Map<String, Object> params, int pageNum, int pageSize) {
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
		return userPeopleDao.deleteById(id);
	}

	@Override
	public int updateStatusById(String id, int status) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Pagination<UserPeople> getPagination(Map<String, Object> params, Page page, Sort... sorts) {
		// TODO Auto-generated method stub
		return null;
	}

	//根据类型查询寄货人还是收货人
	@Override
	public List<UserPeople> getByType(Map<String, Object> param) {
		return userPeopleDao.getByType(param);
	}

}
