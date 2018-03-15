package com.deppon.esb.management.svccfg.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.deppon.esb.management.svccfg.dao.ISvcPoint2Dao;
import com.deppon.esb.management.svccfg.domain.SvcPoint2Info;
import com.deppon.esb.management.svccfg.domain.view.SvcPoint2QueryBean;
import com.deppon.esb.management.svccfg.service.ISvcpoint2Service;


@Service
@Transactional
public class Svcpoint2Service implements ISvcpoint2Service {

	@Resource
	private ISvcPoint2Dao svcpoint2Dao;
			
	
	@Override
	public int addSvcpoint2(SvcPoint2Info info) {
		
		return svcpoint2Dao.addSvcpoint2(info);
	}

	@Override
	public List<SvcPoint2Info> querySvcpoint2(SvcPoint2QueryBean queryBean) {
	
		return svcpoint2Dao.querySvcpoint2(queryBean);
	}

	@Override
	public Integer queryCount(SvcPoint2QueryBean queryBean) {
		
		return svcpoint2Dao.queryCount(queryBean);
	}

	@Override
	public int updateSvcpoint2(SvcPoint2Info info) {
		
		return svcpoint2Dao.updateSvcpoint2(info);
	}

	@Override
	public int deleteSvcpoint2(List<String> list) {
		return svcpoint2Dao.deleteSvcpoint2(list);
	}
	
	/**
	 * 查询系统编码
	 */
	public List<String> querySysIds(){
		return svcpoint2Dao.querySysIds();
	}
	
	public List<String> queryAgrmt(){
		return svcpoint2Dao.queryAgrmt();
	}
	
	/**
	 * 判断是否存在服务编码
	 */
	public boolean existCode(String code){
		return svcpoint2Dao.existCode(code);
	}
	
	/**
	 * 判断是否存在服务名称
	 */
	public boolean existName(String name){
		return svcpoint2Dao.existName(name);
	}
	
	/**
	 * 查询扩展配置
	 */
	public List<SvcPoint2Info> queryExtendSvcpoint(SvcPoint2QueryBean queryBean){
		return svcpoint2Dao.queryExtendSvcpoint(queryBean);
	}
	/**查询配置总数*/
	public Integer queryExtendCount(SvcPoint2QueryBean queryBean){
		return svcpoint2Dao.queryExtendCount(queryBean);
	}
}
