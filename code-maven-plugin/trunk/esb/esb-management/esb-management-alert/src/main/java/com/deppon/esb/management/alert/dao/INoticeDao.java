package com.deppon.esb.management.alert.dao;

import java.util.List;
import java.util.Map;

import com.deppon.esb.management.alert.domain.NoticeInfo;
import com.deppon.esb.management.excptn.domain.ExcptnStatisticsInfo;
import com.deppon.esb.management.pfmc.domain.PfmcStatisticsInfo;

/**
 * @Description 预警通知信息DAO
 * @author HuangHua
 * @date 2012-6-6下午3:38:19
 */
public interface INoticeDao {

	public int insertExcptnStatistics(List<ExcptnStatisticsInfo> excptnStatisticsInfos);
	
	public int insertPfmcStatistics(List<PfmcStatisticsInfo> pfmcStatisticsInfos, List<Map<String,Object>> models);
	
	public int insertNotices(List<NoticeInfo> noticeInfos);
	
	public List<NoticeInfo> getNoticesByServiceCode(String serviceCode);
}
