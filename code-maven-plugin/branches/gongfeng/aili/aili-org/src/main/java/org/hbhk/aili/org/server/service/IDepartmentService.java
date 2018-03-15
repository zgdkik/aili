package org.hbhk.aili.org.server.service;

import java.util.List;

import org.hbhk.aili.base.server.service.IBaseService;
import org.hbhk.aili.base.share.vo.TreeVo;
import org.hbhk.aili.org.share.entity.DepartmentEntity;

public interface IDepartmentService extends IBaseService<DepartmentEntity, String> {
	
	List<TreeVo> getTree(String parentDeptCode);

}
