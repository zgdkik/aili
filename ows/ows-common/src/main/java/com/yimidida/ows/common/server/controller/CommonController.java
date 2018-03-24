package com.yimidida.ows.common.server.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yimidida.ows.base.server.controller.AbstractController;
import com.yimidida.ows.base.share.util.SystemParameterUtil;
import com.yimidida.ymdp.cache.server.CacheManager;
import com.yimidida.ymdp.cache.server.ICache;
import com.yimidida.ymdp.core.share.entity.ResultEntity;
import com.yimidida.ymdp.support.file.IFileService;

@Controller
@RequestMapping("/common")
public class CommonController extends AbstractController {

	private String prefixPatp;

	@Autowired
	private IFileService fileService;

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * @Description:查看图片
	 */
	@RequestMapping("/viewImage/{id}")
	public void viewImage(HttpServletRequest request,
			HttpServletResponse response, Model model,@PathVariable String id) {
		if (prefixPatp == null) {
			prefixPatp = SystemParameterUtil.getValue("upload.path.prefix");
		}
		if (StringUtils.isNotBlank(id)) {
			fileService.viewImage(request, response, prefixPatp+"/" + id);
		}
	}

	@RequestMapping("/invalid")
	@ResponseBody
	public ResultEntity invalid(String cacheId, String key) {
		ICache<String, Object> cache= CacheManager.getInstance().getCache(cacheId);
		cache.invalid(key);
		return returnSuccess("刷新成功");
	}
	
	@RequestMapping("/download/lodop")
	public void download(HttpServletRequest request,HttpServletResponse response) throws IOException {
		File file  = new File(SystemParameterUtil.getValue("lodop.file.path"));
		InputStream input = new FileInputStream(file);
		//FileLoadUtil.getInputStreamForServletpath("classpath*:lodop/yimidida-Lodop.zip");
		downloadFile(response, input, "yimidida-lodop.zip", input.available());
	}


	public void downloadFile(HttpServletResponse response, InputStream input,
			String fileName, int buffer) {
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			response.setContentType("text/html;charset=utf-8");
			response.setContentType("application/x-msdownload;");
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(fileName.getBytes("utf-8"), "ISO8859-1"));
			response.setHeader("Content-Length", String.valueOf(buffer));
			bis = new BufferedInputStream(input);
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[buffer];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		} finally {
			try {
				if (bis != null){
					bis.close();
				}
					
				if (bos != null){
					bos.close();
				}
			} catch (Exception e2) {
				logger.error(e2.getMessage(), e2);
			}
		}
	}
}
