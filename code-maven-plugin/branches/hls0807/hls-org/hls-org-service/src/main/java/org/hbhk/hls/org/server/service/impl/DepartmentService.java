package org.hbhk.hls.org.server.service.impl;

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
import org.hbhk.hls.org.server.dao.IDepartmentDao;
import org.hbhk.hls.org.server.service.IDepartmentService;
import org.hbhk.hls.org.share.entity.DepartmentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService implements IDepartmentService {

	@Autowired
	private IDepartmentDao departmentDao;

	@Override
	public int insert(DepartmentEntity t) {
		String id = t.getId();
		if(StringUtils.isEmpty(id)){
			t.setCreateTime(new Date());
			t.setId(UuidUtil.getUuid());
			t.setCreateUser(UserContext.getCurrentUser().getUserName());
			Map<String, Object> params = new HashMap<>();
			params.put("deptCode", t.getParentDeptCode());
			//修改上级部门为非叶子节点
			List<DepartmentEntity> list =  departmentDao.get(params);
			DepartmentEntity parDepart = new DepartmentEntity();
			parDepart.setId(list.get(0).getId());
			parDepart.setIsLeaf("0");
			departmentDao.update(parDepart);
			t.setIsLeaf("1");
			return departmentDao.insert(t);
		}else{
			t.setUpdateTime(new Date());
			t.setUpdateUser(UserContext.getCurrentUser().getUserName());
			return departmentDao.update(t);
		}
		
	}

	@Override
	public int update(DepartmentEntity t) {
		return departmentDao.update(t);
	}

	@Override
	public DepartmentEntity getById(String id) {
		return departmentDao.getById(id);
	}

	@Override
	public List<DepartmentEntity> get(Map<String, Object> params) {
		return departmentDao.get(params);
	}

	@Override
	public List<DepartmentEntity> getPage(Map<String, Object> params,
			int pageNum, int pageSize) {
		return departmentDao.getPage(params, pageNum, pageSize);
	}

	@Override
	public int getPageTotalCount(Map<String, Object> params) {
		return departmentDao.getPageTotalCount(params);
	}

	@Override
	public int deleteById(String id) {
		return departmentDao.deleteById(id);
	}

	@Override
	public int updateStatusById(String id, int status) {
		return departmentDao.updateStatusById(id, status);
	}

	@Override
	public Pagination<DepartmentEntity> getPagination(
			Map<String, Object> params, Page page, Sort... sorts) {
		return departmentDao.getPagination(params, page, sorts);
	}

	public List<TreeVo> getTree(String parentDeptCode) {
		List<TreeVo> treeVos = new ArrayList<TreeVo>();
		Map<String, Object> params = new HashMap<>();
		if (StringUtils.isEmpty(parentDeptCode)) {
			parentDeptCode = "root";
		}
		params.put("parentDeptCode", parentDeptCode);
		params.put("status", 1);
		List<DepartmentEntity> coordinateVos = departmentDao.get(params);
		if (coordinateVos != null) {
			for (DepartmentEntity d : coordinateVos) {
				TreeVo t = new TreeVo();
				t.setId(d.getDeptCode());
				t.setName(d.getDeptName());
				if("1".equals(d.getIsLeaf())){
					t.setParent(false);
				}else{
					t.setParent(true);
				}
				treeVos.add(t);
			}
		}
		// 网点信息
		return treeVos;
	}
}
