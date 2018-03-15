package com.deppon.esb.management.web.action.svcpoint;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.deppon.esb.management.svccfg.domain.SvcPointInfo;
import com.deppon.esb.management.svccfg.domain.SvcPointRelationInfo;
import com.deppon.esb.management.svccfg.service.ISvcpointRelationService;
import com.deppon.esb.management.svccfg.service.ISvcpointService;

@Controller("backSvcpintAction")
@Scope("prototype")
public class BackSvcpintAction {
	/**
	 * 日志
	 */
	protected static final Logger LOGGER = LoggerFactory.getLogger(BackSvcpintAction.class);

	private SvcPointInfo svcPointInfo;
	private String svcCodes;
	private String message;
	
	@Resource(name = "svcpointService")
	private ISvcpointService svcpointService;

	@Resource(name="svcpointRelationService")
	private ISvcpointRelationService svcpointRelationService;
	
	//用来表示处理状态,extjs前端需要用这个值来判断处理是否成功
	private String success = "false";

	//分页相关属性
	private Integer totalCount;

	private Integer start;

	private Integer limit;
	
	private SvcPointRelationInfo svcPointRelationInfo; 
	
	private List<SvcPointInfo> svcPointInfolist;
	
	/**
	 * 新增后端服务配置信息
	 * @return
	 */
	public String addSvcpoint() {
 		if (check()) {
 			if (null != svcpointService.getSvcpointBySvcCode(svcPointInfo
 					.getSvcCode())) {
 				// 查询是否已经存在同服务名的服务
 				message = "服务编码为" + svcPointInfo.getSvcCode() + "的服务配置已经存在！";
 			}else{
 				svcpointService.addSvcpoint(svcPointInfo);
 				message = "添加成功";
 				this.success = "true";
 			}
		}
		return "success";
	}

	/**
	 * 更新配置
	 * @return
	 */
	public String updateSvcpoint(){
		if(check()){
 			if (null == svcpointService.getSvcpointBySvcCode(svcPointInfo
 					.getSvcCode())) {
 				// 查询是否被删除
 				message = "服务编码为" + svcPointInfo.getSvcCode() + "的服务配置已经被删除！";
 			}
			svcpointService.updateSvcpoint(svcPointInfo);
			message = svcPointInfo.getSvcName()+"更新成功";
			this.success = "true";
		}
		return "success";
	}
	
	/**
	 * 根据svcode进行删除
	 */
	public String deleteSvcpoint(){
		if(null ==svcCodes || "".equals(svcCodes)){
			message="无法获取参数";
		}
		else{
			String[] array = svcCodes.split(",");
			List<String> svcCodes=Arrays.asList(array);
			svcpointService.deleteSvcPointInfos(svcCodes);
			message = "删除成功";
		}
		return "success";
	}
	/**
	 * 查询后端服务配置
	 */
	public String queryBackSvcpoints(){
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
	 * 检查输入是否合法
	 */
	public boolean check() {
		LOGGER.info(ToStringBuilder.reflectionToString(svcPointInfo));
		if (svcPointInfo == null) {
			message = "服务器解析参数失败";
			return false;
		} else if (svcPointInfo.getSvcCode() == null
				|| "".equals(svcPointInfo.getSvcCode())) {
			message = "请输入服务编码";
			return false;
		} else if (svcPointInfo.getSvcAgrmt() == null
				|| "".equals(svcPointInfo.getSvcAgrmt())) {
			message = "请选择接入协议";
			return false;
		} else if (svcPointInfo.getSvcAddr() == null
				|| "".equals(svcPointInfo.getSvcAddr())) {
			message = "请输入地址";
			return false;
		} 
		return true;
	}
	
	public ISvcpointRelationService getSvcpointRelationService() {
		return svcpointRelationService;
	}

	public void setSvcpointRelationService(
			ISvcpointRelationService svcpointRelationService) {
		this.svcpointRelationService = svcpointRelationService;
	}

	public SvcPointRelationInfo getSvcPointRelationInfo() {
		return svcPointRelationInfo;
	}

	public void setSvcPointRelationInfo(SvcPointRelationInfo svcPointRelationInfo) {
		this.svcPointRelationInfo = svcPointRelationInfo;
	}

	public String getSvcCodes() {
		return svcCodes;
	}

	public void setSvcCodes(String svcCodes) {
		this.svcCodes = svcCodes;
	}



	public List<SvcPointInfo> getSvcPointInfolist() {
		return svcPointInfolist;
	}

	public void setSvcPointInfolist(List<SvcPointInfo> svcPointInfolist) {
		this.svcPointInfolist = svcPointInfolist;
	}



	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
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

	public SvcPointInfo getSvcPointInfo() {
		return svcPointInfo;
	}

	public void setSvcPointInfo(SvcPointInfo svcPointInfo) {
		this.svcPointInfo = svcPointInfo;
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

	public ISvcpointService getSvcpointService() {
		return svcpointService;
	}

	public void setSvcpointService(ISvcpointService svcpointService) {
		this.svcpointService = svcpointService;
	}
}
