package com.deppon.esb.management.svccfg.service;

import java.util.List;
import java.util.Map;

import com.deppon.esb.management.svccfg.domain.SvcPoint2Info;
import com.deppon.esb.management.svccfg.domain.SvcPointInfo;
import com.deppon.esb.management.svccfg.domain.SvcPointRelationInfo;
import com.deppon.esb.management.svccfg.domain.view.SvcPoint2QueryBean;
import com.deppon.esb.management.svccfg.domain.view.SvcpointBean;
import com.deppon.esb.management.svccfg.domain.view.SvcpointQueryBean;

public interface ISvcpoint2Service {
	/**
	 * 新增配置 */
	public int addSvcpoint2(SvcPoint2Info info);
	/**
	 * 查询配置 */
	public List<SvcPoint2Info> querySvcpoint2(SvcPoint2QueryBean queryBean);
	/**查询配置总数*/
	public Integer queryCount(SvcPoint2QueryBean queryBean);
	/**
	 * 更新配置 */
	public int updateSvcpoint2(SvcPoint2Info info);
	/**
	 * 删除配置 */
	public int deleteSvcpoint2(List<String> list);
	/**
	 * 查询系统编码
	 */
	public List<String> querySysIds();
	
	/**
	 * 查询接入协议
	 */
	public List<String> queryAgrmt();
	
	/**
	 * 判断是否存在服务编码
	 */
	public boolean existCode(String code);
	
	/**
	 * 判断是否存在服务名称
	 */
	public boolean existName(String name);
	
	/**
	 * 查询扩展配置
	 */
	public List<SvcPoint2Info> queryExtendSvcpoint(SvcPoint2QueryBean queryBean);
	/**查询配置总数*/
	public Integer queryExtendCount(SvcPoint2QueryBean queryBean);
}