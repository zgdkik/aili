package com.feisuo.sds.demo.server.controller;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hbhk.aili.core.share.entity.ResultEntity;
import org.hbhk.aili.mybatis.server.support.Pagination;
import org.hbhk.aili.support.server.excel.poi.ExcelReader;
import org.hbhk.aili.support.server.excel.poi.ExcelWriter;
import org.hbhk.aili.support.server.excel.poi.WriteStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.feisuo.sds.base.server.annotation.QueryPage;
import com.feisuo.sds.base.server.controller.AbstractController;
import com.feisuo.sds.base.share.util.PaginationUtil;
import com.feisuo.sds.base.share.vo.QueryPageVo;
import com.feisuo.sds.base.share.vo.SearchSelectVo;
import com.feisuo.sds.base.share.vo.TreeVo;
import com.feisuo.sds.demo.server.service.IDemoService;
import com.feisuo.sds.demo.share.entity.DemoEntity;

@Controller
@RequestMapping("/demo")
public class DemoController extends AbstractController {

	@Autowired
	private IDemoService demoService;

	@Autowired
	@Qualifier("report1Writer")
	private ExcelWriter report1ExcelWriter;
	
	private ExcelReader excelReader;

	@RequestMapping("/page")
	public String demo() {
		return "/demo";
	}

	@RequestMapping("/getDemo/{id}")
	@ResponseBody
	public ResultEntity getDemo(Model model, HttpServletRequest request,
			HttpServletResponse response, @PathVariable("id") String id) {
		return returnSuccess("欢迎使用智送系统", demoService.getDemo(id));
	}

	@RequestMapping("/getPage")
	@ResponseBody
	public Pagination<DemoEntity> getDemoPage(@QueryPage QueryPageVo queryPageVo) {

		return demoService.getPage(queryPageVo);
	}

	@RequestMapping("/export")
	public void export(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String fileName = new String("excel文件导出.xlsx".getBytes("utf-8"),
				"ISO8859-1");
		response.setCharacterEncoding("utf-8");
		response.setContentType("multipart/form-data");
		response.setHeader("Content-Disposition", "attachment;fileName="
				+ fileName);

		Map<String, Object> beans = new HashMap<String, Object>();
		List<DemoEntity> demoList = demoService
				.get(new HashMap<String, Object>());
		beans.put("demoList", demoList);
		OutputStream os = response.getOutputStream();
		WriteStatus ws = report1ExcelWriter.write(os, beans);
		os.flush();
	}
	
	@RequestMapping("/import")
	public void importExcel(HttpServletRequest request,
			HttpServletResponse response ,MultipartFile excelFile) throws Exception {
		Map<String, Object> beans = new HashMap<>();
		beans.put("list", DemoEntity.class);
		excelReader.readAll(excelFile.getInputStream(), beans);
	}

	@RequestMapping("/form")
	public String from() {
		return "/form";
	}

	@RequestMapping("/tree")
	public String tree() {
		return "/tree";
	}

	@RequestMapping("/getTree")
	@ResponseBody
	public List<TreeVo> getTree(String id, String name) {
		List<TreeVo> list = new ArrayList<>();
		if (id == null) {
			TreeVo t = new TreeVo();
			t.setId("0");
			t.setName("node");
			t.setIsParent(true);
			list.add(t);
		} else {
			if ("0".equals(id)) {
				for (int i = 1; i < 5; i++) {
					TreeVo t = new TreeVo();
					if (i == 1) {
						t.setIsParent(true);
					}
					t.setId("" + i);
					t.setName("node" + i);
					t.setpId("0");
					list.add(t);
				}
			}
			if ("1".equals(id)) {
				for (int i = 5; i < 9; i++) {
					TreeVo t = new TreeVo();
					t.setId("" + i);
					t.setName("node" + i);
					t.setpId("1");
					list.add(t);
				}
			}

		}
		return list;
	}

	@RequestMapping("/autocomplete")
	@ResponseBody
	public List<String> autocomplete() {
		List<String> list = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			list.add("hbhk" + i);
		}
		return list;
	}

	@RequestMapping("/searchSelect")
	@ResponseBody
	public Pagination<SearchSelectVo> searchSelect(
			@QueryPage QueryPageVo queryPageVo) {
		Pagination<DemoEntity> pagination2 = demoService.getPage(queryPageVo);
		Pagination<SearchSelectVo> pagination = PaginationUtil
				.getNewPagination(pagination2);
		List<SearchSelectVo> list = new ArrayList<>();
		SearchSelectVo sso = null;
		for (DemoEntity d : pagination2.getDatas()) {
			sso = new SearchSelectVo();
			sso.setId(d.getId());
			sso.setName(d.getName());
			list.add(sso);
		}
		pagination.setDatas(list);
		return pagination;
	}

}
