package com.deppon.esb.management.failure.dao;

import java.util.List;

import com.deppon.esb.management.failure.domain.EsbFailureInfo;
import com.deppon.esb.management.failure.view.EsbFailureInfoQueryBean;
import com.deppon.esb.management.failure.view.EsbFailureInfoView;

/**
 * 失败日志DAO
 */
public interface IEsbFailureDao {

	/**
	 * 保存失败日志.
	 */
	int saveFailure(EsbFailureInfo esbFailureInfo);
	/**
	 * 
	 * 查询失败日志列表信息
	 */
	public List<EsbFailureInfoView> queryEsbFailureLogList(EsbFailureInfoQueryBean queryBean);
	
	/**
	 *  查询搜索总记录数
	 */
	public Integer queryFailureLogCount(EsbFailureInfoQueryBean queryBean);
	
	/**
	 * 
	 * 查询详细失败日志消息
	 */
	public String queryFailureLogBody(String fid);
	/**
	 *
	 * @author HuangHua
	 * @date 2013-6-3 下午4:58:43
	 */
	int saveFailure(List<EsbFailureInfo> esbFailureInfo);
	
}
