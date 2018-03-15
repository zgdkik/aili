package org.hbhk.aili.org.server.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hbhk.aili.base.server.context.UserContext;
import org.hbhk.aili.base.share.util.UuidUtil;
import org.hbhk.aili.base.share.vo.TreeVo;
import org.hbhk.aili.mybatis.server.support.Page;
import org.hbhk.aili.mybatis.server.support.Pagination;
import org.hbhk.aili.mybatis.server.support.Sort;
import org.hbhk.aili.org.server.dao.IRegionDao;
import org.hbhk.aili.org.server.service.IRegionService;
import org.hbhk.aili.org.share.entity.Region;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author  何波
 *
 */
@Service 
public class RegionService implements IRegionService {

	private  Logger log = LoggerFactory.getLogger(RegionService.class);
	
	@Autowired
	private IRegionDao regionDao;

	
	@Override
	@Transactional
	public int insert(Region t) {
		if (StringUtils.isEmpty(t.getId())) {
			t.setCreateTime(new Date());
			t.setId(UuidUtil.getUuid());
			t.setCreateUser(UserContext.getCurrentUser().getUserName());
			regionDao.insert(t);
		} else {
			t.setUpdateTime(new Date());
			t.setUpdateUser(UserContext.getCurrentUser().getUserName());
			regionDao.update(t);
		}
		return 1;
	}

	@Override
	@Transactional
	public int update(Region t) {
		return regionDao.update(t);
	}

	@Override
	public Region getById(String id) {
		return regionDao.getById(id);
	}

	@Override
	public List<Region> get(Map<String, Object> params) {
		return regionDao.get(params);
	}

	@Override
	public List<Region> getPage(Map<String, Object> params, int pageNum,
			int pageSize) {
		return regionDao.getPage(params, pageNum, pageSize);
	}

	@Override
	public int getPageTotalCount(Map<String, Object> params) {
		return regionDao.getPageTotalCount(params);
	}

	@Override
	@Transactional
	public int deleteById(String id) {
		return regionDao.deleteById(id);
	}

	@Override
	@Transactional
	public int updateStatusById(String id, int status) {
		return regionDao.updateStatusById(id, status);
	}

	@Override
	public Pagination<Region> getPagination(Map<String, Object> params,
			Page page, Sort... sorts) {
		return regionDao.getPagination(params, page, sorts);
	}

	public List<TreeVo> getTree(String parentDeptCode) {
		List<TreeVo> treeVos = new ArrayList<TreeVo>();
		Map<String, Object> params = new HashMap<>();
		if (StringUtils.isEmpty(parentDeptCode)) {
			parentDeptCode = "0";
		}
		params.put("parentId", parentDeptCode);
		params.put("status", 1);
		List<Region> coordinateVos = regionDao.get(params);
		if (coordinateVos != null) {
			for (Region d : coordinateVos) {
				TreeVo t = new TreeVo();
				t.setId(d.getId());
				t.setName(d.getName());
				if(!"3".equals(d.getLevelType())){
					t.setParent(true);
				}
				treeVos.add(t);
			}
		}
		// 网点信息
		return treeVos;
	}
}
