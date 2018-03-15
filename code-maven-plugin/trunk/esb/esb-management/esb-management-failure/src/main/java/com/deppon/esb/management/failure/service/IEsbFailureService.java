package com.deppon.esb.management.failure.service;

import java.util.List;

import com.deppon.esb.management.failure.domain.EsbFailureInfo;
import com.deppon.esb.management.failure.view.EsbFailureInfoQueryBean;
import com.deppon.esb.management.failure.view.EsbFailureInfoView;

/**
 * 失败消息服务类
 */
public interface IEsbFailureService {

	/**
	 * 保存失败日志
	 */
	int saveFailure(EsbFailureInfo esbFailureInfo);
	
	/**
	 * 根据查询条件 查询失败日志信息 
	 */
	List<EsbFailureInfoView> queryEsbFailureLogList(EsbFailureInfoQueryBean queryBean);
	
	/**
	 * 查询详细失败信息
	 */
	String queryFailureLogBody(String fid);
	
	/**
	 * 查询总记录数
	 */
	Integer queryFailureLogCount(EsbFailureInfoQueryBean queryBean);

	/**
	 *
	 * @author HuangHua
	 * @date 2013-6-3 下午4:51:59
	 */
	int saveFailure(List<EsbFailureInfo> esbFailureInfo);
	
}
