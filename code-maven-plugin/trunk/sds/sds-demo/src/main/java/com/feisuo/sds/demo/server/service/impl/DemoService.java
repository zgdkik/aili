package com.feisuo.sds.demo.server.service.impl;

import java.util.List;
import java.util.Map;

import org.hbhk.aili.core.share.ex.BusinessException;
import org.hbhk.aili.mybatis.server.support.Page;
import org.hbhk.aili.mybatis.server.support.Pagination;
import org.hbhk.aili.mybatis.server.support.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.feisuo.sds.base.share.vo.QueryPageVo;
import com.feisuo.sds.demo.server.dao.IDemoDao;
import com.feisuo.sds.demo.server.service.IDemoService;
import com.feisuo.sds.demo.share.entity.DemoEntity;

@Service
@Transactional
public class DemoService implements IDemoService {

	@Autowired
	private IDemoDao demoDao;

	@Transactional(readOnly = true)
	@Override
	public DemoEntity getDemo(String id) {
		return demoDao.getById(id);
	}

	@Transactional(readOnly = true)
	@Override
	public Pagination<DemoEntity> getPage(QueryPageVo queryPageVo) {
		if( queryPageVo==null){
			throw new BusinessException("不允许为空");
		}
		Pagination<DemoEntity> pagination = demoDao.getPagination(queryPageVo.getParaMap(),
				queryPageVo.getPage(),queryPageVo.getSorts());
		return pagination;
	}

	@Override
	public void save(DemoEntity demo) {
		demoDao.insert(demo);
	}

	@Override
	public int insert(DemoEntity t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(DemoEntity t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public DemoEntity getById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DemoEntity> get(Map<String, Object> params) {
		return demoDao.get(params);
	}

	@Override
	public List<DemoEntity> getPage(Map<String, Object> params, int pageNum,
			int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPageTotalCount(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteById(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateStatusById(String id, int status) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Pagination<DemoEntity> getPagination(Map<String, Object> params,
			Page page, Sort... sorts) {
		// TODO Auto-generated method stub
		return null;
	}

}
