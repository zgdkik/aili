package org.hbhk.aili.support.server.file.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hbhk.aili.core.share.util.ReSizeImageUtil;
import org.hbhk.aili.core.share.util.SpringIOUtils;
import org.hbhk.aili.support.server.file.IFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * @Description: 整合第三方框架支持
 * @author 何波
 * @date 2015年3月11日 上午10:05:24
 * 
 */
public class FileService implements IFileService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private String pathPrefix;

	public String saveFile(MultipartFile Filedata, String path, String name)
			throws IOException {
		SpringIOUtils.saveFile(Filedata.getInputStream(), pathPrefix + path,
				name);
		return pathPrefix + path + name;
	}

	public String saveFile(InputStream input, String path, String name)
			throws IOException {
		SpringIOUtils.saveFile(input, pathPrefix + path, name);
		return pathPrefix + path + name;
	}

	public String saveImage(InputStream input, String path, String name,
			int width, int height) throws IOException {
		ReSizeImageUtil.resize(input, width, height, pathPrefix + path, name);
		return pathPrefix + path + name;
	}

	public String saveImage(MultipartFile Filedata, String path, String name,
			int width, int height) throws IOException {
		ReSizeImageUtil.resize(Filedata.getInputStream(), width, height,
				pathPrefix + path, name);
		return pathPrefix + path + name;
	}

	@Override
	public void downloadFile(HttpServletResponse response, InputStream input,
			String fileName, int buffer) throws IOException {
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			response.setContentType("text/html;charset=utf-8");
			response.setContentType("application/x-msdownload;");
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(fileName.getBytes("utf-8"), "ISO8859-1"));
			// response.setHeader("Content-Length", String.valueOf(fileLength));
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
			if (bis != null)
				bis.close();
			if (bos != null)
				bos.close();
		}
	}

	public void viewImage(HttpServletRequest request,
			HttpServletResponse response, String imagePath) {
		// pic为读取到图片的存储路径（数据库中存储的字段值）
		ServletOutputStream out = null;
		FileInputStream ips = null;
		try {
			// ALARM_FILE_PATH为本地图片存放路径，imagePath为图片的真实路径
			ips = new FileInputStream(new File(imagePath));
			response.setContentType("multipart/form-data");
			out = response.getOutputStream();
			// 读取文件流
			int i = 0;
			byte[] buffer = new byte[ips.available()];
			while ((i = ips.read(buffer)) != -1) {
				// 写文件流
				out.write(buffer, 0, i);
			}
			out.flush();
			ips.close();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
			}
			if (ips != null) {
				try {
					ips.close();
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
			}
		}
	}

	public String getPathPrefix() {
		return pathPrefix;
	}

	public void setPathPrefix(String pathPrefix) {
		this.pathPrefix = pathPrefix;
	}

}
