package com.yimidida.ows.home.server.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yimidida.ows.base.server.controller.AbstractController;
import com.yimidida.ows.home.server.service.INoticeService;
import com.yimidida.ows.home.share.entity.Notice;

@Controller
public class DaiyunController extends AbstractController implements  InitializingBean {

	@Autowired
	INoticeService noticeService;
	
	private Map<String, String>  wenzhang = new HashMap<String, String>();

	// 企业简介，企业理念，大事纪要
	@RequestMapping("/daiyun/{roleCode}")
	public String introduce(Model model, @PathVariable String roleCode,
			String title, HttpServletRequest request) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("noticeTitle", wenzhang.get(roleCode));
		List<Notice> list = noticeService.get(params);
		if (list.size() > 0) {
			model.addAttribute(
					"htmlContent",
					list.get(0).getNoticeContent()
							.replaceAll("/common",
									request.getContextPath() + "/common"));
			model.addAttribute("title", title);
		}
		return "daiyun/daiyun";
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		wenzhang.put("aboutus", "关于我们");
		wenzhang.put("flow", "代孕流程");
		wenzhang.put("shiguan", "试管婴儿");
		wenzhang.put("anli", "成功案例");
		wenzhang.put("zhishi", "代孕知识");
		wenzhang.put("lianxi", "联系我们");


	}

}
