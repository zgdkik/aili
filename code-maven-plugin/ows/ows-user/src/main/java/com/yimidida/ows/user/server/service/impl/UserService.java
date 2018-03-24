package com.yimidida.ows.user.server.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.mybatis.spring.support.Page;
import org.mybatis.spring.support.Pagination;
import org.mybatis.spring.support.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yimidida.ows.base.share.vo.QueryPageVo;
import com.yimidida.ows.common.util.UuidUtil;
import com.yimidida.ows.user.server.cache.InvalidateAuthCache;
import com.yimidida.ows.user.server.dao.IUserDao;
import com.yimidida.ows.user.server.service.IUserRoleService;
import com.yimidida.ows.user.server.service.IUserService;
import com.yimidida.ows.user.share.entity.UserEntity;
import com.yimidida.ows.user.share.vo.CurrentUserVo;
import com.yimidida.ymdp.core.share.ex.BusinessException;

@Service
public class UserService implements IUserService {

	@Autowired
	private IUserDao userDao;

	@Autowired
	private IUserRoleService rolereService;

	@Override
	public List<CurrentUserVo> getCurrentUserVos(String userName) {
		return null;
	}

	@Override
	@Transactional
	public int updateStatusById(String id, int status) {
		int count = userDao.updateStatusById(id, status);
		return count;
	}

	/**
	 * 查询用户信息和用户拥有的角色
	 */
	@Override
	public UserEntity queryUserInfoById(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		UserEntity user = userDao.getUserInfoById(map);
		return user;
	}

	@Override
	public Pagination<UserEntity> getPage(QueryPageVo queryPageVo) {
		Pagination<UserEntity> pageInfo = userDao.getPagination(
				queryPageVo.getParaMap(), queryPageVo.getPage());
		return pageInfo;
	}

	@Override
	public boolean checkUserName(String userName) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userName", userName);
		List<UserEntity> list = userDao.get(params);
		return list.size() < 1;
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserEntity> getUserByUserName(String userName) {
		// 要求用户状态为只有在3的时候不能登录1,2,4的都能登录
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userName", userName);
		List<UserEntity> list = userDao.get(params);
		return list;
	}

	@Override
	@Transactional
	public int deleteById(String id) {
		UserEntity user = userDao.getById(id);
		userDao.deleteById(id);
		int count = rolereService.deleteRoleReByUserName(user.getUserName());
		InvalidateAuthCache.invalidateUserCache(user.getUserName());
		return count;
	}

	@Override
	@Transactional
	public int insert(UserEntity user) {
		String userName = user.getUserName().trim();
		user.setUserName(userName);
		//IUser currentUser = UserContext.getCurrentUser();
		// if (currentUser != null) {
		// user.setCreateUser(currentUser.getUserName());
		// user.setUpdateUser(currentUser.getUserName());
		// }
		user.setPassword("ids123456");// 设置默认密码
		user.setId(UuidUtil.getUuid());
		// user.setUpdateTime(new Date());
		user.setCreateTime(new Date());
		user.setPassword(DigestUtils.md5Hex(user.getPassword()));
		int count = userDao.insert(user);
		return count;
	}

	@Override
	@Transactional
	public int update(UserEntity user) {
		int count = rolereService.deleteRoleReByUserName(user.getUserName());
		// if(user.getRoleids()!=null){
		// rolereService.addRoleRe(new ArrayList<String>(user.getRoleids()),
		// user.getUserName());
		// }
		InvalidateAuthCache.invalidateUserCache(user.getUserName());
		return count;
	}

	@Override
	public UserEntity getById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserEntity> get(Map<String, Object> params) {
		if (params != null && params.size() != 0) {
			return this.userDao.get(params);
		} else {
			return null;
		}
	}

	@Override
	public List<UserEntity> getPage(Map<String, Object> params, int pageNum,
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
	public Pagination<UserEntity> getPagination(Map<String, Object> params,
			Page page, Sort... sorts) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public int updateUser(UserEntity user) {
		if (user == null) {
			throw new BusinessException("参数不能为空");
		}
		InvalidateAuthCache.invalidateUserCache(user.getUserName());
		return userDao.update(user);
	}

}
