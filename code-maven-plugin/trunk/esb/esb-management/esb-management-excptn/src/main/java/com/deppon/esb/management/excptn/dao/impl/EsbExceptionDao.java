/*
 * PROJECT NAME: esb-management-excptn
 * PACKAGE NAME: com.deppon.esb.management.excptn.dao.impl
 * FILE    NAME: EsbExceptionDao.java
 * COPYRIGHT: Copyright(c) 2012 Deppon All Rights Reserved.
 */
package com.deppon.esb.management.excptn.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.deppon.esb.management.common.dataaccess.ibatis.IBatis3DaoImpl;
import com.deppon.esb.management.excptn.dao.IEsbExceptionDao;
import com.deppon.esb.management.excptn.domain.EsbExceptionInfo;

/**
 * ESB二期异常处理DAO实现
 * 
 * @author HuangHua
 * @date 2013-1-11 上午10:57:15
 */
@Repository
public class EsbExceptionDao extends IBatis3DaoImpl implements IEsbExceptionDao {

	/**
	 * 保存公共异常对象.
	 * 
	 * @param exceptionInfo
	 *            the exception info
	 * @return the int 执行条数
	 * @author HuangHua
	 * @date 2013-1-11 上午10:57:30
	 * @see com.deppon.esb.management.excptn.dao.IEsbExceptionDao#saveEsbException(com.deppon.esb.management.excptn.domain.EsbExceptionInfo)
	 */
	@Override
	public int saveEsbException(EsbExceptionInfo exceptionInfo) {
		return getSqlSession()
				.insert("com.deppon.esb.management.excptn.domain.EsbExceptionInfo.saveEsbException",
						exceptionInfo);

	}

	/** 
	 * 
	 * @author HuangHua
	 * @date 2013-5-18 上午10:18:12
	 * @see com.deppon.esb.management.excptn.dao.IEsbExceptionDao#querySysExcption()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<EsbExceptionInfo> querySysExcption() {
		return getSqlSession().selectList("com.deppon.esb.management.excptn.domain.EsbExceptionInfo.querySysExcption");
	}

	/** 
	 * 
	 * @author HuangHua
	 * @date 2013-5-18 上午10:51:03
	 * @see com.deppon.esb.management.excptn.dao.IEsbExceptionDao#updateExceptionFlag(java.util.List)
	 */
	@Override
	public int updateExceptionFlagByIds(List<String> ids, int flag) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("flag", flag);
		int count = 0;
		while(count < ids.size()){
			int index = count;
			count = count + 100;
			if(count > ids.size()){
				count = ids.size();
			}
			List<String> list = ids.subList(index, count);
			map.put("ids", list);
			getSqlSession().update("com.deppon.esb.management.excptn.domain.EsbExceptionInfo.updateExceptionFlagByIds", map);
		}
		return ids.size();
	}

}
