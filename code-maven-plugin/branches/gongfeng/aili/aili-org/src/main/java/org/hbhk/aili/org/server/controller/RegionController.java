package org.hbhk.aili.org.server.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hbhk.aili.base.server.annotation.QueryPage;
import org.hbhk.aili.base.server.controller.AbstractController;
import org.hbhk.aili.base.share.vo.QueryPageVo;
import org.hbhk.aili.base.share.vo.TreeVo;
import org.hbhk.aili.core.share.entity.ResultEntity;
import org.hbhk.aili.mybatis.server.support.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.hbhk.aili.org.share.entity.Region;
import org.hbhk.aili.org.server.service.IRegionService;
/**
 * @author  何波
 *
 */
@Controller
@RequestMapping("/region")
public class RegionController extends AbstractController {

	@Autowired
	private IRegionService regionService;

	@RequestMapping("/list")
	public String client() {

		return "/regionList";
	}

	@RequestMapping("/getPagination")
	@ResponseBody
	public Pagination<Region> getPagination(@QueryPage QueryPageVo queryPageVo) {
		queryPageVo.getParaMap().put("status", 1);
		Pagination<Region> pageInfo = regionService.getPagination(
				queryPageVo.getParaMap(), queryPageVo.getPage(),queryPageVo.getSorts());
		return pageInfo;
	}

	@RequestMapping("/edit")
	@ResponseBody
	public ResultEntity edit(Region entity, BindingResult bindingResult) {
		validator(bindingResult);
		String msg = "";
		if (StringUtils.isNotEmpty(entity.getId())) {
			msg = "修改成功!";
		} else {
			msg = "新增成功";
		}
		regionService.insert(entity);
		return returnSuccess(msg);
	}

	@RequestMapping("/get/{id}")
	@ResponseBody
	public ResultEntity getById(@PathVariable String id) {
		return returnSuccess(regionService.getById(id));
	}
	
	@RequestMapping("/updateStatus/{id}")
	@ResponseBody
	public ResultEntity updateStatusById(@PathVariable String id) {
		regionService.updateStatusById(id,0);
		return returnSuccess("删除成功");
	}
	
	@RequestMapping("/getTree")
	@ResponseBody
	public List<TreeVo> getTree(String id) {
		return regionService.getTree(id);

	}
}
