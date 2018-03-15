package com.deppon.esb.management.alert.dao;

import java.util.List;

import com.deppon.esb.management.alert.domain.InterfaceThresholdInfo;
import com.deppon.esb.management.alert.domain.view.InterfaceThresholdBean;
import com.deppon.esb.management.alert.domain.view.InterfaceThresholdQueryBean;

public interface IinterfaceThresholdDao {

	double getThresholdBySvcCode(String svcCode);

	/**
	 * @Description 查询邮件
	 * @param svcCode
	 * @param type
	 *            0-性能，1-异常
	 * @return
	 */
	String[] queryExcptnEmailBySvcCodeAndType(String svcCode, int type);

	// 新增
	void addInterfacethreshold(InterfaceThresholdInfo info);

	// 更新
	void updateInterfaceThreshold(InterfaceThresholdInfo info);

	// 根据id list 删除
	void deleteInterfaceThresholdById(List<String> idList);

	// 查询
	List<InterfaceThresholdInfo> getInterfaceThreshold(
			InterfaceThresholdQueryBean bean);

	List<InterfaceThresholdBean> getThresholdResultBean(
			InterfaceThresholdQueryBean bean);

	// 根据条件查询监控配置总数
	Integer getThresholdResultBeanCount(InterfaceThresholdQueryBean bean);

	// 取得性能监控设置列表；
	List<InterfaceThresholdInfo> getThresholdForPerformance(
			InterfaceThresholdQueryBean bean);

	// 取得异常监控设置列表；
	List<InterfaceThresholdInfo> getThresholdForException(
			InterfaceThresholdQueryBean bean);
	//取得异常监控设置列表
	List<InterfaceThresholdBean> getExceptionSet(
			InterfaceThresholdQueryBean bean);
	//取得异常监控设置数量
	Integer getExceptionSetCount(InterfaceThresholdQueryBean bean);

	/**
	 * 查询所有状态不完整的告警信息
	 * @author HuangHua
	 * @date 2013-5-14 下午4:34:43
	 */
	InterfaceThresholdInfo getThresholdForStatusNoComplete(
			InterfaceThresholdQueryBean condition);
}
