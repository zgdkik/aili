package org.hbhk.aili.common.server.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.hbhk.aili.base.server.controller.AbstractController;
import org.hbhk.aili.base.share.util.SystemParameterUtil;
import org.hbhk.aili.cache.server.CacheManager;
import org.hbhk.aili.cache.server.ICache;
import org.hbhk.aili.core.share.entity.ResultEntity;
import org.hbhk.aili.support.server.file.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/common")
public class CommonController extends AbstractController {

	private String prefixPatp;

	@Autowired
	private IFileService fileService;

	/**
	 * @Description:查看图片
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

	@RequestMapping("/invalid")
	@ResponseBody
	public ResultEntity invalid(String cacheId, String key) {
		ICache<String, Object> cache= CacheManager.getInstance().getCache(cacheId);
		cache.invalid(key);
		return returnSuccess("刷新成功");
	}
}
