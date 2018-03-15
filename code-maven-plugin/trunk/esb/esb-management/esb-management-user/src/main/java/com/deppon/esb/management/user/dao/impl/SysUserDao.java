package com.deppon.esb.management.user.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.deppon.esb.management.common.dataaccess.ibatis.IBatis3DaoImpl;
import com.deppon.esb.management.user.dao.ISysUserDao;
import com.deppon.esb.management.user.domain.SysUserInfo;

/**
 * The Class SysUserDao.
 */
@Repository
@Transactional
public class SysUserDao extends IBatis3DaoImpl implements ISysUserDao {

	/** 
	 * @author HuangHua
	 * @date 2013-1-6 下午5:01:11
	 * @see com.deppon.esb.management.user.dao.ISysUserDao#querySysUserCount(java.util.Map)
	 */
	@Override
	public Integer querySysUserCount(Map<String, Object> map) {
		return (Integer) getSqlSession().selectOne("com.deppon.esb.management.user.domain.SysUserInfo.querySysUserCount", map);
	}

	/** 
	 * @author HuangHua
	 * @date 2013-1-6 下午5:01:11
	 * @see com.deppon.esb.management.user.dao.ISysUserDao#querySysUsers(java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<SysUserInfo> querySysUsers(Map<String, Object> map) {
		return getSqlSession().selectList("com.deppon.esb.management.user.domain.SysUserInfo.querySysUsers", map);
	}

	/** 
	 * @author HuangHua
	 * @date 2013-1-6 下午5:01:11
	 * @see com.deppon.esb.management.user.dao.ISysUserDao#deleteSysUsersByIds(java.lang.String[])
	 */
	@Override
	public int deleteSysUsersByIds(String[] ids) {
		return getSqlSession().delete("com.deppon.esb.management.user.domain.SysUserInfo.deleteSysUsersByIds", ids);
	}

	/** 
	 * @author HuangHua
	 * @date 2013-1-6 下午5:01:11
	 * @see com.deppon.esb.management.user.dao.ISysUserDao#updateSysUser(com.deppon.esb.management.user.domain.SysUserInfo)
	 */
	@Override
	public int updateSysUser(SysUserInfo sysUserInfo) {
		return getSqlSession().update("com.deppon.esb.management.user.domain.SysUserInfo.updateSysUser", sysUserInfo);
	}

	/** 
	 * @author HuangHua
	 * @date 2013-1-6 下午5:01:11
	 * @see com.deppon.esb.management.user.dao.ISysUserDao#addSysUser(com.deppon.esb.management.user.domain.SysUserInfo)
	 */
	@Override
	public int addSysUser(SysUserInfo sysUserInfo) {
		return getSqlSession().insert("com.deppon.esb.management.user.domain.SysUserInfo.addSysUser", sysUserInfo);
	}

	/** 
	 * @author HuangHua
	 * @date 2013-1-6 下午5:01:11
	 * @see com.deppon.esb.management.user.dao.ISysUserDao#querySysUserBySysUserName(java.lang.String)
	 */
	@Override
	public String querySysUserBySysUserName(String sysUserName) {
		
		return (String) getSqlSession().selectOne("com.deppon.esb.management.user.domain.SysUserInfo.querySysUserBySysUserName", sysUserName);
	}

	/** 
	 * @author HuangHua
	 * @date 2013-1-6 下午5:01:11
	 * @see com.deppon.esb.management.user.dao.ISysUserDao#modifyPassWord(com.deppon.esb.management.user.domain.SysUserInfo)
	 */
	@Override
	public int modifyPassWord(SysUserInfo sysUserInfo) {
		
		return getSqlSession().update("com.deppon.esb.management.user.domain.SysUserInfo.modifyPassWord", sysUserInfo);
	}

	/** 
	 * @author HuangHua
	 * @date 2013-1-6 下午5:01:11
	 * @see com.deppon.esb.management.user.dao.ISysUserDao#attemptLogin(java.lang.String, java.lang.String)
	 */
	@Override
	public SysUserInfo attemptLogin(String sysUserName, String password) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("sysUserName", sysUserName);
		map.put("password", password);
		return (SysUserInfo) getSqlSession().selectOne("com.deppon.esb.management.user.domain.SysUserInfo.attemptLogin", map);
	}

}
