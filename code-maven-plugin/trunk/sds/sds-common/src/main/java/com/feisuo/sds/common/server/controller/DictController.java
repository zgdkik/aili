package com.feisuo.sds.common.server.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hbhk.aili.core.share.entity.ResultEntity;
import org.mybatis.spring.support.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.feisuo.sds.base.server.annotation.QueryPage;
import com.feisuo.sds.base.server.context.UserContext;
import com.feisuo.sds.base.server.controller.AbstractController;
import com.feisuo.sds.base.share.vo.QueryPageVo;
import com.feisuo.sds.common.server.service.IDictService;
import com.feisuo.sds.common.server.service.IDictValueService;
import com.feisuo.sds.common.share.entity.DictEntity;
import com.feisuo.sds.common.share.entity.DictValueEntity;
import com.feisuo.sds.common.util.UuidUtil;

/**
 * 
 * ClassName: DictController
 * Description: TODO
 * Author: dongshenghua
 * Date: 2015年12月5日
 */
@Controller
@RequestMapping("/common")
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
	 * Description: 查询数据字典列表分页
	 * @param queryPageVo
	 * @return
	 * Created by dongshenghua 2015年12月7日
	 */
	@RequestMapping("/queryDictList")
	@ResponseBody
	public Pagination<DictEntity> queryDictList(@QueryPage QueryPageVo queryPageVo){
		Pagination<DictEntity> pageInfo = dictService.getPagination(queryPageVo.getParaMap(),queryPageVo.getPage(),queryPageVo.getSorts());
		return pageInfo;
	}
	
	/**
	 * 
	 * Description: 查询数据字典分类值列表分页
	 * @param queryPageVo
	 * @return
	 * Created by dongshenghua 2015年12月7日
	 */
	@RequestMapping("/queryDictValueList")
	@ResponseBody
	public Pagination<DictValueEntity> queryDictValueList(@QueryPage QueryPageVo queryPageVo){
		Pagination<DictValueEntity> pageInfo = dictValueService.getPagination(queryPageVo.getParaMap(),queryPageVo.getPage(),queryPageVo.getSorts());
		return pageInfo;
	}
	
	
	/**
	 * 
	 * Description: 新增分类
	 * @return
	 * Created by dongshenghua 2015年12月7日
	 */
	@RequestMapping("/addDict")
	@ResponseBody
	public ResultEntity addDict(DictEntity dictEntity) {
		dictEntity.setId(UuidUtil.getUuid());
		dictEntity.setCreateUser(UserContext.getCurrentUser().getUserName());
		dictEntity.setCreateTime(new Date());
		
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("dictCode", dictEntity.getDictCode());
		List<DictEntity> dictEntityList=dictService.get(map);
		
		
		String str="";
		String count="0";
		if(dictEntityList.size()>0){
			count="1";
			str="分类别名已经存在";
		}else{
			int num=dictService.insert(dictEntity);
			if(num>0){
				str="添加成功";
			}else{
				count="1";
				str="添加失败";
			}
		}
		return returnSuccess(str,count);
	}
	
	
	/**
	 * 
	 * Description: 显示修改页面信息
	 * @return
	 * Created by dongshenghua 2015年12月7日
	 */
	@RequestMapping("/showEditDictForm/{dictCode}")
	@ResponseBody
	public ResultEntity showEditDictForm(@PathVariable("dictCode") String dictCode) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("dictCode", dictCode);
		
		List<DictEntity> functionEntity=dictService.get(map);
	    
		return returnSuccess(functionEntity.get(0));
	}
	
	
	/**
	 * 
	 * Description: 修改分类
	 * @return
	 * Created by dongshenghua 2015年12月7日
	 */
	@RequestMapping("/editDict")
	@ResponseBody
	public ResultEntity editDict(DictEntity dictEntity) {
		dictEntity.setUpdateUser(UserContext.getCurrentUser().getUserName());
		dictEntity.setUpdateTime(new Date());
		
		String count="0";
		String str="";
		int num=dictService.update(dictEntity);
		if(num>0){
			str="修改成功";
		}else{
			count="1";
			str="修改失败";
		}
		return returnSuccess(str,count);
	}
	
	/**
	 * 
	 * Description: 修改分类状态
	 * @return
	 * Created by dongshenghua 2015年12月7日
	 */
	@RequestMapping("/editDictStatus")
	@ResponseBody
	public ResultEntity editDictStatus(DictEntity dictEntity) {
		dictEntity.setUpdateUser(UserContext.getCurrentUser().getUserName());
		dictEntity.setUpdateTime(new Date());
		
		String count="0";
		String str="";
		int num=dictService.updateStatusById(dictEntity.getId(), dictEntity.getStatus());
		
		//启动
		if(dictEntity.getStatus()==1){
			if(num>0){
				str="启用成功";
			}else{
				count="1";
				str="启用失败";
			}
		//作废
		}else{
			DictValueEntity dictValueEntity=new DictValueEntity();
			dictValueEntity.setDictCode(dictEntity.getDictCode());
			dictValueEntity.setStatus(dictEntity.getStatus());
			dictValueEntity.setUpdateUser(UserContext.getCurrentUser().getUserName());
			dictValueEntity.setUpdateTime(new Date());
			//作废类型下的数据状态
			int aa=dictService.editDictValueStatusService(dictValueEntity);
			if(num>0 && aa>0){
				str="作废成功";
			}else{
				count="1";
				str="作废失败";
			}
		}
		
		return returnSuccess(str,count);
	}
	
	
	/**
	 * 
	 * Description: 新增数据
	 * @param dictEntity
	 * @return
	 * Created by dongshenghua 2015年12月8日
	 */
	@RequestMapping("/addDictValue")
	@ResponseBody
	public ResultEntity addDictValue(DictValueEntity dictValueEntity) {
		dictValueEntity.setId(UuidUtil.getUuid());
		dictValueEntity.setCreateUser(UserContext.getCurrentUser().getUserName());
		dictValueEntity.setCreateTime(new Date());
		
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("key", dictValueEntity.getKey());
		map.put("dictCode", dictValueEntity.getDictCode());
		List<DictValueEntity> dictValueEntityList=dictValueService.get(map);
		
		Map<String,Object> map1=new HashMap<String,Object>();
		map1.put("orderNo", dictValueEntity.getOrderNo());
		map1.put("dictCode", dictValueEntity.getDictCode());
		List<DictValueEntity> dictValueEntityList1=dictValueService.get(map1);
		
		
		String str="";
		String count="0";
		if(dictValueEntityList.size()>0){
			count="1";
			str="key已经存在";
		}else if(dictValueEntityList1.size()>0){
			count="1";
			str="序号已经存在";
		}else{
			int num=dictValueService.insert(dictValueEntity);
			if(num>0){
				str="添加成功";
			}else{
				count="1";
				str="添加失败";
			}
		}
		return returnSuccess(str,count);
	}
	
	
	/**
	 * 
	 * Description: 显示数据修改页面信息
	 * @return
	 * Created by dongshenghua 2015年12月7日
	 */
	@RequestMapping("/showUpdateForm/{id}")
	@ResponseBody
	public ResultEntity showUpdateForm(@PathVariable("id") String id) {
		
		DictValueEntity functionEntity=dictValueService.getById(id);
	    
		return returnSuccess(functionEntity);
	}

	
	/**
	 * 
	 * Description: 修改数据
	 * @return
	 * Created by dongshenghua 2015年12月7日
	 */
	@RequestMapping("/updateDict")
	@ResponseBody
	public ResultEntity updateDict(DictValueEntity dictValueEntity) {
		dictValueEntity.setUpdateUser(UserContext.getCurrentUser().getUserName());
		dictValueEntity.setUpdateTime(new Date());
		
		String count="0";
		String str="";
		int num=dictValueService.update(dictValueEntity);
		if(num>0){
			str="修改成功";
		}else{
			count="1";
			str="修改失败";
		}
		return returnSuccess(str,count);
	}
	
	
	/**
	 * 
	 * Description: 修改数据状态
	 * @return
	 * Created by dongshenghua 2015年12月7日
	 */
	@RequestMapping("/editDictValueStatus")
	@ResponseBody
	public ResultEntity editDictValueStatus(DictValueEntity dictValueEntity) {
		dictValueEntity.setUpdateUser(UserContext.getCurrentUser().getUserName());
		dictValueEntity.setUpdateTime(new Date());
		
		String count="0";
		String str="";
		int num=dictValueService.updateStatusById(dictValueEntity.getId(), dictValueEntity.getStatus());
		
		//启动
		if(dictValueEntity.getStatus()==1){
			if(num>0){
				str="启用成功";
			}else{
				count="1";
				str="启用失败";
			}
		//作废
		}else{
			if(num>0){
				str="作废成功";
			}else{
				count="1";
				str="作废失败";
			}
		}
		
		return returnSuccess(str,count);
	}
}
