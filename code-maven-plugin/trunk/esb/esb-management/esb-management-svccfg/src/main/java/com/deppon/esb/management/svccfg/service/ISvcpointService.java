package com.deppon.esb.management.svccfg.service;

import java.util.List;
import java.util.Map;

import com.deppon.esb.management.svccfg.domain.SvcPointInfo;
import com.deppon.esb.management.svccfg.domain.SvcPointRelationInfo;
import com.deppon.esb.management.svccfg.domain.view.SvcpointBean;
import com.deppon.esb.management.svccfg.domain.view.SvcpointQueryBean;

public interface ISvcpointService {
	/**
	 * 新增服务配置
	 * @param info
	 */
	public void addSvcpoint(SvcPointInfo info);
	/**
	 * 更新服务配置
	 * @param info
	 */
	public void updateSvcpoint(SvcPointInfo info);
//	public List<SvcPointInfo> getSvcpoints();
	/**
	 * 根据id查询服务配置
	 */
	public SvcPointInfo getSvcpointBySvcCode(String str);
	/**
	 * 根据id删除服务配置
	 * @param svcCodes
	 */
	public void deleteSvcPointInfos(List<String> svcCodes);
	
	@SuppressWarnings("rawtypes")
	public Integer getTotalCount(Map map);
	@SuppressWarnings("rawtypes")
	public List<SvcPointInfo> getSvcpointsByConditions(Map map);
	//添加服务
	public void addSvcpoint(SvcPointInfo info, SvcPointRelationInfo svcPointRelationInfo);
	/**
	 * 根据QueryBean查询esb服务管理配置
	 */
	public List<SvcpointBean> getSvcpointBean(SvcpointQueryBean bean);
	
	/**
	 * 根据QueryBean查询esb服务管理配置总数
	 */
	public Integer getSvcpointBeanCount(SvcpointQueryBean bean);
	/**
	 * 查询是否存在svcpointrelation
	 */
	public boolean isSvcpointRelationExisit(SvcPointRelationInfo info);
	
	/**
	 * 新增映射
	 */
	public void addSvcpointRelation(SvcPointRelationInfo info);
	/**
	 * 删除映射
	 */
	public Integer deleteSvcpointRelation(List<String> ids);
	/**
	 * 根据esb端svccode查询映射关系
	 */
	public List<SvcPointRelationInfo> getSvcpointRelation(String frontSvcCode);
	/**
	 *
	 * @author HuangHua
	 * @date 2013-5-4 下午4:29:08
	 */
	public SvcPointInfo loadConfigBySvcCode(String serviceCode);
}
