package com.feisuo.sds.common.server.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.hbhk.aili.support.server.file.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.feisuo.sds.base.server.controller.AbstractController;
import com.feisuo.sds.base.share.util.SystemParameterUtil;

@Controller
@RequestMapping("/common")
public class CommonController extends AbstractController {

	private String prefixPatp;

	@Autowired
	private IFileService fileService;

	/**
	 * @Description:查看图片
	 * @Author:nizhenghua
	 * @date 2015-12-18上午10:59:45
	 */
	@RequestMapping("/viewImage")
	public void viewImage(HttpServletRequest request,
			HttpServletResponse response, Model model, String id) {
		if (prefixPatp == null) {
			prefixPatp = SystemParameterUtil.getValue("upload.path.prefix");
		}
		if (StringUtils.isNotBlank(id)) {
			fileService.viewImage(request, response, prefixPatp + id);
		}
	}
}
