package com.yimidida.ows.common.server.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.Page;
import org.mybatis.spring.support.Pagination;
import org.mybatis.spring.support.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.yimidida.ows.base.server.context.UserContext;
import com.yimidida.ows.base.share.vo.TreeVo;
import com.yimidida.ows.common.server.dao.IDictDao;
import com.yimidida.ows.common.server.service.IDictService;
import com.yimidida.ows.common.share.entity.DictEntity;
import com.yimidida.ows.common.util.Constants;
import com.yimidida.ows.common.util.UuidUtil;
import com.yimidida.ymdp.core.share.ex.BusinessException;

@Service
@Transactional
public class DictService implements IDictService {

	
	@Autowired
	private IDictDao dictDao;

	
	/**
	 * 组装数据字典树
	 * @param id，name
	 * @author zb134373 16.3.25 15:53
	 */
	@Override
	@Transactional(readOnly=true)
	public List<TreeVo> getDictTree(String id, String name) {
		List<TreeVo> list = new ArrayList<>();
		Map<String,Object> map=new HashMap<String,Object>();
		//根节点查询
		if (id == null) {
			map.put("dictCode", Constants.ROOT_CODE);
			
		} else {
			//非根节点
			map.put("parentDictCode", id);
		}
		map.put("status", Constants.Y);
		List<DictEntity> functionEntityList=dictDao.get(map);
		//封装TreeVo
		for(DictEntity dictEntity:functionEntityList){
			TreeVo t = new TreeVo();
			t.setId(dictEntity.getDictCode());
			t.setName(dictEntity.getDictName());
			if(Constants.N==dictEntity.getIsLeaf()){
				t.setParent(true);
			}else{
				t.setParent(false);
			}
			list.add(t);
		}
		return list;
	}
	/**
	 * 
	 * Description: 新增分类
	 * dictEntity 中 dictCode,remark,dictName,parentDictCode
	 * @return
	 * Created by zb 2016年3月22日
	 */
	@Override
	@Transactional
	public void addDict(DictEntity dictEntity) {
		//设置基本数据
		dictEntity.setId(UuidUtil.getUuid());
		dictEntity.setCreateUser(UserContext.getCurrentUser().getUserName());
		dictEntity.setCreateTime(new Date());
		dictEntity.setUpdateTime(new Date());
		dictEntity.setUpdateUser(UserContext.getCurrentUser().getUserName());
		dictEntity.setIsLeaf(Constants.Y);//新增的都是叶子节点
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("dictCode", dictEntity.getDictCode());
		int countNum=dictDao.getPageTotalCount(map);//查询个数
		if(countNum>0){
			throw new BusinessException("分类编号已经存在!");
		}else{
			Map<String,Object> mapParent=new HashMap<String,Object>();
			mapParent.put("dictCode", dictEntity.getParentDictCode());
			List<DictEntity> dictEntityList=dictDao.get(mapParent);
			//如果其父节点是叶子节点，则修改为非叶子节点
			if(!CollectionUtils.isEmpty(dictEntityList)){
				if(dictEntityList.get(0).getIsLeaf()==Constants.Y){
					DictEntity parent = new DictEntity();
					parent.setId(dictEntityList.get(0).getId());
					parent.setIsLeaf(Constants.N);
					dictDao.update(parent);//先修改父节点
				}
			}
			dictDao.insert(dictEntity);//再插入新增节点
		}
	}
	
	/**
	 * 
	 * Description: 修改分类
	 * 
	 * @return
	 * Created by zb 2016年3月22日
	 */
	@Override
	@Transactional
	public void editDict(DictEntity dictEntity) {
		if(Constants.ROOT_CODE.equals(dictEntity.getDictCode())){
			throw new BusinessException("根节点不允许修改！");
		}
		dictEntity.setUpdateUser(UserContext.getCurrentUser().getUserName());
		dictEntity.setUpdateTime(new Date());
		dictDao.update(dictEntity);
	}

	 /**
	 * 
	 * Description: 删除分类
	 * 删除其子节点，数据字典的对应字段
	 * @return
	 * @param dictCode
	 * Created by zb 2016年3月22日
	 */
	@Override
	@Transactional
	public void deleteDict(String dictCode) {
		if(Constants.ROOT_CODE.equals(dictCode)){
			throw new BusinessException("根节点不允许删除！");
		}
		Map<String,Object> mapChild=new HashMap<String,Object>();
		mapChild.put("dictCode",dictCode);
		mapChild.put("status",Constants.N);
		mapChild.put("updateUser",UserContext.getCurrentUser().getUserName());
		mapChild.put("updateTime",new Date());
		dictDao.deleteChildParent(mapChild);//逻辑删除自己及子节点
		dictDao.deleteValueKey(mapChild);//逻辑删除自己及子节点的数据字典
	}
	
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

	
	
}
