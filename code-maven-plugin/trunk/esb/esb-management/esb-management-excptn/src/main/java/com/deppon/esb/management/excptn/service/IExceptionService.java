package com.deppon.esb.management.excptn.service;

import java.util.List;

import com.deppon.esb.management.excptn.domain.view.ExceptionBean;
import com.deppon.esb.management.excptn.domain.view.ExceptionBean2;
import com.deppon.esb.management.excptn.domain.view.ExceptionQueryBean;
import com.deppon.esb.management.excptn.domain.view.ExceptionQueryBean2;

/**
 * The Interface IExceptionService.
 */
public interface IExceptionService {
	
	/**
	 * Query exception bean.
	 * 
	 * @param bean
	 *            the bean
	 * @return the list
	 */
	public List<ExceptionBean> queryExceptionBean(ExceptionQueryBean bean);
	
	/**
	 * Query exceptio bean count.
	 * 
	 * @param bean
	 *            the bean
	 * @return the integer
	 */
	public Integer queryExceptioBeanCount(ExceptionQueryBean bean);
	
	/**
	 * Query exception bean.  For 2.0
	 * 
	 * @param bean
	 *            the bean
	 * @return the list
	 */
	public List<ExceptionBean2> queryExceptionBean2(ExceptionQueryBean2 bean);
	
	/**
	 * Query exceptio bean count.  For 2.0
	 * 
	 * @param bean
	 *            the bean
	 * @return the integer
	 */
	public Integer queryExceptioBeanCount2(ExceptionQueryBean2 bean);
	
	/**
	 * Querey exception stack trace.
	 * 
	 * @param id
	 *            the id
	 * @return the string
	 */
	public String quereyExceptionStackTrace(String id);
	/**
	 * Querey exception stack trace. FOr 2.0
	 * 
	 * @param id
	 *            the id
	 * @return the string
	 */
	public String quereyExceptionStackTrace2(String id);
}
