package org.hbhk.aili.common.server.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.hbhk.aili.base.server.annotation.QueryPage;
import org.hbhk.aili.base.server.context.UserContext;
import org.hbhk.aili.base.server.controller.AbstractController;
import org.hbhk.aili.base.share.entity.Client;
import org.hbhk.aili.base.share.vo.QueryPageVo;
import org.hbhk.aili.common.server.service.IClientService;
import org.hbhk.aili.core.share.entity.ResultEntity;
import org.hbhk.aili.core.share.ex.BusinessException;
import org.hbhk.aili.mybatis.server.support.Pagination;
import org.hbhk.aili.mybatis.server.support.Sort;
import org.hbhk.aili.support.server.file.IFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/client")
public class ClientController extends AbstractController {

	private Logger  log = LoggerFactory.getLogger(getClass());
	@Autowired
	private IClientService clientService;
	
	@Autowired
	private IFileService fileService;
	

	@RequestMapping("/list")
	public String client() {

		return "client/list";
	}

	@RequestMapping("/getPagination")
	@ResponseBody
	public Pagination<Client> getPagination(@QueryPage QueryPageVo queryPageVo) {
		queryPageVo.getParaMap().put("status", 1);
		Sort sort = new Sort();
		sort.setField("release_time");
		sort.setType(Sort.DESC);
		Sort sort1 = new Sort();
		sort1.setField("app_key");
		Pagination<Client> pageInfo = clientService.getPagination(
				queryPageVo.getParaMap(), queryPageVo.getPage(),
				new Sort[]{sort1,sort});
		return pageInfo;
	}

	@RequestMapping("/edit")
	@ResponseBody
	public ResultEntity edit(Client client, BindingResult bindingResult) {
		validator(bindingResult);
		String msg = "";
		if (StringUtils.isNotEmpty(client.getId())) {
			msg = "修改成功!";
		} else {
			msg = "新增成功";
		}
		clientService.insert(client);
		return returnSuccess(msg);
	}

	@RequestMapping("/get/{id}")
	@ResponseBody
	public ResultEntity getById(@PathVariable String id) {
		return returnSuccess(clientService.getById(id));
	}
	
	@RequestMapping("/updateStatus/{id}")
	@ResponseBody
	public ResultEntity updateStatusById(@PathVariable String id) {
		clientService.updateStatusById(id,0);
		return returnSuccess("删除成功");
	}
	
	@RequestMapping("/upload")
	@ResponseBody
	public ResultEntity upload(String id,MultipartFile barFile ,HttpServletResponse response) throws Exception {
		//获取客户端信息
		try {
			if(StringUtils.isEmpty(id)){
				throw new BusinessException("id 不能为空");
			}
			Client c=  clientService.getById(id);
			if(c ==null){
				throw new BusinessException("上传的应用不存在");
			}
			String path = "/"+c.getAppKey()+"/"+c.getVersionName()+"/";
			String filePath =  fileService.saveFile(barFile, path,"bar-app.apk");
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("id", id);
			params.put("filePath", filePath);
			params.put("updateTime", new Date());
			params.put("updateUser", UserContext.getCurrentUser().getUserName());
			log.info("应用:"+c.getAppKey()+"路径:"+filePath);
			clientService.updateFilePath(params);
			return returnSuccess("上传成功");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			return returnException(e.getMessage());
		}
	
	}
}
