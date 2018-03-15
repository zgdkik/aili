package com.deppon.esb.management.excptn.dao;

import java.util.List;

import com.deppon.esb.management.excptn.domain.ExceptionInfo;
import com.deppon.esb.management.excptn.domain.view.ExceptionBean;
import com.deppon.esb.management.excptn.domain.view.ExceptionBean2;
import com.deppon.esb.management.excptn.domain.view.ExceptionQueryBean;
import com.deppon.esb.management.excptn.domain.view.ExceptionQueryBean2;

/**
 * @Description 异常信息DAO接口
 * @author HuangHua
 * @date 2012-03-08 15:38:54
 * 
 */
public interface IExceptionDao {

	public List<ExceptionInfo> queryExceptionList();

	public void storeException(ExceptionInfo exceptionInfo);

	public void storeExceptionList(List<ExceptionInfo> list);

	public List<ExceptionBean> queryExceptionBean(ExceptionQueryBean bean);

	/**
	 * For 2.0
	 * 
	 * @param bean
	 * @return
	 */
	public List<ExceptionBean2> queryExceptionBean2(ExceptionQueryBean2 bean);

	/**
	 * 查询总数
	 * 
	 * @param bean
	 * @return
	 */
	public Integer queryExcpetionBeanCount(ExceptionQueryBean bean);

	/**
	 * 查询总数 For 2.0
	 * 
	 * @param bean
	 * @return
	 */
	public Integer queryExcpetionBeanCount2(ExceptionQueryBean2 bean);

	/**
	 * 查询异常堆栈信息
	 * 
	 * @param id
	 * @return
	 */
	public String queryExceptionStrace(String id);

	/**
	 * 查询重发信息
	 */
	public List<ExceptionInfo> queryRedo();

	/**
	 * 更新重发状态
	 */
	public Integer updateRedoSuccess(List<String> ids);

	/**
	 * 更新重发次数
	 * 
	 * @param ids
	 */
	public Integer updateRedoCount(List<String> ids);

	/**
	 * 查询异常堆栈信息  For 2.0
	 * @param id
	 * @return
	 */
	public String queryExceptionStrace2(String id);
	
}
