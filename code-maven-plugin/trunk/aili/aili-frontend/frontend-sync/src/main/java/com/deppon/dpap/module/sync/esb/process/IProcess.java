package com.deppon.dpap.module.sync.esb.process;

public interface IProcess {

	/**
	 * 业务逻辑处理.
	 * 
	 * @param req
	 *            the req
	 * @return the object
	 * @throws ESBBusinessException
	 *             the eSB business exception
	 * @author HuangHua
	 * @date 2012-12-21 上午10:02:42
	 */
	Object process(Object req);

}
