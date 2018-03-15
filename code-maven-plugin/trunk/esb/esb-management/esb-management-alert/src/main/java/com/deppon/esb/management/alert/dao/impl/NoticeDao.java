package com.deppon.esb.management.alert.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.deppon.esb.management.alert.dao.INoticeDao;
import com.deppon.esb.management.alert.domain.NoticeInfo;
import com.deppon.esb.management.common.dataaccess.ibatis.IBatis3DaoImpl;
import com.deppon.esb.management.excptn.domain.ExcptnStatisticsInfo;
import com.deppon.esb.management.pfmc.domain.PfmcStatisticsInfo;

@Repository
public class NoticeDao extends IBatis3DaoImpl implements INoticeDao {

	/** 保存异常预警信息 */
	@Override
	public int insertExcptnStatistics(List<ExcptnStatisticsInfo> excptnStatisticsInfos) {
		List<NoticeInfo> noticeInfos = new ArrayList<NoticeInfo>();
		for (ExcptnStatisticsInfo excptnStatisticsInfo : excptnStatisticsInfos) {
			NoticeInfo noticeInfo = new NoticeInfo();
			noticeInfo.setSendTime(new Date());
			noticeInfo.setContent("startTime:" + excptnStatisticsInfo.getStartTime() + ", endTime:" + excptnStatisticsInfo.getEndTime()
					+ ", svcCode:" + excptnStatisticsInfo.getSvcCode() + ", excptnCount:" + excptnStatisticsInfo.getExcptnCount());
			noticeInfo.setStatisticsTime(excptnStatisticsInfo.getCreateTime());
			noticeInfo.setSvcCode(excptnStatisticsInfo.getSvcCode());
			noticeInfo.setType(NoticeInfo.NOTICE_TYPE_EXCEPTION);// 统计类型：0：性能，1：异常，2：队列
			noticeInfos.add(noticeInfo);
		}
		return insertNotices(noticeInfos);
	}

	/** 保存性能预警信息 */
	@Override
	public int insertPfmcStatistics(List<PfmcStatisticsInfo> pfmcStatisticsInfos, List<Map<String, Object>> models) {
		List<NoticeInfo> noticeInfos = new ArrayList<NoticeInfo>();
		for (int i = 0; i < pfmcStatisticsInfos.size(); i++) {
			NoticeInfo noticeInfo = new NoticeInfo();
			noticeInfo.setSendTime(new Date());
			Map<String, Object> map = models.get(i);
			PfmcStatisticsInfo pfmcStatisticsInfo = pfmcStatisticsInfos.get(i);
			noticeInfo.setStatisticsTime(pfmcStatisticsInfo.getCreateTime());
			noticeInfo.setSvcCode(pfmcStatisticsInfo.getSvcCode());
			noticeInfo.setContent("svcName:" + map.get("svcName") + ", svcCode:" + map.get("svcCode") + ", startTime:" + map.get("startTime")
					+ ", endTime:" + map.get("endTime") + ", avgTime:" + map.get("avgTime") + ", threshold:" + map.get("threshold")
					+ ", maxrespTime:" + map.get("maxrespTime"));
			noticeInfo.setType(NoticeInfo.NOTICE_TYPE_PERFORMANCE);// 统计类型：0：性能，1：异常，2：队列
			noticeInfos.add(noticeInfo);
		}
		return insertNotices(noticeInfos);
	}

	/** 保存预警通知信息 */
	@Override
	public int insertNotices(List<NoticeInfo> noticeInfos) {
		for (NoticeInfo noticeInfo : noticeInfos) {
			getSqlSession().insert("com.deppon.esb.management.alert.domain.NoticeInfo.insertNotices", noticeInfo);
		}
		return noticeInfos.size();
	}

	@SuppressWarnings("unchecked")
	public List<NoticeInfo> getNoticesByServiceCode(String serviceCode) {
		return this.getSqlSession().selectList(
				"com.deppon.esb.management.alert.domain.NoticeInfo.getNoticesByServiceCode", 
				serviceCode);
	}
}
