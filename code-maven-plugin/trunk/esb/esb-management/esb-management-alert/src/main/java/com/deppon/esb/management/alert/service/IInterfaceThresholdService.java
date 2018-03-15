package com.deppon.esb.management.alert.service;

import java.util.List;

import com.deppon.esb.management.alert.domain.InterfaceThresholdInfo;
import com.deppon.esb.management.alert.domain.view.InterfaceThresholdBean;
import com.deppon.esb.management.alert.domain.view.InterfaceThresholdQueryBean;

public interface IInterfaceThresholdService {
	//新增
	public void addInterfacethreshold(InterfaceThresholdInfo info);
	//更新
	public void updateInterfaceThreshold(InterfaceThresholdInfo info);
	//根据id list 删除
	public void deleteInterfaceThresholdById(List<String> idList);
	//查询
	public  List<InterfaceThresholdInfo >getInterfaceThreshold(InterfaceThresholdQueryBean bean);
	public List<InterfaceThresholdBean> getThresholdResultBean(InterfaceThresholdQueryBean bean);
	//查询性能监控设置记录数量
	public Integer getThresholdResultBeanCount(InterfaceThresholdQueryBean bean);
	
	//查询异常监控设置记录
	public List<InterfaceThresholdBean> getExceptionSet(InterfaceThresholdQueryBean bean);
	//查询异常监控设置记录数量
	public Integer getExceptionSetCount(InterfaceThresholdQueryBean bean);
}
