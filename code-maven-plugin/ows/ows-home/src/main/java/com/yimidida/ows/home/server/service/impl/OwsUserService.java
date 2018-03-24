package com.yimidida.ows.home.server.service.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.Page;
import org.mybatis.spring.support.Pagination;
import org.mybatis.spring.support.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yimidida.ows.home.server.dao.IOwsUserDao;
import com.yimidida.ows.home.server.service.IOwsUserService;
import com.yimidida.ows.home.share.entity.OwsUser;

/**
 * 个人信息的 业务层
 */
@Service
public class OwsUserService implements IOwsUserService {
	@Autowired
	IOwsUserDao owsUserDao;

	@Override
	public int insert(OwsUser t) {
		return owsUserDao.insert(t);
	}

	@Override
	public int update(OwsUser t) {
		return owsUserDao.update(t);
	}

	@Override
	public OwsUser getById(String id) {
		return owsUserDao.getById(id);
	}

	@Override
	public List<OwsUser> get(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return owsUserDao.get(params);
	}

	@Override
	public List<OwsUser> getPage(Map<String, Object> params, int pageNum, int pageSize) {
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
	public Pagination<OwsUser> getPagination(Map<String, Object> params, Page page, Sort... sorts) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int changePassWord(String id, String newPassWord) {
		return owsUserDao.changePassWord(id, newPassWord);
	}

	@Override
	public int changeEmail(OwsUser o) {
			return owsUserDao.changeEmail(o);
	}

	
	public OwsUser findUserInfoByLoginName(String userName) {
		try {
			return owsUserDao.queryfindUserInfoByLoginName(userName).get(0);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public boolean modifyMailTime(Map map) {
		try {
			return owsUserDao.modifyMailTime(map)>0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public int updateDetailsByuserId(OwsUser o) {
		return owsUserDao.updateDetailsByuserId(o);
	}
	
	@Override
	public OwsUser checkUserName(String userName) {
		try {
			return owsUserDao.querycheckUserName(userName);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public boolean modifyPassword(Map<String, String> map) {
		try {
			return owsUserDao.modifyPassword(map)>0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public void updatePhoneById(OwsUser o) {
		owsUserDao.updatePhoneById(o);
	}

	@Override
	public void updateMemberCardById(OwsUser user) {
		owsUserDao.updateMemberCardById(user);
	}
}
