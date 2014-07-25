package org.hbhk.maikkr.core.server.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.aili.core.server.web.BaseController;
import org.hbhk.aili.core.share.pojo.ResponseEntity;
import org.hbhk.aili.security.server.annotation.NeedLogin;
import org.hbhk.maikkr.core.server.service.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/core")
public class CoreController extends BaseController {

	private Log log = LogFactory.getLog(getClass());
	@Autowired
	private IFileService fileService;

	@RequestMapping("/main")
	public String main() {
		return "main";
	}

	@RequestMapping("/upload")
	@ResponseBody
	@NeedLogin
	public ResponseEntity upload(HttpServletRequest request,
			HttpServletResponse response, MultipartFile Filedata)
			throws Exception {
		try {
			fileService.saveFile(Filedata);
			return returnSuccess();
		} catch (Exception e) {
			log.error("save file error", e);
			return returnException();
		}

	}
}