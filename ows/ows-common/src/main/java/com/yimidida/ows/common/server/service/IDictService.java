package com.yimidida.ows.common.server.service;

import java.util.List;

import com.yimidida.ows.base.server.service.IBaseService;
import com.yimidida.ows.base.share.vo.TreeVo;
import com.yimidida.ows.common.share.entity.DictEntity;

public interface IDictService extends IBaseService<DictEntity, String> {
	/**
	 * 组装数据字典树
	 * @param id，name
	 * @author zb134373 16.3.25 15:53
	 */
	 public List<TreeVo> getDictTree(String id,String name);
	 /**
	 * 
	 * Description: 新增分类
	 * dictEntity 中 dictCode,remark,dictName,parentDictCode
	 * 新增首先要修改上级节点的isLeaf属性，然后再插入数据，增加实务操作
	 * @return
	 * Created by zb 2016年3月22日
	 */
	 public void addDict (DictEntity dictEntity);
	 /**
	 * 
	 * Description: 修改分类
	 * 首先判断编号是否存在相同的,然后修改其子节点，数据字典的对应字段
	 * @return
	 * Created by zb 2016年3月22日
	 */
	 public void editDict (DictEntity dictEntity);
	 
	 
	 /**
	 * 
	 * Description: 删除分类
	 * 删除其子节点，数据字典的对应字段
	 * @return
	 * @param dictCode
	 * Created by zb 2016年3月22日
	 */
	 public void deleteDict (String dictCode);
	 
}
