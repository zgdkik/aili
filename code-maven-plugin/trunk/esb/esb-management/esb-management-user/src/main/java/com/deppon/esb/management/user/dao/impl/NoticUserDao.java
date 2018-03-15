package com.deppon.esb.management.user.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.deppon.esb.management.common.dataaccess.ibatis.IBatis3DaoImpl;
import com.deppon.esb.management.user.dao.INoticUserDao;
import com.deppon.esb.management.user.domain.NoticUserInfo;

/**
 * The Class NoticUserDao.
 */
@Repository
public class NoticUserDao extends IBatis3DaoImpl implements INoticUserDao {

	/** 
	 * @author HuangHua
	 * @date 2013-1-6 下午4:59:15
	 * @see com.deppon.esb.management.user.dao.INoticUserDao#queryNoticUserCount(java.util.Map)
	 */
	@Override
	public Integer queryNoticUserCount(Map<String, Object> map) {
		return (Integer) getSqlSession().selectOne("com.deppon.esb.management.user.domain.NoticUserInfo.queryNoticUserCount", map);
	}

	/** 
	 * @author HuangHua
	 * @date 2013-1-6 下午4:59:15
	 * @see com.deppon.esb.management.user.dao.INoticUserDao#queryNoticUsers(java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<NoticUserInfo> queryNoticUsers(Map<String, Object> map) {
		return getSqlSession().selectList("com.deppon.esb.management.user.domain.NoticUserInfo.queryNoticUsers", map);
	}

	/** 
	 * @author HuangHua
	 * @date 2013-1-6 下午4:59:15
	 * @see com.deppon.esb.management.user.dao.INoticUserDao#deleteNoticUsersByIds(java.lang.String[])
	 */
	@Override
	public int deleteNoticUsersByIds(String[] ids) {
		return getSqlSession().delete("com.deppon.esb.management.user.domain.NoticUserInfo.deleteNoticUsersByIds", ids);
	}

	/** 
	 * @author HuangHua
	 * @date 2013-1-6 下午4:59:15
	 * @see com.deppon.esb.management.user.dao.INoticUserDao#addNoticUser(com.deppon.esb.management.user.domain.NoticUserInfo)
	 */
	@Override
	public int addNoticUser(NoticUserInfo noticUserInfo) {
		return getSqlSession().insert("com.deppon.esb.management.user.domain.NoticUserInfo.addNoticUser", noticUserInfo);
	}

	/** 
	 * @author HuangHua
	 * @date 2013-1-6 下午4:59:15
	 * @see com.deppon.esb.management.user.dao.INoticUserDao#updateNoticuser(com.deppon.esb.management.user.domain.NoticUserInfo)
	 */
	@Override
	public int updateNoticuser(NoticUserInfo noticUserInfo) {
		return getSqlSession().insert("com.deppon.esb.management.user.domain.NoticUserInfo.updateNoticuser", noticUserInfo);
	}

	/** 
	 * @author HuangHua
	 * @date 2013-1-6 下午4:59:15
	 * @see com.deppon.esb.management.user.dao.INoticUserDao#querySysUsers(java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<NoticUserInfo> querySysUsers(Map<String, Object> map) {
		return getSqlSession().selectList("com.deppon.esb.management.user.domain.NoticUserInfo.querySysUsers", map);
	}

	/** 
	 * @author HuangHua
	 * @date 2013-1-6 下午4:59:15
	 * @see com.deppon.esb.management.user.dao.INoticUserDao#querySysUserCount(java.util.Map)
	 */
	@Override
	public Integer querySysUserCount(Map<String, Object> map) {
		return (Integer) getSqlSession().selectOne("com.deppon.esb.management.user.domain.NoticUserInfo.querySysUserCount", map);
	}

	/** 
	 * @author HuangHua
	 * @date 2013-1-6 下午4:59:15
	 * @see com.deppon.esb.management.user.dao.INoticUserDao#queryEmailById(java.lang.String[])
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String[] queryEmailById(String[] ids) {
		// 因为这个数组是从数据库中读出来的，所以出现用户名前后有空格的几率较大，先做trim处理
		String[] strings = new String[ids.length];
		for (int i = 0; i < ids.length; i++) {
			strings[i] = ids[i].trim();
		}
		List<String> list = getSqlSession().selectList("com.deppon.esb.management.user.domain.NoticUserInfo.queryEmailById", strings);
		if (list.size() > 0) {
			return list.toArray(new String[list.size()]);
		} else {
			return null;
		}
	}

	/** 
	 * @author HuangHua
	 * @date 2013-1-6 下午4:59:15
	 * @see com.deppon.esb.management.user.dao.INoticUserDao#queryNoticeUser(java.lang.String)
	 */
	@Override
	public NoticUserInfo queryNoticeUser(String id) {
		return (NoticUserInfo) getSqlSession().selectOne(
				"com.deppon.esb.management.user.domain.NoticUserInfo.queryNoticeUser", 
				id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NoticUserInfo> queryAlertUser() {
		return getSqlSession().selectList("com.deppon.esb.management.user.domain.NoticUserInfo.queryAlertUser");
	}

}
