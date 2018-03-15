/**
 * 
 */
package com.deppon.esb.management.svccfg.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.deppon.esb.management.common.dataaccess.ibatis.IBatis3DaoImpl;
import com.deppon.esb.management.svccfg.dao.ISvcPointDao;
import com.deppon.esb.management.svccfg.domain.SvcPointInfo;
import com.deppon.esb.management.svccfg.domain.view.SvcpointBean;
import com.deppon.esb.management.svccfg.domain.view.SvcpointQueryBean;

/**
 * @Description 服务DAO实现
 * @author HuangHua
 * @date 2012-03-19 19:47:27
 *
 */
@Repository
public class SvcPointDao extends IBatis3DaoImpl implements ISvcPointDao{

	/* (non-Javadoc)
	 * @see com.deppon.esb.management.dao.ISvcPointDao#loadConfigBySvcCode(java.lang.String)
	 */
	//modify by HuangHua 2012-7-11  添加空判断，如果svcCode为空，则直接返回null
	@Override
	public SvcPointInfo loadConfigBySvcCode(String svcCode) {
		if(svcCode != null && !"".equals(svcCode) ){
			SvcPointInfo svcPointInfo;
			svcPointInfo = (SvcPointInfo) getSqlSession().selectOne("com.deppon.esb.management.svccfg.domain.SvcPointInfo.loadConfigBySvcCode",svcCode);
			return svcPointInfo;
		}else{
			return null;
		}
	}

//	@SuppressWarnings("unchecked")
//	@Override
//	public List<SvcPointInfo> loadAllConfigs() {
//		return (List<SvcPointInfo>)getSqlSession().selectList(
//				"com.deppon.esb.management.svccfg.domain.SvcPointInfo.loadAllConfigs", 
//				SvcPointInfo.class);
//	}

	@Override
	public void addSvcpointInfo(SvcPointInfo info) {
		this.getSqlSession().insert("com.deppon.esb.management.svccfg.domain.SvcPointInfo.saveSvcpoint", info);
	}

	@Override
	public void deleteSvcPointInfos(List<String> list) {
		for(String svcCode:list){
			this.getSqlSession().delete("com.deppon.esb.management.svccfg.domain.SvcPointInfo.deleteSvcpoint", svcCode);
		}
	}

	@Override
	public void updateSvcPointInfo(SvcPointInfo info) {
		this.getSqlSession().update("com.deppon.esb.management.svccfg.domain.SvcPointInfo.updateSvcpoint", info);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<SvcPointInfo> loadConfigByConditions(Map map) {
		RowBounds rowBounds = new RowBounds((Integer)map.get("start"),(Integer)map.get("limit"));
		return this.getSqlSession().selectList("com.deppon.esb.management.svccfg.domain.SvcPointInfo.loadSvcpointByConditions", map,rowBounds);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Integer getSvcpointTotalCount(Map map) {
		return (Integer)this.getSqlSession().selectOne("com.deppon.esb.management.svccfg.domain.SvcPointInfo.getTotalCount",map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SvcpointBean> getSvcpointBean(SvcpointQueryBean bean) {
		RowBounds rowBounds = new RowBounds((Integer)bean.getStart(),bean.getLimit());
		return this.getSqlSession().selectList("com.deppon.esb.management.svccfg.domain.SvcPointInfo.getSvcpointBean", bean,rowBounds);
	}

	@Override
	public Integer getSvcpointBeanCount(SvcpointQueryBean bean) {
		return (Integer)this.getSqlSession().selectOne("com.deppon.esb.management.svccfg.domain.SvcPointInfo.getSvcpointBeanCount", bean);
	}

}
