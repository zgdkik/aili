package org.hbhk.aili.crud.server.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hbhk.aili.cache.server.CacheSupport;
import org.hbhk.aili.crud.server.dao.IColumDao;
import org.hbhk.aili.crud.server.dao.IConditionDao;
import org.hbhk.aili.crud.server.dao.ICrudDao;
import org.hbhk.aili.crud.share.entity.ColumnEntity;
import org.hbhk.aili.crud.share.entity.Condition;
import org.hbhk.aili.crud.share.entity.CrudEntity;
import org.hbhk.aili.crud.share.vo.CrudVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CrudCache extends CacheSupport<CrudVo> {

	public static final String REPORT_CACHE_ID = "REPORT_CACHE_ID";

	@Autowired
	private ICrudDao crudDao;

	@Autowired
	private IColumDao columDao;

	@Autowired
	private IConditionDao conditionDao;
	
	@Override
	public String getCacheId() {
		return REPORT_CACHE_ID;
	}

	@Override
	public CrudVo doSet(String key) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("code", key);
		params.put("status", 1);
		List<CrudEntity> list = crudDao.get(params);
		if (list == null || list.isEmpty()) {
			return null;
		}
		params.clear();
		params.put("crudCode", key);
		List<Condition> cdts  = conditionDao.get(params);
		List<ColumnEntity> cols = columDao.get(params);
		CrudEntity crud = list.get(0);
		CrudVo crudVo = new CrudVo();
		crudVo.setCrud(crud);
		crudVo.setColumns(cols);
		crudVo.setConditions(cdts);
		return crudVo;
	}

}
