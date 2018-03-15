/**
 * 
 */
package com.deppon.esb.management.svccfg.dao;

import java.util.List;
import java.util.Map;

import com.deppon.esb.management.svccfg.domain.SvcPointInfo;
import com.deppon.esb.management.svccfg.domain.view.SvcpointBean;
import com.deppon.esb.management.svccfg.domain.view.SvcpointQueryBean;

/**
 * @Description 服务DAO接口
 * @author HuangHua
 * @date 2012-03-19 19:43:50
 *
 */
public interface ISvcPointDao {

	/**
	 * @Description 通过服务编码查询性能配置信息
	 * @param 服务编码
	 * @return 性能配置信息
	 */
	public SvcPointInfo loadConfigBySvcCode(String svcCode);
	
//	/**
//	 * 查询所有有效的服务配置信息；
//	 */
//	public List<SvcPointInfo> loadAllConfigs();
	/**
	 * 添加服务配置信息
	 */
	public void addSvcpointInfo(SvcPointInfo info);
	
	/**
	 * 根据服务配置编码删除服务配置信息
	 */
	public void deleteSvcPointInfos(List<String> svccode);
	/**
	 * 更新服务配置信息
	 */
	public void updateSvcPointInfo(SvcPointInfo info);
	
	/**
	 * 根据条件查询配置信息
	 */
	@SuppressWarnings("rawtypes")
	public List<SvcPointInfo> loadConfigByConditions(Map map);
	
	/**
	 * 获取总记录数
	 */
	@SuppressWarnings("rawtypes")
	public Integer getSvcpointTotalCount(Map map);
	
	/**
	 * 查询esb服务管理配置
	 */
	public List<SvcpointBean> getSvcpointBean(SvcpointQueryBean bean);
	
	/**
	 * 根据条件查询esb服务管理配置总数
	 */
	public Integer getSvcpointBeanCount(SvcpointQueryBean bean);
}

