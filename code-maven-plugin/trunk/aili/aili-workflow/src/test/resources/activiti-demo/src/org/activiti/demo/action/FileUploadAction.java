package org.activiti.demo.action;

import java.io.File;

import javax.annotation.Resource;

import org.activiti.demo.base.BaseAction;
import org.activiti.demo.engine.ProcessEngineCore;
import org.activiti.demo.utils.UnZipFile;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import ch.qos.logback.classic.Logger;

/**
 * 
 * @Title: FileUploadAction.java
 * @Description: org.activiti.demo.action
 * @Package org.activiti.demo.action
 * @author hncdyj123@163.com
 * @date 2013-3-14
 * @version V1.0
 * 
 */
@SuppressWarnings("serial")
@Controller("fileUploadAction")
@Scope("protype")
@Namespace("/activiti/upload")
@ParentPackage("json-default")
public class FileUploadAction extends BaseAction {

	/** 日志对象 **/
	private static final Logger logger = (Logger) LoggerFactory.getLogger(FileUploadAction.class);

	/** 日志对象 **/
	private File template;
	/** 文件名 **/
	private String templateFileName;
	@Resource(name = "processEngineCore")
	private ProcessEngineCore processEngineCore;

	/**
	 * 资源文件上传
	 * 
	 * @return
	 */
	@Action(value = "upload", results = { @Result(type = "json", params = { "root", "result", "contentType", "text/html" }) })
	public String upload() {
		try {
			if (template == null) {
				return ERROR;
			}
			if (validateFileSize(template)) {
				this.setMessage("文件超过5M!");
				return SUCCESS;
			}

			// 将上传的文件拷贝到临时目录中
			copyFileToTempFolder(System.getProperty("user.dir"));

			// 处理上传到临时目录中的文件
			String str = handleTempFile(System.getProperty("user.dir"));

			// 发布流程
			processEngineCore.deploymentInstance(str);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 将上传的文件拷贝到临时目录中
	 * 
	 * @param tempFilePath
	 * @throws Exception
	 */
	private void copyFileToTempFolder(String tempFilePath) throws Exception {
		// 获取读入的文件(参数1：文件存放的路径。参数2：上传的文件的名称)
		File file = new File(new File(tempFilePath), templateFileName);
		// 查看上传文件要存放的文件夹是否存在，如果不存在就创建文件目录
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		FileUtils.copyFile(template, file);
	}

	/**
	 * 处理上传到临时目录中的文件
	 * 
	 * @param tempFilePath
	 * @return 流程文件路径
	 */
	private String handleTempFile(String tempFilePath) throws Exception {

		// 得到上传文件后缀
		String fileSuffix = templateFileName.substring(templateFileName.lastIndexOf(".") + 1, templateFileName.length());

		// zip文件
		if ("zip".equals(fileSuffix)) {
			logger.debug("zip temp dir is " + tempFilePath + File.separator + templateFileName);
			UnZipFile.unzip(tempFilePath + File.separator + templateFileName);
		}

		return tempFilePath + File.separator + templateFileName;
	}

	/**
	 * 验证文件大小
	 * 
	 * @param file
	 * @return
	 */
	private boolean validateFileSize(File file) {
		if (file.length() > 5242880) {
			return true;
		}
		return false;
	}

	public File getTemplate() {
		return template;
	}

	public void setTemplate(File template) {
		this.template = template;
	}

	public String getTemplateFileName() {
		return templateFileName;
	}

	public void setTemplateFileName(String templateFileName) {
		this.templateFileName = templateFileName;
	}

	public void setProcessEngineCore(ProcessEngineCore processEngineCore) {
		this.processEngineCore = processEngineCore;
	}

}