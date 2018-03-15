package com.deppon.esb.management.pfmc.dao.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.deppon.esb.management.common.dataaccess.ibatis.IBatis3DaoImpl;
import com.deppon.esb.management.pfmc.dao.IPerformanceDao;
import com.deppon.esb.management.pfmc.domain.PerformanceInfo;

/**
 * 性能日志信息DAO接口实现.
 * @author HuangHua
 * @date 2012-12-29 下午3:41:33
 */
@Repository
public class PerformanceDao extends IBatis3DaoImpl implements IPerformanceDao {

	/**
	 * 根据服务名和服务提供者查询性能信息列表.
	 * 
	 * @param svcName
	 *            the svc name
	 * @param svcProvider
	 *            the svc provider
	 * @return the list< performance info>
	 * @author HuangHua
	 * @date 2012-12-29 下午3:38:36
	 * @see com.deppon.esb.management.pfmc.dao.IPerformanceDao#queryPerformanceList(java.lang.String,
	 *      java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PerformanceInfo> queryPerformanceList(String svcName, String svcProvider) {
		List<PerformanceInfo> list = null;
		Map<String, Object> paras = new HashMap<String, Object>();
		paras.put("svcName", svcName);
		paras.put("svcProvider", svcProvider);
		list = getSqlSession().selectList("com.deppon.esb.management.pfmc.domain.PerformanceInfo.queryPerformanceList",
				paras);
		return list;
	}

	/**
	 * Save performance.
	 * 
	 * @param performanceInfo
	 *            the performance info
	 * @author HuangHua
	 * @date 2012-12-29 下午3:38:36
	 * @see com.deppon.esb.management.pfmc.dao.IPerformanceDao#savePerformance(com.deppon.esb.management.pfmc.domain.PerformanceInfo)
	 */
	@Override
	public void savePerformance(PerformanceInfo performanceInfo) {
		try {
			getSqlSession().insert("com.deppon.esb.management.pfmc.domain.PerformanceInfo.savePerformance", performanceInfo);
		} catch (Throwable t) {
			logger.error(t.getMessage(), t);
		}
	}

	/**
	 * 保存性能信息列表.
	 * 
	 * @param list
	 *            the list
	 * @author HuangHua
	 * @date 2012-12-29 下午3:38:36
	 * @see com.deppon.esb.management.pfmc.dao.IPerformanceDao#storePerformanceInfoList(java.util.List)
	 */
	@Override
	public void storePerformanceInfoList(List<PerformanceInfo> list) {
		// 不要自作聪明的认为for循环的性能差，在mybatis的世界里，你可以写demo测试这样做好不好
		for (Iterator<PerformanceInfo> iterator = list.iterator(); iterator.hasNext();) {
			PerformanceInfo performanceInfo = (PerformanceInfo) iterator.next();
			savePerformance(performanceInfo);
		}
	}

}
