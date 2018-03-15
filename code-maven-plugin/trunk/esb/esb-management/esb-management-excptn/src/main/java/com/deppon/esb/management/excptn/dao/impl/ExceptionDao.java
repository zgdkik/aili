package com.deppon.esb.management.excptn.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.deppon.esb.management.common.dataaccess.ibatis.IBatis3DaoImpl;
import com.deppon.esb.management.excptn.dao.IExceptionDao;
import com.deppon.esb.management.excptn.domain.ExceptionInfo;
import com.deppon.esb.management.excptn.domain.view.ExceptionBean;
import com.deppon.esb.management.excptn.domain.view.ExceptionBean2;
import com.deppon.esb.management.excptn.domain.view.ExceptionQueryBean;
import com.deppon.esb.management.excptn.domain.view.ExceptionQueryBean2;


@Repository
public class ExceptionDao extends IBatis3DaoImpl implements IExceptionDao{

	@Override
	public List<ExceptionInfo> queryExceptionList() {
		return null;
	}

	@Override
	public void storeException(ExceptionInfo exceptionInfo) {
		try {
			getSqlSession().insert("com.deppon.esb.management.excptn.domain.ExceptionInfo.saveException", exceptionInfo);
		} catch (Throwable t) {
			logger.error(t.getMessage(), t);
		}
	}

	@Override
	public void storeExceptionList(List<ExceptionInfo> list) {
		for(ExceptionInfo info:list){
			getSqlSession().insert("com.deppon.esb.management.excptn.domain.ExceptionInfo.saveException", info);
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ExceptionBean> queryExceptionBean(ExceptionQueryBean bean) {
		return this.getSqlSession().selectList("com.deppon.esb.management.excptn.domain.ExceptionInfo.selectExceptionBean", bean);
	}
	/**
	 * For 2.0 queryException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ExceptionBean2> queryExceptionBean2(ExceptionQueryBean2 bean) {
		return this.getSqlSession().selectList("com.deppon.esb.management.excptn.domain.EsbExceptionInfo.selectExceptionBean2", bean);
	}

	@Override
	public Integer queryExcpetionBeanCount(ExceptionQueryBean bean) {
		return (Integer)this.getSqlSession().selectOne("com.deppon.esb.management.excptn.domain.ExceptionInfo.count",bean);
	}
	/**
	 * For 2.0 查询总记录数
	 */
	@Override
	public Integer queryExcpetionBeanCount2(ExceptionQueryBean2 bean) {
		return (Integer)this.getSqlSession().selectOne("com.deppon.esb.management.excptn.domain.EsbExceptionInfo.count2",bean);
	}
	/**
	 * 查询详细异常信息  For 2.0
	 */
	@Override
	public String queryExceptionStrace2(String id) {
		return (String)this.getSqlSession().selectOne("com.deppon.esb.management.excptn.domain.EsbExceptionInfo.selectStackTrace2", id);
	}
	
	@Override
	public String queryExceptionStrace(String id) {
		return (String)this.getSqlSession().selectOne("com.deppon.esb.management.excptn.domain.ExceptionInfo.selectStackTrace", id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ExceptionInfo> queryRedo() {
		return this.getSqlSession().selectList("com.deppon.esb.management.excptn.domain.ExceptionInfo.selectRedo");
	}

	@Override
	public Integer updateRedoSuccess(List<String> ids) {
		return (Integer)this.getSqlSession().update("com.deppon.esb.management.excptn.domain.ExceptionInfo.updateRedoSuccess", ids);		
	}

	@Override
	public Integer updateRedoCount(List<String> ids) {
		return this.getSqlSession().update("com.deppon.esb.management.excptn.domain.ExceptionInfo.updateRedoCount", ids);
	}

}
