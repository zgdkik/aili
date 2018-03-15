package com.deppon.esb.management.alert.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.deppon.esb.management.alert.dao.IinterfaceThresholdDao;
import com.deppon.esb.management.alert.domain.InterfaceThresholdInfo;
import com.deppon.esb.management.alert.domain.view.InterfaceThresholdBean;
import com.deppon.esb.management.alert.domain.view.InterfaceThresholdQueryBean;
import com.deppon.esb.management.alert.service.IInterfaceThresholdService;
import com.deppon.esb.management.user.dao.INoticUserDao;
import com.deppon.esb.management.user.domain.NoticUserInfo;
@Service
@Transactional
public class InterfaceThresholdService implements IInterfaceThresholdService {
	@Resource(name="interfaceThresholdDao")
	private IinterfaceThresholdDao interfaceThresholdDao;
	
	@Resource(name="noticUserDao")
	private INoticUserDao noticUserDao;

	@Override
	public void addInterfacethreshold(InterfaceThresholdInfo info) {
		interfaceThresholdDao.addInterfacethreshold(info);
	}

	@Override
	public void updateInterfaceThreshold(InterfaceThresholdInfo info) {
		interfaceThresholdDao.updateInterfaceThreshold(info);
	}

	@Override
	public void deleteInterfaceThresholdById(List<String> idList) {
		interfaceThresholdDao.deleteInterfaceThresholdById(idList);
	}

	@Override
	public List<InterfaceThresholdInfo> getInterfaceThreshold(
			InterfaceThresholdQueryBean bean) {
		return interfaceThresholdDao.getInterfaceThreshold(bean);
	}

	@Override
	public List<InterfaceThresholdBean> getThresholdResultBean(
			InterfaceThresholdQueryBean bean) {
		Map<String,NoticUserInfo> userMap = this.getNoticeUserInfo();//获取所有预警人员
		List<InterfaceThresholdBean> list = interfaceThresholdDao.getThresholdResultBean(bean);
		StringBuffer buffer = null;
		//后期改进：从缓存中获取人员信息。
		for(InterfaceThresholdBean  trb :list){
			if(trb.getPersonId() == null){
				continue;
			}
			String ids[]=trb.getPersonId().split(",");
			buffer =  new StringBuffer();
			for(int i =0;i<ids.length;i++){
				NoticUserInfo info = userMap.get(ids[i]);
				if(info != null){
					buffer.append(info.getUserName());
					buffer.append(",");
				}
			}
			trb.setPersonName(buffer.substring(0, buffer.length()-1));
		}
		return list;
	}

	@Override
	public Integer getThresholdResultBeanCount(InterfaceThresholdQueryBean bean) {
		return interfaceThresholdDao.getThresholdResultBeanCount(bean);
	}	
	
	public Map<String,NoticUserInfo> getNoticeUserInfo(){
		Map<String,Object>  condition = new HashMap<String,Object>();
		condition.put("start", 0);
		condition.put("limit", 10000);
		List<NoticUserInfo> list = noticUserDao.queryNoticUsers(condition);
		Map<String,NoticUserInfo> map = new HashMap<String,NoticUserInfo>();
		for(NoticUserInfo info:list){
			map.put(info.getId(),info);
		}
		return map;
	}

	@Override
	public List<InterfaceThresholdBean> getExceptionSet(
			InterfaceThresholdQueryBean bean) {
		Map<String,NoticUserInfo> userMap = this.getNoticeUserInfo();//获取所有预警人员
		List<InterfaceThresholdBean> list = interfaceThresholdDao.getExceptionSet(bean);
		StringBuffer buffer = null;
		//后期改进：从缓存中获取人员信息。
		for(InterfaceThresholdBean  trb :list){
			if(trb.getPersonId() == null){
				continue;
			}
			String ids[]=trb.getPersonId().split(",");
			buffer =  new StringBuffer();
			for(int i =0;i<ids.length;i++){
				NoticUserInfo info = userMap.get(ids[i]);
				if(info != null){
					buffer.append(info.getUserName());
					buffer.append(",");
				}
			}
			trb.setPersonName(buffer.substring(0, buffer.length()-1));
		}
		return list;
	}

	@Override
	public Integer getExceptionSetCount(InterfaceThresholdQueryBean bean) {
		return interfaceThresholdDao.getExceptionSetCount(bean);
	}
}
