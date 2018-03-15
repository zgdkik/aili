package org.hbhk.aili.workflow.server.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.hbhk.aili.base.server.context.UserContext;
import org.hbhk.aili.base.share.util.UuidUtil;
import org.hbhk.aili.mybatis.server.support.Page;
import org.hbhk.aili.mybatis.server.support.Pagination;
import org.hbhk.aili.mybatis.server.support.Sort;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.hbhk.aili.workflow.share.entity.WorkflowPublish;
import org.hbhk.aili.workflow.server.service.IWorkflowPublishService;
import org.hbhk.aili.workflow.server.dao.IWorkflowPublishDao;

/**
 * @author  何波
 *
 */
@Service 
public class WorkflowPublishService implements IWorkflowPublishService {

	private  Logger log = LoggerFactory.getLogger(WorkflowPublishService.class);
	
	@Autowired
	private IWorkflowPublishDao workflowPublishDao;

	
	@Override
	@Transactional
	public int insert(WorkflowPublish t) {
		if (StringUtils.isEmpty(t.getId())) {
			t.setCreateTime(new Date());
			t.setId(UuidUtil.getUuid());
			t.setCreateUser(UserContext.getCurrentUser().getUserName());
			workflowPublishDao.insert(t);
		} else {
			t.setUpdateTime(new Date());
			t.setUpdateUser(UserContext.getCurrentUser().getUserName());
			workflowPublishDao.update(t);
		}
		return 1;
	}

	@Override
	@Transactional
	public int update(WorkflowPublish t) {
		return workflowPublishDao.update(t);
	}

	@Override
	public WorkflowPublish getById(String id) {
		return workflowPublishDao.getById(id);
	}

	@Override
	public List<WorkflowPublish> get(Map<String, Object> params) {
		return workflowPublishDao.get(params);
	}

	@Override
	public List<WorkflowPublish> getPage(Map<String, Object> params, int pageNum,
			int pageSize) {
		return workflowPublishDao.getPage(params, pageNum, pageSize);
	}

	@Override
	public int getPageTotalCount(Map<String, Object> params) {
		return workflowPublishDao.getPageTotalCount(params);
	}

	@Override
	@Transactional
	public int deleteById(String id) {
		return workflowPublishDao.deleteById(id);
	}

	@Override
	@Transactional
	public int updateStatusById(String id, int status) {
		return workflowPublishDao.updateStatusById(id, status);
	}

	@Override
	public Pagination<WorkflowPublish> getPagination(Map<String, Object> params,
			Page page, Sort... sorts) {
		return workflowPublishDao.getPagination(params, page, sorts);
	}

	
}
