package org.hbhk.aili.route.http.processor;

import java.util.Map;

import org.apache.camel.Header;
import org.apache.camel.Properties;
import org.hbhk.aili.route.common.exception.AgentConcurrentException;
import org.hbhk.aili.route.common.exception.AgentExceptionConstant;
import org.hbhk.aili.route.jms.common.constant.ExpressConstant;
import org.hbhk.aili.route.jms.server.security.flowcontrol.container.ICountContainer;
import org.hbhk.aili.route.jms.server.security.flowcontrol.counter.ICounter;

public class ConcurrentProcessor {
	//private Logger LOG = Logger.getLogger(ConcurrentProcessor.class);
	private ICountContainer esbInterfaceConcurrentContainer;
	private ICountContainer esbUserInterfaceConcurrentContainer;

	/**
	 * 计数器值减一
	 * @param username
	 * @param esbServiceCode
	 * @param properties
	 */
	public void counterDec(
			@Header(ExpressConstant.EXPRESS_USERNAME) String username,
			@Header(ExpressConstant.EXPRESS_ESB_SERVICE_CODE) String esbServiceCode,
			@Properties Map<String, Object> properties) {
		
		ICounter userItfCounter = esbUserInterfaceConcurrentContainer.get(username
				+ esbServiceCode);
		properties.put(ExpressConstant.USER_ITF_CON_COUNTER, userItfCounter);
		if (userItfCounter != null &&userItfCounter.decrementandGet() <0){
				String msg = "用户" + username + "访问接口" + esbServiceCode + "并发到达上限值:"+userItfCounter.getInitValue();
				throw new AgentConcurrentException(msg,
						AgentExceptionConstant.TYPE_SYS,
						AgentExceptionConstant.CONCURRENT_USER_ITF_EEROR);
		}
		
		ICounter itfCounter = esbInterfaceConcurrentContainer
				.get(esbServiceCode);
		properties.put(ExpressConstant.ITF_CON_COUNTER, itfCounter);
/*		if (itfCounter != null &&itfCounter.decrementandGet() <0) {			
				String msg = null;
				msg = "接口:" + esbServiceCode + "的总并发量到达上限值："+itfCounter.getInitValue()
						+ itfCounter.getInitValue();
				throw new AgentConcurrentException(msg,
						AgentExceptionConstant.TYPE_SYS,
						AgentExceptionConstant.CONCURRENT_ITF_EEROR);
		}*/

		//LOG.info("接口:"+esbServiceCode+"并发值" +(itfCounter.getInitValue()-itfCounter.get()));
		//LOG.info("用户:"+username+",接口:"+esbServiceCode+",并发值" +(userItfCounter.getInitValue()-userItfCounter.get()));
	}

	/**
	 * 把计数器值加一;
	 * 
	 * @param username
	 * @param esbServiceCode
	 * @param properties
	 */
	public void counterInc(
			@Header(ExpressConstant.EXPRESS_USERNAME) String username,
			@Header(ExpressConstant.EXPRESS_ESB_SERVICE_CODE) String esbServiceCode,
			@Properties Map<String, Object> properties) {
/*		ICounter counter = (ICounter)properties.get(ExpressConstant.ITF_CON_COUNTER);
		// 当计数器存在,并且标志位里
		if (counter != null) {
			counter.incrementAndGet();
		}*/
		ICounter userItfCounter = (ICounter)properties.get(ExpressConstant.USER_ITF_CON_COUNTER);
		if (userItfCounter != null) {
			userItfCounter.incrementAndGet();
		}
	}

	public ICountContainer getEsbInterfaceConcurrentContainer() {
		return esbInterfaceConcurrentContainer;
	}

	public void setEsbInterfaceConcurrentContainer(
			ICountContainer esbInterfaceConcurrentContainer) {
		this.esbInterfaceConcurrentContainer = esbInterfaceConcurrentContainer;
	}

	public ICountContainer getEsbUserInterfaceConcurrentContainer() {
		return esbUserInterfaceConcurrentContainer;
	}

	public void setEsbUserInterfaceConcurrentContainer(
			ICountContainer esbUserInterfaceConcurrentContainer) {
		this.esbUserInterfaceConcurrentContainer = esbUserInterfaceConcurrentContainer;
	}

}
