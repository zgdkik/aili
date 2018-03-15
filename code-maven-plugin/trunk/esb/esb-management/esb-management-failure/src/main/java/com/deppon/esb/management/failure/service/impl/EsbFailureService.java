package com.deppon.esb.management.failure.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.deppon.esb.management.failure.dao.IEsbFailureDao;
import com.deppon.esb.management.failure.domain.EsbFailureInfo;
import com.deppon.esb.management.failure.service.IEsbFailureService;
import com.deppon.esb.management.failure.view.EsbFailureInfoQueryBean;
import com.deppon.esb.management.failure.view.EsbFailureInfoView;

/**
 * 失败消息处理实现类
 */
@Transactional
@Service
public class EsbFailureService implements IEsbFailureService {
	
	@Resource
	private IEsbFailureDao esbFailureDao;

	/** 
	 * 保存失败日志
	 */
	@Override
	public int saveFailure(EsbFailureInfo esbFailureInfo) {
		return esbFailureDao.saveFailure(esbFailureInfo);
	}

	/**
	 *  根据条件 查失败日志
	 */
	@Override
	public List<EsbFailureInfoView> queryEsbFailureLogList(
			EsbFailureInfoQueryBean queryBean) {
		return esbFailureDao.queryEsbFailureLogList(queryBean);
	}

	/**
	 * 查询详细失败信息
	 */
	@Override
	public String queryFailureLogBody(String fid) {
		return esbFailureDao.queryFailureLogBody(fid);
	}

	/**
	 * 查询过滤总记录数
	 */
	public Integer queryFailureLogCount(EsbFailureInfoQueryBean queryBean){
		return esbFailureDao.queryFailureLogCount(queryBean);
	}

	/** 
	 * 
	 * @author HuangHua
	 * @date 2013-6-3 下午4:57:17
	 * @see com.deppon.esb.management.failure.service.IEsbFailureService#saveFailure(java.util.List)
	 */
	@Override
	public int saveFailure(List<EsbFailureInfo> esbFailureInfo) {
		return esbFailureDao.saveFailure(esbFailureInfo);
	}
	
}
