package org.hbhk.maikkr.core.server.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hbhk.aili.security.server.annotation.NeedLogin;
import org.hbhk.aili.springmvc.shared.util.SpringIOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/core")
public class CoreController {

	@RequestMapping("/main")
	public String main() {
		return "main";
	}

	@RequestMapping("/upload")
	@ResponseBody
	@NeedLogin
	public String upload(HttpServletRequest request,
			HttpServletResponse response, MultipartFile Filedata)
			throws IOException {
		SpringIOUtils.saveFile(Filedata.getInputStream(), "userImages","hbhk",
				Filedata.getOriginalFilename());
		return "upload";
	}

}