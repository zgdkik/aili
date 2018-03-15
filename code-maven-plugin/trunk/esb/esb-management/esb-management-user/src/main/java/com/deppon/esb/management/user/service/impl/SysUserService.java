package com.deppon.esb.management.user.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.deppon.esb.management.user.dao.ISysUserDao;
import com.deppon.esb.management.user.domain.SysUserInfo;
import com.deppon.esb.management.user.service.ISysUserService;

/**
 * The Class SysUserService.
 */
@Service
@Transactional
public class SysUserService implements ISysUserService {

	/** The sys user dao. */
	@Resource
	private ISysUserDao sysUserDao;

	/** 
	 * 
	 * @author HuangHua
	 * @date 2013-1-6 下午5:25:56
	 * @see com.deppon.esb.management.user.service.ISysUserService#querySysUsers(com.deppon.esb.management.user.domain.SysUserInfo, int, int)
	 */
	@Override
	public Map<String, Object> querySysUsers(SysUserInfo sysUserInfo, int start, int limit) {
		List<SysUserInfo> sysUserInfoList = new ArrayList<SysUserInfo>();
		Integer resultCount;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sysUserInfo", sysUserInfo);
		resultCount = sysUserDao.querySysUserCount(map);
		map.put("start", start);
		map.put("limit", limit);
		sysUserInfoList = sysUserDao.querySysUsers(map);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("sysUserInfoList", sysUserInfoList);
		result.put("resultCount", resultCount);
		return result;
	}

	/** 
	 * 
	 * @author HuangHua
	 * @date 2013-1-6 下午5:25:56
	 * @see com.deppon.esb.management.user.service.ISysUserService#deleteSysUsersByIds(java.lang.String[])
	 */
	@Override
	public int deleteSysUsersByIds(String[] ids) {
		return sysUserDao.deleteSysUsersByIds(ids);
	}

	/** 
	 * 
	 * @author HuangHua
	 * @date 2013-1-6 下午5:25:56
	 * @see com.deppon.esb.management.user.service.ISysUserService#updateSysUser(com.deppon.esb.management.user.domain.SysUserInfo)
	 */
	@Override
	public int updateSysUser(SysUserInfo sysUserInfo) {
		return sysUserDao.updateSysUser(sysUserInfo);
	}

	/** 
	 * 
	 * @author HuangHua
	 * @date 2013-1-6 下午5:25:56
	 * @see com.deppon.esb.management.user.service.ISysUserService#addSysUser(com.deppon.esb.management.user.domain.SysUserInfo)
	 */
	@Override
	public int addSysUser(SysUserInfo sysUserInfo) {
		return sysUserDao.addSysUser(sysUserInfo);
	}

	/** 
	 * 
	 * @author HuangHua
	 * @date 2013-1-6 下午5:25:56
	 * @see com.deppon.esb.management.user.service.ISysUserService#querySysUserBySysUserName(java.lang.String)
	 */
	@Override
	public String querySysUserBySysUserName(String sysUserName) {

		return sysUserDao.querySysUserBySysUserName(sysUserName);
	}

	/** 
	 * 
	 * @author HuangHua
	 * @date 2013-1-6 下午5:25:56
	 * @see com.deppon.esb.management.user.service.ISysUserService#modifyPassWord(com.deppon.esb.management.user.domain.SysUserInfo)
	 */
	@Override
	public boolean modifyPassWord(SysUserInfo sysUserInfo) {
		int count = sysUserDao.modifyPassWord(sysUserInfo);
		if(count>0){
			return true;
		}
		return false;
	}

	/** 
	 * 
	 * @author HuangHua
	 * @date 2013-1-6 下午5:25:56
	 * @see com.deppon.esb.management.user.service.ISysUserService#attemptLogin(java.lang.String, java.lang.String)
	 */
	public SysUserInfo attemptLogin(String sysUserName, String password) {
		return sysUserDao.attemptLogin(sysUserName, password);
	}

}
