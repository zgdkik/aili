package com.deppon.esb.management.web.action.queuemonitor;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.deppon.esb.management.alert.domain.QueueThresholdInfo;
import com.deppon.esb.management.alert.domain.view.QueueBean;
import com.deppon.esb.management.alert.domain.view.QueueQueryBean;
import com.deppon.esb.management.alert.service.IQueueThresholdService;
/**
 * 队列监控设置
 * @author qiancheng
 *
 */
@Controller("queueMonitorAction")
@Scope("prototype")
public class QueueMonitorAction  {
	
	private QueueThresholdInfo queue;
	private QueueQueryBean bean ;
	private List<QueueBean> queueList;
	private String ids;
	
	private String message;
	private String success;
	private Integer start;
	private Integer limit;
	private Integer totalCount;
	@Resource
	IQueueThresholdService queueThresholdService;
	
	/**
	 * 添加队列监控设置
	 * 
	 */
	public String addQueueMonitor(){
		//校验数据合法性
		if(check()){
			//判断是否有重复队列监控配置；
			QueueQueryBean bean = new QueueQueryBean();
			bean.setQueue(queue.getQueue());
			bean.setThreshold(queue.getThreshold());
			bean.setChannel(queue.getChannelId());
			bean.setPjVersion(queue.getPjVersion());
			List<QueueThresholdInfo> list =  queueThresholdService.getQueueThresholdInfo(bean);
			if(list.size()>0){
				message = "已经存在同样的监控设置";
			}
			else{
				//添加队列监控
				int size = queueThresholdService.addQueueThreshold(queue);
				if(size ==1){
					message ="操作成功";
				}else{
					message ="添加失败";
				}
			}
		}
		return "success";
	}


	/**
	 * 更新队列监控设置
	 */
	public String updateQueueMonitor(){
		int size = queueThresholdService.updateQueueThreshold(queue);
		if(size ==1){
			message ="操作成功";
		}else{
			message ="添加失败";
		}
		return "success";
	}
	/**
	 * 删除队列监控设置
	 */
	public String deleteQueueMonitor(){
		//检查参数
		
		String[] idArray = ids.split(",");
		List<String> idList = Arrays.asList(idArray);
		int size = queueThresholdService.deleteQueueThreshold(idList);
		message ="删除"+size+"条记录";
		return "success";
	}
	/**
	 * 查询队列监控设置
	 */
	public String queryQueueMonitor(){
		if(bean == null){
			bean = new QueueQueryBean();
		}
		bean.setStart(this.start);
		bean.setLimit(limit);
		queueList = queueThresholdService.getQueueBean(bean);
		totalCount = queueThresholdService.getQueueBeanCount(bean);
		this.success="true";
		return "success";
	}
	
	
	/**
	 * 校验输入
	 */
	public boolean check(){
		boolean flag = false;
		if(queue.getThreshold() ==null){
			message = "队列深度不能为空";
		}
		else if(queue.getQueue() == null ||"".equals(queue.getQueue())){
			message="队列名称不能为空";
		}
		else if(queue.getPersonId()==null ||"".equals(queue.getPersonId())){
			message="必须添加监控人员";
		}
		else{
			flag= true;
		}
		return flag;
	}
	
	public QueueThresholdInfo getQueue() {
		return queue;
	}

	public void setQueue(QueueThresholdInfo queue) {
		this.queue = queue;
	}

	public QueueQueryBean getBean() {
		return bean;
	}

	public void setBean(QueueQueryBean bean) {
		this.bean = bean;
	}

	public List<QueueBean> getQueueList() {
		return queueList;
	}

	public void setQueueList(List<QueueBean> queueList) {
		this.queueList = queueList;
	}

	public IQueueThresholdService getQueueThresholdService() {
		return queueThresholdService;
	}

	public void setQueueThresholdService(
			IQueueThresholdService queueThresholdService) {
		this.queueThresholdService = queueThresholdService;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	
	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
}
