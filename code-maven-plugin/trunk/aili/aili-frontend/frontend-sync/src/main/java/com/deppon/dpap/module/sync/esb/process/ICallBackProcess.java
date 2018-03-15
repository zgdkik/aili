package com.deppon.dpap.module.sync.esb.process;
public interface ICallBackProcess {

	/**
	 * 回调方法,请尽量把所有异常都抛出来.
	 * 
	 * @param response
	 *            the response
	 * @throws ESBException
	 *             the eSB exception
	 */
	void callback(Object response);

	/**
	 * 当服务端处理业务逻辑时，出现了异常。 而在返回对象里没有定义异常信息时，最终返回一个异常对象。 默认是.
	 * 
	 * @param errorResponse
	 *            the error response
	 * @throws ESBException
	 *             the eSB exception
	 *             {@link com.deppon.esb.exception.CommonExceptionInfo};
	 *             为了方便确认具体的业务，在参数里加入了业务ID。 获取参数的正确处理方法是： ErrorResponse response
	 *             = (ErrorResponse)errorResponse；
	 */
	void errorHandler(Object errorResponse);

}
