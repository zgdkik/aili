package org.hbhk.aili.core.server.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hbhk.aili.core.share.util.ModuleConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(ModuleConstants.MODULE_FRAMEWORK)
public class FileUploadControllor {

	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public String getUploadFile(HttpServletRequest request,
			HttpServletResponse response, String filename) {
		String myappPath = request.getSession().getServletContext()
				.getRealPath("/");
		try {
			if (request instanceof MultipartHttpServletRequest) {
				MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
				MultipartFile file = multipartRequest.getFiles("userfile1")
						.get(0);
				long size = file.getSize();
				byte[] data = new byte[(int) size];
				InputStream input = file.getInputStream();
				input.read(data);

				File outFile = new File(myappPath + File.separator
						+ file.getOriginalFilename());
				if (!outFile.exists()) {
					outFile.createNewFile();
					System.out.println("full path = "
							+ outFile.getAbsolutePath());
				} else {
					System.out.println("full path = "
							+ outFile.getAbsolutePath());
				}
				FileOutputStream outStream = new FileOutputStream(outFile);

				outStream.write(data);
				outStream.close();
				input.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/m1/index";
	}

	@RequestMapping("/fileupload")
	public String toUpload() {

		return "fileupload";
	}

	@RequestMapping("/download")
	public ModelAndView download( String fileName,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		java.io.BufferedInputStream bis = null;
		java.io.BufferedOutputStream bos = null;
		String ctxPath = request.getSession().getServletContext()
				.getRealPath("/")
				+ "\\" + "images\\";
		fileName = new String(fileName.getBytes("UTF-8"),"UTF-8");
		String downLoadPath = ctxPath + fileName;
		System.out.println(downLoadPath);
		try {
			long fileLength = new File(downLoadPath).length();
			response.setContentType("application/x-msdownload;");
			response.setHeader("Content-disposition", "attachment; filename="+ fileName);
			response.setHeader("Content-Length", String.valueOf(fileLength));
			bis = new BufferedInputStream(new FileInputStream(downLoadPath));
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bis != null)
				bis.close();
			if (bos != null)
				bos.close();
		}
		return null;
	}

}
