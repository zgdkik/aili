package org.hbhk.aili.route.jms.server.common.log.status;

import java.util.Map;


/**
 * 状态日志接口
 * 状态日志的消息 see {@link org.hbhk.aili.route.jms.server.common.log.status.StatusInfo}
 * @author HuangHua
 *
 */
public interface IStatusService {
	
	/**
	 * 保存审计日志.
	 * 
	 * @param headers
	 *            路由中的消息头
	 */
	public void saveStatus(Map<String, Object> headers);

}
