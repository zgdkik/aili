package com.feisuo.sds.user.server.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.codec.digest.DigestUtils;
import org.hbhk.aili.core.share.ex.BusinessException;
import org.mybatis.spring.support.Page;
import org.mybatis.spring.support.Pagination;
import org.mybatis.spring.support.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.feisuo.sds.base.server.context.UserContext;
import com.feisuo.sds.base.share.entity.IUser;
import com.feisuo.sds.base.share.vo.QueryPageVo;
import com.feisuo.sds.common.util.UuidUtil;
import com.feisuo.sds.user.server.cache.InvalidateAuthCache;
import com.feisuo.sds.user.server.dao.IUserDao;
import com.feisuo.sds.user.server.service.IUserRoleService;
import com.feisuo.sds.user.server.service.IUserService;
import com.feisuo.sds.user.share.entity.UserEntity;
import com.feisuo.sds.user.share.vo.CurrentUserVo;

@Service
@Transactional
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
	public int updateStatusById(String id, int status) {
		int count = userDao.updateStatusById(id, status);
		return count;
	}
	
	/**
	 * 查询用户信息和用户拥有的角色
	 */
	@Override
	@Transactional(readOnly=true)
	public UserEntity queryUserInfoById(String id){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", id);
		UserEntity user = userDao.getUserInfoById(map);
		return user;
	}
	
	@Override
	@Transactional(readOnly=true)
	public Pagination<UserEntity> getPage(QueryPageVo queryPageVo) {
		Pagination<UserEntity> pageInfo  = userDao.queryUserInfoByPage(queryPageVo.getPage(),queryPageVo.getParaMap());
		if (!"admin".equals(UserContext.getCurrentUser().getName())) {
			for (int i = 0; i < pageInfo.getList().size(); i++) {
				if ("admin".equals(pageInfo.getList().get(i).getUserName())) {
					pageInfo.getList().remove(pageInfo.getList().get(i));
				}
			}
		}
		return pageInfo;
	}
	
	@Override
	@Transactional(readOnly=true)
	public boolean checkUserName(String userName) {
		List<UserEntity> list = userDao.checkUserName(userName);
		return list.size()<1;
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<UserEntity> getUserByUserName(String userName) {
		Map<String, Object> map = new HashMap<String,Object>();
		//要求用户状态为只有在3的时候不能登录1,2,4的都能登录
		List<UserEntity> list = userDao.checkUserName(userName);
		return list;
	}
	
	@Override
	public int deleteById(String id) {
		UserEntity user = userDao.getById(id);
		userDao.deleteUserById(id);
		int count = rolereService.deleteRoleReByUserName(user.getUserName());
		InvalidateAuthCache.invalidateUserCache(user.getUserName());
		return count;
	}
	
	@Override
	public int insert(UserEntity user) {
		String userName = user.getUserName().trim();
		user.setUserName(userName);
		if(!checkUserName(user.getUserName())){
			if(user.getType()==1){
				throw new BusinessException("用户已存在");
			}else{
				throw new BusinessException("账号已存在");
			}
		}
		IUser currentUser = UserContext.getCurrentUser();
		if(currentUser!=null){
			user.setCreateUser(currentUser.getUserName());
			user.setUpdateUser(currentUser.getUserName());
		}
		//给主账户和子账户分配角色
		if(user.getType()==2){
			Set<String> roleids = new HashSet<String>();
			roleids.add("account");
			user.setRoleids(roleids);
		}else if(user.getType()==3){
			Set<String> roleids = new HashSet<String>();
			roleids.add("subaccount");
			user.setRoleids(roleids);
		}
		user.setPassword("ids123456");//设置默认密码
		user.setId(UuidUtil.getUuid());
		user.setFrozenStatus(1);
		user.setBeginTime(new Date());
		user.setUpdateTime(new Date());
		user.setCreateTime(new Date());
		user.setPassword(DigestUtils.md5Hex(user.getPassword()));
		int count = userDao.insert(user);
		if(count>0){
			if(user.getRoleids()!=null&&user.getRoleids().size()>0){
				rolereService.addRoleRe(new ArrayList<String>(user.getRoleids()),user.getUserName());
			}
		}
		return count;
	}

	@Override
	public int update(UserEntity user) {
		int count = rolereService.deleteRoleReByUserName(user.getUserName());
		if(user.getRoleids()!=null){
			rolereService.addRoleRe(new ArrayList<String>(user.getRoleids()), user.getUserName());
		}
		InvalidateAuthCache.invalidateUserCache(user.getUserName());
		return count;
	}
	/**
	 * 根据用户名禁用登录账号
	 */
	@Override
	public int lockByName(String userName) {
		IUser currentUser = UserContext.getCurrentUser();
		Map<String,Object> map = new HashMap<String,Object>();
		int num = 0;
		if(currentUser!=null){
			map.put("updateUser", currentUser.getUserName());
			map.put("updateTime", new Date());
			map.put("userName", userName);
			map.put("status", 2);
			num = userDao.updateStatusByUserName(map);
			
		}
		return num;
	}
	/**
	 * 根据登录名恢复被禁用账号
	 */
	@Override
	public int unLockByName(String userName) {
		IUser currentUser = UserContext.getCurrentUser();
		Map<String,Object> map = new HashMap<String,Object>();
		int num = 0;
		if(currentUser!=null){
			map.put("updateUser", currentUser.getUserName());
			map.put("updateTime", new Date());
			map.put("userName", userName);
			map.put("status", 1);
			num = userDao.updateStatusByUserName(map);
		}
		return num;
	}
	
	/**
	 * 根据用户名冻结账号
	 */
	@Override
	public int frozenByNames(List<String> userNames) {
		IUser currentUser = UserContext.getCurrentUser();
		Map<String,Object> map = new HashMap<String,Object>();
		int num = 0;
		if(currentUser!=null){
			map.put("updateUser", currentUser.getUserName());
			map.put("updateTime", new Date());
			map.put("userNames", userNames);
			map.put("frozenStatus", 4);
			num = userDao.frozenByNames(map);
		}
		return num;
	}
	/**
	 * 根据用户名恢复被冻结账号
	 */
	@Override
	public int unFrozenByNames(List<String> userNames) {
		IUser currentUser = UserContext.getCurrentUser();
		Map<String,Object> map = new HashMap<String,Object>();
		int num = 0;
		if(currentUser!=null){
			map.put("updateUser", currentUser.getUserName());
			map.put("updateTime", new Date());
			map.put("userNames", userNames);
			map.put("frozenStatus", 1);
			num = userDao.unFrozenByNames(map);
		}
		return num;
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
		}else {
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
	public int updateUser(UserEntity user) {
		if(user == null){
			throw new BusinessException("参数不能为空");
		}
		InvalidateAuthCache.invalidateUserCache(user.getUserName());
		return userDao.update(user);
	}

}
