package org.hbhk.aili.security.server.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hbhk.aili.core.server.annotation.SecurityFilter;
import org.hbhk.aili.security.server.context.UserContext;
import org.hbhk.aili.security.share.define.SecurityConstant;
import org.hbhk.aili.security.share.pojo.ResourceInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(SecurityConstant.moduleName)
public class LoginController {

	@RequestMapping("/loginpage")
	@SecurityFilter(false)
	public String loginpage() {
		return "loginpage";
	}

	@SecurityFilter(false)
	@RequestMapping("/home")
	public String home() {
		return "home4";
	}

	@RequestMapping("/logout")
	@SecurityFilter(false)
	public String logout() {
		UserContext.remove();

		return "loginpage";
	}

	@RequestMapping("/error")
	@SecurityFilter(false)
	public String error() {
		return "error";
	}

	@RequestMapping("/menu")
	@SecurityFilter(false)
	public String menu() {
		return "menu";
	}

	@RequestMapping("/getMenu")
	@SecurityFilter(false)
	@ResponseBody
	public Map<String, ResourceInfo> getMenu(String root) {

		ResourceInfo menus = new ResourceInfo();
		menus.setText("text");
		menus.setExpanded(true);
		menus.setHasChildren(true);
		List<ResourceInfo> mm = new ArrayList<ResourceInfo>();
		for (int i = 0; i < 3; i++) {
			ResourceInfo m = new ResourceInfo();
			for (int j = 0; j < 1 && i == j; j++) {
				ResourceInfo mj = new ResourceInfo();
				mj.setText("textj");
				List<ResourceInfo> mm1 = new ArrayList<ResourceInfo>();
				mm1.add(mj);
				m.setChildren(mm1);
			}
			m.setText("text" + i);
			mm.add(m);
		}
		menus.setChildren(mm);
		Map<String, ResourceInfo> map = new HashMap<String, ResourceInfo>();
		map.put("root", menus);
		return map;
	}

}