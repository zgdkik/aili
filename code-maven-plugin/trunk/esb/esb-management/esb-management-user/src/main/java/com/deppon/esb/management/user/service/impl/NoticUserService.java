package com.deppon.esb.management.user.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.deppon.esb.management.user.dao.INoticUserDao;
import com.deppon.esb.management.user.domain.NoticUserInfo;
import com.deppon.esb.management.user.service.INoticUserService;

/**
 * The Class NoticUserService.
 */
@Service
@Transactional
public class NoticUserService implements INoticUserService {
	
	/** 常量定义 LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(NoticUserService.class);

	/** The notic user dao. */
	@Resource(name="noticUserDao")
	INoticUserDao noticUserDao;

	/** 
	 * 
	 * @author HuangHua
	 * @date 2013-1-6 下午5:25:42
	 * @see com.deppon.esb.management.user.service.INoticUserService#queryNoticUsers(java.lang.String, java.lang.String, java.lang.String, java.lang.String, int, int)
	 */
	@Override
	public Map<String, Object> queryNoticUsers(String userName, String telPhone, String mobilePhone, String email, int start, int limit) {
		List<NoticUserInfo> noticUserList = new ArrayList<NoticUserInfo>();
		Integer resultCount;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userName", userName);
		map.put("telPhone", telPhone);
		map.put("mobilePhone", mobilePhone);
		map.put("email", email);
		resultCount = noticUserDao.queryNoticUserCount(map);
		map.put("start", start);
		map.put("limit", limit);
		noticUserList = noticUserDao.queryNoticUsers(map);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("noticUserInfoList", noticUserList);
		result.put("resultCount", resultCount);
		return result;
	}

	/** 
	 * 
	 * @author HuangHua
	 * @date 2013-1-6 下午5:25:42
	 * @see com.deppon.esb.management.user.service.INoticUserService#deleteNoticUsersByIds(java.lang.String[])
	 */
	@Override
	public int deleteNoticUsersByIds(String[] paras) {
		return noticUserDao.deleteNoticUsersByIds(paras);
	}

	/** 
	 * 
	 * @author HuangHua
	 * @date 2013-1-6 下午5:25:42
	 * @see com.deppon.esb.management.user.service.INoticUserService#updateNoticuser(com.deppon.esb.management.user.domain.NoticUserInfo)
	 */
	@Override
	public int updateNoticuser(NoticUserInfo noticUserInfo) {
		return noticUserDao.updateNoticuser(noticUserInfo);
	}

	/** 
	 * 
	 * @author HuangHua
	 * @date 2013-1-6 下午5:25:42
	 * @see com.deppon.esb.management.user.service.INoticUserService#addNoticUser(com.deppon.esb.management.user.domain.NoticUserInfo)
	 */
	@Override
	public int addNoticUser(NoticUserInfo noticUserInfo) {
		return noticUserDao.addNoticUser(noticUserInfo);
	}

	/** 
	 * 
	 * @author HuangHua
	 * @date 2013-1-6 下午5:25:42
	 * @see com.deppon.esb.management.user.service.INoticUserService#querySysUsers(java.lang.String, java.lang.String, java.lang.String, java.lang.String, int, int)
	 */
	@Override
	public Map<String, Object> querySysUsers(String userName, String telPhone, String mobilePhone, String email, int start, int limit) {
		List<NoticUserInfo> noticUserList = new ArrayList<NoticUserInfo>();
		Integer resultCount;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userName", userName);
		map.put("telPhone", telPhone);
		map.put("mobilePhone", mobilePhone);
		map.put("email", email);
		map.put("start", start);
		map.put("limit", limit);
		noticUserList = noticUserDao.querySysUsers(map);
		LOGGER.info("**********************************"+noticUserList.size());
		map.remove("start");
		map.remove("limit");
		resultCount = noticUserDao.querySysUserCount(map);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("noticUserInfoList", noticUserList);
		result.put("resultCount", resultCount);
		return result;
	}

}
