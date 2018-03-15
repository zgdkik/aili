package com.feisuo.sds.common.server.service.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.Page;
import org.mybatis.spring.support.Pagination;
import org.mybatis.spring.support.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feisuo.sds.common.server.dao.IDictDao;
import com.feisuo.sds.common.server.service.IDictService;
import com.feisuo.sds.common.share.entity.DictEntity;
import com.feisuo.sds.common.share.entity.DictValueEntity;

@Service
public class DictService implements IDictService {

	
	@Autowired
	private IDictDao dictDao;
	
	@Override
	public int insert(DictEntity t) {
		// TODO Auto-generated method stub
		return dictDao.insert(t);
	}

	@Override
	public int update(DictEntity t) {
		// TODO Auto-generated method stub
		return dictDao.update(t);
	}

	@Override
	public DictEntity getById(String id) {
		// TODO Auto-generated method stub
		return dictDao.getById(id);
	}

	@Override
	public List<DictEntity> get(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return dictDao.get(params);
	}

	@Override
	public List<DictEntity> getPage(Map<String, Object> params, int pageNum,
			int pageSize) {
		// TODO Auto-generated method stub
		return dictDao.getPage(params, pageNum, pageSize);
	}

	@Override
	public int getPageTotalCount(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return dictDao.getPageTotalCount(params);
	}

	@Override
	public int deleteById(String id) {
		// TODO Auto-generated method stub
		return dictDao.deleteById(id);
	}

	@Override
	public int updateStatusById(String id, int status) {
		// TODO Auto-generated method stub
		return dictDao.updateStatusById(id, status);
	}

	@Override
	public Pagination<DictEntity> getPagination(Map<String, Object> params,
			Page page, Sort... sorts) {
		// TODO Auto-generated method stub
		return dictDao.getPagination(params, page, sorts);
	}

	@Override
	public int editDictValueStatusService(DictValueEntity dictValueEntity) {
		// TODO Auto-generated method stub
		return dictDao.editDictValueStatus(dictValueEntity);
	}

	
}
