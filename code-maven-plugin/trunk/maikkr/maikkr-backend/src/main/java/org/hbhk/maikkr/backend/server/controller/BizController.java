package org.hbhk.maikkr.backend.server.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.aili.core.server.web.BaseController;
import org.hbhk.aili.core.share.ex.BusinessException;
import org.hbhk.aili.core.share.pojo.ResponseEntity;
import org.hbhk.aili.orm.server.intercptor.QueryBeanParam;
import org.hbhk.aili.orm.server.page.bean.QueryBean;
import org.hbhk.aili.orm.share.model.Pagination;
import org.hbhk.maikkr.backend.server.annotation.NeedLogin;
import org.hbhk.maikkr.core.server.service.IBizInfoService;
import org.hbhk.maikkr.core.server.service.IFileService;
import org.hbhk.maikkr.core.shared.pojo.BizInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/backend")
@NeedLogin
@Controller
public class BizController extends BaseController {
	private Log log = LogFactory.getLog(getClass());
	@Autowired
	private IBizInfoService bizInfoService;
	
	@Autowired
	private IFileService fileService;

	@RequestMapping("/bizlist")
	public String themelist() {
		return "bizlist";
	}

	@RequestMapping("/queryBizsByPage")
	@ResponseBody
	public Pagination<BizInfo> queryBizsByPage(
			@QueryBeanParam QueryBean queryBean) {
		return bizInfoService.findEffectBizInfoListByQueryMapWithPage(
				queryBean.getPage(), null, queryBean.getParaMap());
	}

	/**
	 * add, del
	 * 
	 * @param oper
	 * @param user
	 * @return
	 */
	@RequestMapping("/editBiz")
	@ResponseBody
	public ResponseEntity editTheme(String oper, BizInfo biz) {
		try {
			if (oper.equals("del")) {
				biz.setStatus(2);
				bizInfoService.update(biz);
			}
			if (oper.equals("add")) {
				bizInfoService.save(biz);
			}
			if (oper.equals("edit")) {
				bizInfoService.update(biz);
			}
			return returnSuccess();
		} catch (BusinessException e) {
			return returnException(e.getMessage());
		}
	}

	@RequestMapping("/upload")
	public String upload(HttpServletRequest request,
			HttpServletResponse response, MultipartFile filedata,String id)
			throws Exception {
		PrintWriter out = response.getWriter();
		try {
			String imgurl = fileService.saveFile(filedata);
			BizInfo biz = new BizInfo();
			biz.setId(id);
			biz.setImgUrl(imgurl);
			bizInfoService.update(biz);
		} catch (Exception e) {
			log.error("save file error", e);
		} finally {
			out.close();
		}
		return "bizlist";
	}
}