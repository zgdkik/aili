package com.deppon.esb.management.web.action.exceptionmonitorset;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.deppon.esb.management.alert.domain.InterfaceThresholdInfo;
import com.deppon.esb.management.alert.domain.view.InterfaceThresholdBean;
import com.deppon.esb.management.alert.domain.view.InterfaceThresholdQueryBean;
import com.deppon.esb.management.alert.service.IInterfaceThresholdService;
import com.deppon.esb.management.svccfg.domain.SvcPointInfo;
import com.deppon.esb.management.svccfg.service.ISvcpointService;

/**
 * 后端、ESB端异常监控设置
 * 
 * @author qiancheng
 * 
 */
@Controller("exceptionMonitorSetAction")
@Scope("prototype")
public class ExceptionMonitorSetAction {
	/**
	 * 日志
	 */
	protected static final Logger LOGGER = LoggerFactory.getLogger(ExceptionMonitorSetAction.class);
	@Resource(name = "interfaceThresholdService")
	private IInterfaceThresholdService interfaceThresholdService;

	private List<InterfaceThresholdBean> thresholdList;
	private InterfaceThresholdInfo thresholdInfo;
	private InterfaceThresholdQueryBean queryBean;
	private String message;
	private Integer start;
	private Integer limit;
	private String success = "false";
	private Integer totalCount;

	//用于查询服务配置
	private SvcPointInfo svcPointInfo;
	private List<SvcPointInfo> svcPointInfolist;
	@Resource(name = "svcpointService")
	private ISvcpointService svcpointService;
	public String addThreshold() {
		// 检查参数
		if(check1()){
			// 检查是否已经添加同样的预警监控设置
			if (isThresholdExist(thresholdInfo)) {
				operatFail();
				message = "已经存在同样的预警监控配置";
			}else{
				thresholdInfo.setType(Integer.valueOf(1));
				interfaceThresholdService.addInterfacethreshold(thresholdInfo);
				operatSuccess();
			}
		}
		return "success";
	}

	public String queryFullTrheshold() {
		if (queryBean == null) {
			queryBean = new InterfaceThresholdQueryBean();
		}
		queryBean.setStart(start);
		queryBean.setLimit(limit);
		totalCount = interfaceThresholdService.getExceptionSetCount(queryBean);
		thresholdList = interfaceThresholdService.getExceptionSet(queryBean);
		operatSuccess();
		return "success";
	}

	public String updateThreshold() {
		interfaceThresholdService.updateInterfaceThreshold(thresholdInfo);
		operatSuccess();
		return "success";
	}

	public String deleteThreshold() {
		if (check2()) {
			String[] ids = queryBean.getPersonId().split(",");
			interfaceThresholdService.deleteInterfaceThresholdById(Arrays
					.asList(ids));
			operatSuccess();
		}
		return "success";
	}

	/**
	 * 检查参数
	 */
	private boolean check1() {
		boolean flag =false ;
		if (thresholdInfo == null) {
			message = "参数解析异常 ";
		}
		else if(thresholdInfo.getPersonId()==null || "".equals(thresholdInfo.getPersonId())){
			message ="请选择预警人员";
		}
		else if(thresholdInfo.getSvcCode() == null){
			message="请选择服务";
		}
		else if(thresholdInfo.getChannelId()==null){
			message="请 选择预警渠道";
		}
		else{
			flag= true;
		}
		return flag;
	}

	/**
	 * 检查参数
	 */
	private boolean check2() {
		boolean flag = false;
		if (queryBean == null || queryBean.getPersonId() == null
				|| "".equals(queryBean.getPersonId())) {
			message = "参数解析异常 ";
		} else {
			flag = true;
		}
		return flag;
	}

	/**
	 * 检查是否已经存在同样性能监控配置
	 * (性能监控配置唯一性由svcCode，channelid，threshold决定，
	 * 异常监控设置由svcCode, channelid决定)
	 * @param thresholdInfo
	 * @return
	 */
	private boolean isThresholdExist(InterfaceThresholdInfo thresholdInfo) {
		InterfaceThresholdQueryBean bean = new InterfaceThresholdQueryBean();
		bean.setSvcCode(thresholdInfo.getSvcCode());
		bean.setChannel(thresholdInfo.getChannelId());
		bean.setThreshold(thresholdInfo.getThreshold());
		List<InterfaceThresholdInfo> list = interfaceThresholdService
				.getInterfaceThreshold(bean);
		boolean flag = false;
		if (list.size() > 0) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 查询后端服务配置
	 */
	public String queryBackSvcpoint(){
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("start",this.start);
			map.put("limit",this.limit);
			map.put("frntOrBck", "B");
			if(null !=svcPointInfo ){
				if(null !=svcPointInfo.getSvcName() || !"".equals(svcPointInfo.getSvcName())){
					map.put("svcName", svcPointInfo.getSvcName());	
				}
				if(null != svcPointInfo.getSvcAddr() || !"".equals(svcPointInfo.getSvcAddr())){
					map.put("svcAddr", svcPointInfo.getSvcAddr());					
				}
				if(null !=svcPointInfo.getSvcAgrmt() ||!"".equals(svcPointInfo.getSvcAgrmt()) ){
					map.put("svcAgrmt", svcPointInfo.getSvcAgrmt());					
				}
				if(null != svcPointInfo.getSvcProvdId() || !"".equals(svcPointInfo.getSvcProvdId()))
				{
					map.put("svcProvdId", svcPointInfo.getSvcProvdId());
				}
			}
			this.totalCount=svcpointService.getTotalCount(map);		
			svcPointInfolist = svcpointService.getSvcpointsByConditions(map);
			message ="查询成功";
			success="true";
			return "success";
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return "error";
		}
	}
	
	/**
	 * 查询前端服务配置
	 */
	public String queryEsbSvcpoint(){
		
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("start",this.start);
			map.put("limit",this.limit);
			map.put("frntOrBck", "F");
			if(null !=svcPointInfo ){
				if(null !=svcPointInfo.getSvcName() || !"".equals(svcPointInfo.getSvcName())){
					map.put("svcName", svcPointInfo.getSvcName());	
				}
				if(null != svcPointInfo.getSvcAddr() || !"".equals(svcPointInfo.getSvcAddr())){
					map.put("svcAddr", svcPointInfo.getSvcAddr());					
				}
				if(null !=svcPointInfo.getSvcAgrmt() ||!"".equals(svcPointInfo.getSvcAgrmt()) ){
					map.put("svcAgrmt", svcPointInfo.getSvcAgrmt());					
				}
				if(null != svcPointInfo.getSvcProvdId() || !"".equals(svcPointInfo.getSvcProvdId()))
				{
					map.put("svcProvdId", svcPointInfo.getSvcProvdId());
				}
			}
			this.totalCount=svcpointService.getTotalCount(map);		
			svcPointInfolist = svcpointService.getSvcpointsByConditions(map);
			message ="查询成功";
			success="true";
			return "success";
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return "error";
		}
	}
	
	public void operatSuccess() {
		this.message = "操作成功";
		this.success = "true";
	}

	public void operatFail() {
		this.message = "操作失败";
		this.success = "false";
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

	public SvcPointInfo getSvcPointInfo() {
		return svcPointInfo;
	}

	public void setSvcPointInfo(SvcPointInfo svcPointInfo) {
		this.svcPointInfo = svcPointInfo;
	}

	public List<SvcPointInfo> getSvcPointInfolist() {
		return svcPointInfolist;
	}

	public void setSvcPointInfolist(List<SvcPointInfo> svcPointInfolist) {
		this.svcPointInfolist = svcPointInfolist;
	}

	public ISvcpointService getSvcpointService() {
		return svcpointService;
	}

	public void setSvcpointService(ISvcpointService svcpointService) {
		this.svcpointService = svcpointService;
	}

}
