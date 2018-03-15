package com.deppon.esb.management.web.action.mqConnect;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.deppon.esb.management.mq.domain.QueueManagerInfo;
import com.deppon.esb.management.mq.service.IMqManagerService;
import com.deppon.esb.management.web.deploy.struts.ESBActionSupport;

@Controller("mqConnectAction")
@Scope("prototype")
public class MqConnectAction extends ESBActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1764144261316600393L;

	@Resource
	private IMqManagerService mqManagerService;

	// 删除时的唯一标识
	private String fid;

	private QueueManagerInfo queueManagerInfo;
	private List<QueueManagerInfo> mqConnectInfoList;

	// 查询所有连接信息
	public String queryMqConnect() {
		if (queueManagerInfo == null) {
			mqConnectInfoList = mqManagerService.findAll();
		} else {
			mqConnectInfoList = mqManagerService
					.queryManagerAdder(queueManagerInfo);
		}
		return SUCCESS;
	}

	// 删除连接信息
	public String deleteMqConnectByIds() {
		if (fid == null || "0".equals(fid) || "null".equals(fid)) {
			message = "数据不能为null";
			return SUCCESS;
		}
		List<String> list = new ArrayList<String>();
		String[] paras = fid.split(",");
		for (int i = 0; i < paras.length; i++) {
			list.add(paras[i]);
		}
		int count = mqManagerService.deleteManagerAdder(list);
		list.clear();
		message = "成功删除[" + count + "]条数据！";
		success = true;
		return SUCCESS;
	}

	// 修改连接信息
	public String updateMqConnect() {
		if (queueManagerInfo == null || queueManagerInfo.getId() == null) {
			message = "数据不能为null";
			return SUCCESS;
		}
		if (mqManagerService.queryManagerAdder(queueManagerInfo).size() != 0) {
			message = "已经存在请重新修改";
			return SUCCESS;
		}
		int count = mqManagerService.updateManagerAdder(queueManagerInfo);
		if (count < 1) {
			message = "修改数据失败!";
		} else {
			message = "修改数据成功！";
			success = true;
		}
		return SUCCESS;
	}

	public String addMqConnect() {
		if (queueManagerInfo == null) {
			message = "数据不能为null";
			return SUCCESS;
		}
		if (mqManagerService.queryManagerAdder(queueManagerInfo).size() != 0) {
			message = "新增的数据已经存在，请重新增加";
			return SUCCESS;
		}
		int count = mqManagerService.addManagerAdder(queueManagerInfo);
		if (count < 1) {
			message = "修改数据失败!";
		} else {
			message = "修改数据成功！";
			success = true;
		}
		return SUCCESS;
	}

	public IMqManagerService getMqManagerService() {
		return mqManagerService;
	}

	public QueueManagerInfo getQueueManagerInfo() {
		return queueManagerInfo;
	}

	public List<QueueManagerInfo> getMqConnectInfoList() {
		return mqConnectInfoList;
	}

	public void setMqManagerService(IMqManagerService mqManagerService) {
		this.mqManagerService = mqManagerService;
	}

	public void setQueueManagerInfo(QueueManagerInfo queueManagerInfo) {
		this.queueManagerInfo = queueManagerInfo;
	}

	public void setMqConnectInfoList(List<QueueManagerInfo> mqConnectInfoList) {
		this.mqConnectInfoList = mqConnectInfoList;
	}

	public String getFid() {
		return fid;
	}

	public void setFid(String fid) {
		this.fid = fid;
	}

}
