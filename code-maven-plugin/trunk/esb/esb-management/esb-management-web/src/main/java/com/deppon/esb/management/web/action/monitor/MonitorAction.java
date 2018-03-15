package com.deppon.esb.management.web.action.monitor;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.deppon.esb.management.alert.domain.InterfaceThresholdInfo;
import com.deppon.esb.management.alert.domain.view.InterfaceThresholdBean;
import com.deppon.esb.management.alert.domain.view.InterfaceThresholdQueryBean;
import com.deppon.esb.management.alert.service.IInterfaceThresholdService;
/**
 * 后端、ESB端服务性能监控设置
 * @author qiancheng
 *
 */
@Controller("monitorAction")
@Scope("prototype")
public class MonitorAction {
	@Resource(name="interfaceThresholdService")
	private IInterfaceThresholdService interfaceThresholdService;
	

	private List<InterfaceThresholdBean> thresholdList;
	private InterfaceThresholdInfo thresholdInfo;
	private InterfaceThresholdQueryBean queryBean;
	private String message;
	private Integer start;
	private Integer limit;
	private String success = "false";
	private Integer totalCount;
	public String addThreshold(){
		//检查参数
		check1();
		//检查是否已经添加同样的预警监控设置
		if(isThresholdExist(thresholdInfo)){
			operatFail();
			message="已经存在同样的预警监控配置";
		}else{
			interfaceThresholdService.addInterfacethreshold(thresholdInfo);
			operatSuccess();
		}
		return "success";
	}
	
	public String queryFullTrheshold(){
		if(queryBean == null){
			queryBean = new InterfaceThresholdQueryBean();
		}
		queryBean.setStart(start);
		queryBean.setLimit(limit);
	//	totalCount = interfaceThresholdService()
		totalCount= interfaceThresholdService.getThresholdResultBeanCount(queryBean);
		thresholdList =interfaceThresholdService.getThresholdResultBean(queryBean);
		operatSuccess();
		return "success";
	}
	
	public String updateThreshold(){
		interfaceThresholdService.updateInterfaceThreshold(thresholdInfo);
		operatSuccess();
		return "success";
	}
	public String deleteThreshold(){
		if(check2()){
			String[] ids = queryBean.getPersonId().split(",");
			interfaceThresholdService.deleteInterfaceThresholdById(Arrays.asList(ids));
			operatSuccess();
		}
		return "success";
	}
	/**
	 * 检查参数
	 */
	private void check1(){
		if(queryBean == null){
			message="参数解析异常 ";
		}
	}
	
	/**
	 * 检查参数
	 */
	private boolean check2(){
		boolean flag = false;
		if(queryBean == null 
			||queryBean.getPersonId() == null
			||"".equals(queryBean.getPersonId())){
			message="参数解析异常 ";
		}
		else{
			flag = true;
		}
		return flag;
	}
	/**
	 * 检查是否已经存在同样预警监控配置
	 * @param thresholdInfo
	 * @return
	 */
	private boolean isThresholdExist(InterfaceThresholdInfo thresholdInfo){
		InterfaceThresholdQueryBean bean = new InterfaceThresholdQueryBean();
		bean.setSvcCode(thresholdInfo.getSvcCode());
		bean.setChannel(thresholdInfo.getChannelId());
		bean.setThreshold(thresholdInfo.getThreshold());
		List<InterfaceThresholdInfo>  list = interfaceThresholdService.getInterfaceThreshold(bean);
		boolean flag = false;
		if(list.size()>0){
			flag = true;
		}
		return flag;
	}
	
	public void operatSuccess(){
		this.message="操作成功";
		this.success="true";
	}
	public void operatFail(){
		this.message="操作失败";
		this.success="false";
	}
	
	public String getMessage() {
		return message;
	}	
	
	public void setMessage(String message) {
		this.message = message;
	}


	public IInterfaceThresholdService getInterfaceThresholdService() {
		return interfaceThresholdService;
	}

	public void setInterfaceThresholdService(
			IInterfaceThresholdService interfaceThresholdService) {
		this.interfaceThresholdService = interfaceThresholdService;
	}

	public InterfaceThresholdInfo getThresholdInfo() {
		return thresholdInfo;
	}

	public void setThresholdInfo(InterfaceThresholdInfo thresholdInfo) {
		this.thresholdInfo = thresholdInfo;
	}

	public List<InterfaceThresholdBean> getThresholdList() {
		return thresholdList;
	}

	public void setThresholdList(List<InterfaceThresholdBean> thresholdList) {
		this.thresholdList = thresholdList;
	}

	public InterfaceThresholdQueryBean getQueryBean() {
		return queryBean;
	}

	public void setQueryBean(InterfaceThresholdQueryBean queryBean) {
		this.queryBean = queryBean;
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

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	
}
