package com.deppon.esb.management.excptn.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.deppon.esb.management.excptn.dao.IExceptionDao;
import com.deppon.esb.management.excptn.domain.view.ExceptionBean;
import com.deppon.esb.management.excptn.domain.view.ExceptionBean2;
import com.deppon.esb.management.excptn.domain.view.ExceptionQueryBean;
import com.deppon.esb.management.excptn.domain.view.ExceptionQueryBean2;
import com.deppon.esb.management.excptn.service.IExceptionService;

@Service
@Transactional
public class ExceptionService implements IExceptionService {

	@Resource(name="exceptionDao")
	private IExceptionDao exceptionDao;
	@Override
	public List<ExceptionBean> queryExceptionBean(ExceptionQueryBean bean) {
		return exceptionDao.queryExceptionBean(bean);
	}
	@Override
	public Integer queryExceptioBeanCount(ExceptionQueryBean bean) {
		return exceptionDao.queryExcpetionBeanCount(bean);
	}
	/**
	 * For 2.0  查询异常信息
	 */
	@Override
	public List<ExceptionBean2> queryExceptionBean2(ExceptionQueryBean2 bean) {
		return exceptionDao.queryExceptionBean2(bean);
	}
	/**
	 * For 2.0 查询异常总记录数
	 */
	@Override
	public Integer queryExceptioBeanCount2(ExceptionQueryBean2 bean) {
		return exceptionDao.queryExcpetionBeanCount2(bean);
	}
	@Override
	public String quereyExceptionStackTrace(String id) {
		return exceptionDao.queryExceptionStrace(id);
	}
	@Override
	public String quereyExceptionStackTrace2(String id) {
		return exceptionDao.queryExceptionStrace2(id);
	}
}
