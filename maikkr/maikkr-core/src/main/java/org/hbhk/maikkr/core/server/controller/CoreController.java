package org.hbhk.maikkr.core.server.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hbhk.aili.core.share.util.IOUtils;
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
	public String upload(HttpServletRequest request,
			HttpServletResponse response, MultipartFile file)
			throws IOException {
		IOUtils.saveFile(file.getInputStream(), "userImages","hbhk",
				file.getOriginalFilename());
		return "upload";
	}

}