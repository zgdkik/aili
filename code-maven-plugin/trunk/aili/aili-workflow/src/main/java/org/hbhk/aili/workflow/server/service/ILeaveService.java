package org.hbhk.aili.workflow.server.service;

import org.hbhk.aili.base.server.service.IBaseService;
import org.hbhk.aili.workflow.share.entity.Leave;
/**
 * 请假工作流
 * @author hbhk
 */
public interface ILeaveService extends IBaseService<Leave, String> {

	String insertLeave(Leave leave,String processInstanceByKey);
	
	String updateLeave(Leave leave,String processInstanceByKey) ;
}
