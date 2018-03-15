package org.hbhk.aili.common.server.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.hbhk.aili.base.server.annotation.QueryPage;
import org.hbhk.aili.base.server.context.UserContext;
import org.hbhk.aili.base.server.controller.AbstractController;
import org.hbhk.aili.base.share.vo.QueryPageVo;
import org.hbhk.aili.base.share.vo.TreeVo;
import org.hbhk.aili.common.server.service.IDictService;
import org.hbhk.aili.common.server.service.IDictValueService;
import org.hbhk.aili.common.share.entity.DictEntity;
import org.hbhk.aili.common.share.entity.DictValueEntity;
import org.hbhk.aili.common.share.util.Constants;
import org.hbhk.aili.core.share.entity.ResultEntity;
import org.hbhk.aili.mybatis.server.support.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/dict")
public class DictController  extends AbstractController{
	
	@Autowired
	private IDictService dictService;
	
	@Autowired
	private IDictValueService dictValueService;
	/**
	 * 
	 * Description: 数据字典首页
	 * @return
	 * Created by dongshenghua 2015年12月5日
	 */
	@RequestMapping("/showDictIndex")
	public String showDictIndex() {
		
		return "/dict/dict";
	}
	
	/**
	 * 
	 * Description: 加载数据字典树
	 * @author zb134373 16.3.22 20:02
	 */
	@RequestMapping("/getDictTree")
	@ResponseBody
	public List<TreeVo> getDictTree(String id,String name) {
		return dictService.getDictTree(id,name);
	}

	/**
	 * 
	 * Description: 新增分类
	 * dictEntity 中 dictCode,remark,dictName,parentDictCode
	 * @return
	 * Created by zb 2016年3月22日
	 */
	@RequestMapping("/addDict")
	@ResponseBody
	public ResultEntity addDict(@Valid DictEntity dictEntity,BindingResult bindingResult) {
		validator(bindingResult);
		dictService.addDict(dictEntity);
		return returnSuccess("新增成功!");
	}

	/**
	 * 
	 * Description: 显示修改页面信息
	 * @return
	 * Created by zb 2016年3月23日
	 */
	@RequestMapping("/showEditDictForm/{dictCode}")
	@ResponseBody
	public ResultEntity showEditDictForm(@PathVariable("dictCode") String dictCode) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("dictCode", dictCode);
		List<DictEntity> dictEntity=dictService.get(map);
		return returnSuccess(dictEntity.get(0));
	}
	
	/**
	 * 
	 * Description: 修改分类
	 * @return
	 * Created by ZB 2016年3月25日
	 */
	@RequestMapping("/editDict")
	@ResponseBody
	public ResultEntity editDict(DictEntity dictEntity) {
		dictService.editDict(dictEntity);
		return returnSuccess("修改成功!");		
	}
	
	/**
	 * 
	 * Description: 逻辑删除数据字典类型
	 * @return
	 * Created by zb 2016年3月28日
	 */
	@RequestMapping("/deleteDict/{dictCode}")
	@ResponseBody
	public ResultEntity deleteDict(@PathVariable("dictCode") String dictCode) {
		dictService.deleteDict(dictCode);
		return returnSuccess("删除成功!");		
	}
	
	
	//------------------------------dictValue----start---------
	
	/**
	 * 
	 * Description: 加载数据字典keyvalue
	 * @author zb134373 16.3.28 20:02
	 */
	@RequestMapping("/getDictValueList")
	@ResponseBody
	public Pagination<DictValueEntity> getDictValueList(@QueryPage QueryPageVo queryPageVo) {
		Pagination<DictValueEntity> pageInfo = dictValueService.getPage(queryPageVo);
		return pageInfo;
	}
	
	
	/**
	 * 
	 * Description: 新增数据
	 * @param dictEntity
	 * @return
	 * Created by zb 2016年3月28日
	 */
	@RequestMapping("/addDictValue")
	@ResponseBody
	public ResultEntity addDictValue(@Valid DictValueEntity dictValueEntity,BindingResult bindingResult) {
		validator(bindingResult);//校验
		dictValueService.addDictValue(dictValueEntity);
		return returnSuccess("添加成功");
	}
	
	
	/**
	 * 
	 * Description: 显示数据修改页面信息
	 * @return
	 * Created by dongshenghua 2015年12月7日
	 */
	@RequestMapping("/showEditDictValueForm/{id}")
	@ResponseBody
	public ResultEntity showEditDictValueForm(@PathVariable("id") String id) {

		DictValueEntity dictValueEntity=dictValueService.getById(id);	    
		return returnSuccess(dictValueEntity);
	}

	
	/**
	 * 
	 * Description: 修改数据
	 * @return
	 * Created by zb 2016年3月28日
	 */
	@RequestMapping("/updateDictValue")
	@ResponseBody
	public ResultEntity updateDictValue(DictValueEntity dictValueEntity) {
		dictValueService.updateDictValue(dictValueEntity);
		return returnSuccess("修改成功");
	}
	
	
	/**
	 * 
	 * Description:逻辑删除数据字典
	 * @return
	 * Created by zb 2016年3月28日
	 */
	@RequestMapping("/deleteDictValue/{id}")
	@ResponseBody
	public ResultEntity deleteDictValue(@PathVariable("id") String id) {
		DictValueEntity dictValueEntity = new DictValueEntity();
		dictValueEntity.setId(id);
		dictValueEntity.setStatus( Constants.N);
		dictValueEntity.setUpdateUser(UserContext.getCurrentUser().getUserName());
		dictValueEntity.setUpdateTime(new Date());
		dictValueService.update(dictValueEntity);
		return returnSuccess("删除成功！");
	}
}
