/**
 * 
 */
package com.deppon.esb.management.svccfg.dao.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.deppon.esb.management.common.dataaccess.ibatis.IBatis3DaoImpl;
import com.deppon.esb.management.svccfg.dao.ISvcPoint2Dao;
import com.deppon.esb.management.svccfg.domain.SvcPoint2Info;
import com.deppon.esb.management.svccfg.domain.view.SvcPoint2QueryBean;

/**
 *esb 实现类
 */
@Repository
public class SvcPoint2Dao extends IBatis3DaoImpl implements ISvcPoint2Dao{

	@Override
	public int addSvcpoint2(SvcPoint2Info info) {
		return getSqlSession().insert("com.deppon.esb.management.svccfg.domain.SvcPoint2Info.insertSvcpoint", info);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SvcPoint2Info> querySvcpoint2(SvcPoint2QueryBean queryBean) {
		RowBounds rowBounds = new RowBounds(queryBean.getStart(),queryBean.getLimit());
		return getSqlSession().selectList("com.deppon.esb.management.svccfg.domain.SvcPoint2Info.querySvcpoint", queryBean,rowBounds);
	}

	@Override
	public int updateSvcpoint2(SvcPoint2Info info) {
		return getSqlSession().update("com.deppon.esb.management.svccfg.domain.SvcPoint2Info.updateSvcpoint",info );
	}

	@Override
	public int deleteSvcpoint2(List<String> code) {
		return getSqlSession().delete("com.deppon.esb.management.svccfg.domain.SvcPoint2Info.deleteSvcpoint", code);
	}
	@Override
	public Integer queryCount(SvcPoint2QueryBean queryBean){
		return (Integer)getSqlSession().selectOne("com.deppon.esb.management.svccfg.domain.SvcPoint2Info.querySvcpointCount", queryBean);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> querySysIds() {	
		return getSqlSession().selectList("com.deppon.esb.management.svccfg.domain.SvcPoint2Info.querySysIds");
	}
	
	@SuppressWarnings("unchecked")
	public List<String> queryAgrmt(){
		return getSqlSession().selectList("com.deppon.esb.management.svccfg.domain.SvcPoint2Info.queryAgrmt");
	}
	
	/**
	 * 判断是否存在服务编码
	 */
	public boolean existCode(String code){
		Integer count=(Integer)getSqlSession().selectOne("com.deppon.esb.management.svccfg.domain.SvcPoint2Info.existCode",code);
		return (count>0);
	}
	
	/**
	 * 判断是否存在服务名称
	 */
	public boolean existName(String name){
		Integer count=(Integer)getSqlSession().selectOne("com.deppon.esb.management.svccfg.domain.SvcPoint2Info.existName",name);
		return (count>0);
	}
	/**
	 * 查询扩展配置
	 */
	@SuppressWarnings("unchecked")
	public List<SvcPoint2Info> queryExtendSvcpoint(SvcPoint2QueryBean queryBean){
		RowBounds rowBounds = new RowBounds(queryBean.getStart(),queryBean.getLimit());
		return getSqlSession().selectList("com.deppon.esb.management.svccfg.domain.SvcPoint2Info.queryExtendSvcpoint", queryBean,rowBounds);

	}
	/**查询配置总数*/
	public Integer queryExtendCount(SvcPoint2QueryBean queryBean){
		return (Integer)getSqlSession().selectOne("com.deppon.esb.management.svccfg.domain.SvcPoint2Info.queryExtendSvcpointCount", queryBean);
	}
}
