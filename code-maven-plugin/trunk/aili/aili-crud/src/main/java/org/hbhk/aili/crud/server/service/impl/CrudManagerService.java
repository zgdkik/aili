package org.hbhk.aili.crud.server.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hbhk.aili.base.server.context.UserContext;
import org.hbhk.aili.base.share.util.UuidUtil;
import org.hbhk.aili.cache.server.CacheManager;
import org.hbhk.aili.cache.server.ICache;
import org.hbhk.aili.core.share.ex.BusinessException;
import org.hbhk.aili.crud.server.cache.CrudCache;
import org.hbhk.aili.crud.server.dao.IColumDao;
import org.hbhk.aili.crud.server.dao.IConditionDao;
import org.hbhk.aili.crud.server.dao.ICrudDao;
import org.hbhk.aili.crud.server.service.ICrudManagerService;
import org.hbhk.aili.crud.share.entity.ColumnEntity;
import org.hbhk.aili.crud.share.entity.Condition;
import org.hbhk.aili.crud.share.entity.CrudEntity;
import org.hbhk.aili.mybatis.server.support.Page;
import org.hbhk.aili.mybatis.server.support.Pagination;
import org.hbhk.aili.mybatis.server.support.Sort;
import org.hbhk.aili.user.server.service.IPrivilegeService;
import org.hbhk.aili.user.share.entity.PrivilegeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CrudManagerService implements ICrudManagerService {

	@Autowired
	private ICrudDao crudDao;

	@Autowired
	private IPrivilegeService privilegeService;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private IConditionDao conditionDao;

	@Autowired
	private IColumDao columDao;

	@Override
	public int insert(CrudEntity t) {
		String id = t.getId();
		int num = 0;
		PrivilegeEntity privilege = new PrivilegeEntity();
		privilege.setPrivilegeCode(t.getCode());
		privilege.setType("menu");
		privilege.setUrl("/crud/" + t.getCode());
		privilege.setPrivilegeName(t.getName());
		privilege.setParentCode(t.getParentMenuCode());
		if (StringUtils.isEmpty(id)) {
			//校验编码
			Map<String, Object> params =new HashMap<>();
			params.put("code", t.getCode());
			List<CrudEntity> list = crudDao.get(params);
			if(list!=null && list.size()>0){
				throw new BusinessException("编码已经存在");
			}
			t.setCreateTime(new Date());
			id = UuidUtil.getUuid();
			t.setId(id);
			t.setCreateUser(UserContext.getCurrentUser().getUserName());
			privilege.setId(id);
			num = crudDao.insert(t);
			privilegeService.addMenuService(privilege);
		} else {
			t.setCode(null);
			t.setUpdateTime(new Date());
			t.setUpdateUser(UserContext.getCurrentUser().getUserName());
			num = crudDao.update(t);
			Map<String, Object> map =new HashMap<>();
		    map.put("privilegeCode", privilege.getPrivilegeCode());
			PrivilegeEntity  privilegeDb = privilegeService.getMenu(map);
			privilege.setId(privilegeDb.getId());
			privilegeService.updateMenuService(privilege);
		}
		return num;
	}

	@Override
	public int update(CrudEntity t) {
		return 0;
	}

	@Override
	public CrudEntity getById(String id) {
		return crudDao.getById(id);
	}

	@Override
	public List<CrudEntity> get(Map<String, Object> params) {
		return null;
	}

	@Override
	public List<CrudEntity> getPage(Map<String, Object> params, int pageNum,
			int pageSize) {
		return null;
	}

	@Override
	public int getPageTotalCount(Map<String, Object> params) {
		return 0;
	}

	@Override
	public int deleteById(String id) {
		CrudEntity curd=  getById(id);
		Condition delCondition = new Condition();
		delCondition.setCrudCode(curd.getCode());
		conditionDao.deleteByObj(delCondition);
		ColumnEntity delColumn = new ColumnEntity();
		delColumn.setCrudCode(curd.getCode());
		columDao.deleteByObj(delColumn);
		return crudDao.deleteById(id);
	}

	@Override
	public int updateStatusById(String id, int status) {
		return crudDao.updateStatusById(id, status);
	}

	@Override
	public Pagination<CrudEntity> getPagination(Map<String, Object> params,
			Page page, Sort... sorts) {
		return crudDao.getPagination(params, page, sorts);
	}

	@Override
	@Transactional
	public void edit(CrudEntity report, ColumnEntity[] cols, Condition[] cdts) {
		
		ICache<String, CrudEntity> crudCache = CacheManager.getInstance().getCache(CrudCache.REPORT_CACHE_ID);
		crudCache.invalid(report.getCode());
		Condition delCondition = new Condition();
		delCondition.setCrudCode(report.getCode());
		conditionDao.deleteByObj(delCondition);
		
		ColumnEntity delColumn = new ColumnEntity();
		delColumn.setCrudCode(report.getCode());
		columDao.deleteByObj(delColumn);
		String code = report.getCode();
		insert(report);
		if (cols != null) {
			for (Condition condition : cdts) {
				condition.setCrudCode(code);
				condition.setId(UuidUtil.getUuid());
				condition.setCreateTime(new Date());
				conditionDao.insert(condition);
			}
		}

		if (cdts != null) {
			for (ColumnEntity col : cols) {
				col.setCrudCode(code);
				col.setId(UuidUtil.getUuid());
				col.setCreateTime(new Date());
				columDao.insert(col);
			}
		}
	}

}
