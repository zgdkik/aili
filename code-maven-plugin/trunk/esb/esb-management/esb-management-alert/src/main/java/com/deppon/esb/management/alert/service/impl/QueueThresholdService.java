package com.deppon.esb.management.alert.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.deppon.esb.management.alert.dao.IQueueThresholdDao;
import com.deppon.esb.management.alert.domain.QueueThresholdInfo;
import com.deppon.esb.management.alert.domain.view.QueueBean;
import com.deppon.esb.management.alert.domain.view.QueueQueryBean;
import com.deppon.esb.management.alert.service.IQueueThresholdService;
import com.deppon.esb.management.user.dao.INoticUserDao;
import com.deppon.esb.management.user.domain.NoticUserInfo;

@Service
@Transactional
public class QueueThresholdService implements IQueueThresholdService {

	@Resource(name="queueThresholdDao")
	IQueueThresholdDao queueThresholdDao;
	
	@Resource(name="noticUserDao")
	private INoticUserDao noticUserDao;
	
	@Override
	public int addQueueThreshold(QueueThresholdInfo info) {
		return queueThresholdDao.addQueueThreshold(info);
	}

	@Override
	public int updateQueueThreshold(QueueThresholdInfo info) {
		return queueThresholdDao.updateQueueThreshold(info);

	}

	@Override
	public int deleteQueueThreshold(List<String> ids) {
		return queueThresholdDao.deleteQueueThreshold(ids);

	}

	@Override
	public List<QueueThresholdInfo> getQueueThresholdInfo(QueueQueryBean bean) {
		return queueThresholdDao.queryQueueThreshold(bean);
	}

	@Override
	public List<QueueBean> getQueueBean(QueueQueryBean bean) {
			List<QueueBean> list = queueThresholdDao.queryQueueBean(bean);
			Map<String,NoticUserInfo> userMap = this.getNoticeUserInfo();//获取所有预警人员
			StringBuffer buffer = null;
			//获取所有预警人员姓名
			for(QueueBean queue:list){
				if(queue.getPersonId()== null || "".equals(queue.getPersonId())){
					continue;
				}
				String ids[]=queue.getPersonId().split(",");
				buffer = new StringBuffer();
				for(int i =0;i<ids.length;i++){
					NoticUserInfo info = userMap.get(ids[i]);
					if(info != null){
						buffer.append(info.getUserName());
						buffer.append(",");
					}
				}
				if(buffer.length() < 1){
					queue.setPersonName("请重新配置新的告警人员");
					continue;
				}
				queue.setPersonName(buffer.substring(0, buffer.length()-1));
			}
		return list;
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
	public Integer getQueueBeanCount(QueueQueryBean bean) {
		return queueThresholdDao.queryQueueBeanCount(bean);
	}	
}
