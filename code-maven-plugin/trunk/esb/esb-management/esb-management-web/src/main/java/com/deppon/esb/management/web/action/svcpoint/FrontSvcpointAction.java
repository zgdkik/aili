package com.deppon.esb.management.web.action.svcpoint;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.deppon.esb.management.svccfg.domain.SvcPointInfo;
import com.deppon.esb.management.svccfg.domain.SvcPointRelationInfo;
import com.deppon.esb.management.svccfg.domain.view.SvcpointBean;
import com.deppon.esb.management.svccfg.domain.view.SvcpointQueryBean;
import com.deppon.esb.management.svccfg.service.ISvcpointRelationService;
import com.deppon.esb.management.svccfg.service.ISvcpointService;

@Controller("frontSvcpointAction")
@Scope("prototype")
public class FrontSvcpointAction {
	/**
	 * 日志
	 */
	protected static final Logger LOGGER = LoggerFactory.getLogger(FrontSvcpointAction.class);

	private SvcPointInfo svcPointInfo;
	private String svcCodes;
	private String message;

	@Resource(name = "svcpointService")
	private ISvcpointService svcpointService;

	@Resource(name = "svcpointRelationService")
	private ISvcpointRelationService svcpointRelationService;

	// 用来表示处理状态,extjs前端需要用这个值来判断处理是否成功
	private String success = "false";

	// 分页相关属性
	private Integer totalCount;

	private Integer start;

	private Integer limit;

	private SvcPointRelationInfo svcPointRelationInfo;
	private SvcpointQueryBean bean;
	private List<SvcpointBean> svcpointBeanlist;
	private List<SvcPointRelationInfo> relationList;
	private String frontSvcCode;
	private String ids;
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

	/**
	 * 查询前端服务配置
	 * 
	 * @return
	 */
	public String queryFrontSvcpoints() {
		try {
			if (bean == null) {
				bean = new SvcpointQueryBean();
			}
			bean.setStart(start);
			bean.setLimit(limit);
			this.totalCount=svcpointService.getSvcpointBeanCount(bean);
			svcpointBeanlist = svcpointService.getSvcpointBean(bean);
			message = "查询成功";
			success = "true";
			return "success";
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return "error";
		}
	}

	/**
	 * 添加前端服务配置
	 */
	public String addFrontSvcpoint() {
		if (check()) {
			if (null != svcpointService.getSvcpointBySvcCode(svcPointInfo
					.getSvcCode())) {
				// 查询是否已经存在同服务名的服务
				message = "服务编码为" + svcPointInfo.getSvcCode() + "的服务配置已经存在！";
			} else {
				svcpointService.addSvcpoint(svcPointInfo, svcPointRelationInfo);
				message = "添加成功";
				this.success = "true";
			}
		}
		return "success";
	}

	/**
	 * 更新前端服务配置
	 */
	public String updateFrontSvcpoint() {
		if (check()) {
			// 前端服务与后端服务已存在
			if (!svcpointRelationService
					.isExistSvcpointRelation(svcPointRelationInfo)) {
				message = "已经存在同样的前后端服务配置关联";
			} else {
				this.svcpointService.addSvcpoint(svcPointInfo,
						svcPointRelationInfo);
				message = "前端服务配置保存成功";
				this.success = "true";
			}
		}
		return "success";
	}

	/**
	 * 根据svcode进行删除
	 */
	public String deleteSvcpoint() {
		if (null == svcCodes || "".equals(svcCodes)) {
			message = "无法获取参数";
		} else {
			String[] array = svcCodes.split(",");
			List<String> svcCodes = Arrays.asList(array);
			svcpointService.deleteSvcPointInfos(svcCodes);
			message = "删除成功";
		}
		return "success";
	}

	/**
	 * 增加前端后端关联
	 * 
	 */
	public String addRelation() {
		if (null == svcPointRelationInfo) {
			message = "解析参数出错";
		} else if (svcpointRelationService
				.isExistSvcpointRelation(svcPointRelationInfo)) {
			message = "已经存在同样的关联关系，不能再增加";
		} else {
			svcpointRelationService.addSvcPointRelation(svcPointRelationInfo);
			message = "操作成功";
			this.success = "true";
		}
		return "success";
	}
	/**
	 * 查询关联关系
	 */
	public String queryRelation(){
		if(frontSvcCode == null || "".equals(frontSvcCode)){
			message ="frontSvcCode 为空,请检查frontSvcCode";
			success="fail";
		}
		else{
			relationList = svcpointService.getSvcpointRelation(frontSvcCode);
			message="操作成功";
			success="success";
		}
		return "success";
	}
	/**
	 * 删除关联关系
	 */

	public String deleteRelation() {
		if(ids == null){
			message = "参数传递错误";
			success="fail";
		}
		else{
			String[] array = ids.split(",");
			svcpointService.deleteSvcpointRelation(Arrays.asList(array));
			message="操作成功";
			success="success";
		}
		return "success";
	}
	
	
	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getFrontSvcCode() {
		return frontSvcCode;
	}

	public void setFrontSvcCode(String frontSvcCode) {
		this.frontSvcCode = frontSvcCode;
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

	public void setSvcPointRelationInfo(
			SvcPointRelationInfo svcPointRelationInfo) {
		this.svcPointRelationInfo = svcPointRelationInfo;
	}

	public String getSvcCodes() {
		return svcCodes;
	}

	public void setSvcCodes(String svcCodes) {
		this.svcCodes = svcCodes;
	}

	public List<SvcpointBean> getSvcpointBeanlist() {
		return svcpointBeanlist;
	}

	public void setSvcpointBeanlist(List<SvcpointBean> svcpointBeanlist) {
		this.svcpointBeanlist = svcpointBeanlist;
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

	public SvcpointQueryBean getBean() {
		return bean;
	}

	public void setBean(SvcpointQueryBean bean) {
		this.bean = bean;
	}

	public List<SvcPointRelationInfo> getRelationList() {
		return relationList;
	}

	public void setRelationList(List<SvcPointRelationInfo> relationList) {
		this.relationList = relationList;
	}
	
}
