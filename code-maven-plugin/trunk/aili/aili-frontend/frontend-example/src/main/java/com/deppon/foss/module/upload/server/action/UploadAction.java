package com.deppon.foss.module.upload.server.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.deppon.foss.framework.exception.BusinessException;
import com.deppon.foss.framework.server.components.file.FileInfo;
import com.deppon.foss.framework.server.components.file.FileManager;
import com.deppon.foss.framework.server.context.UserContext;
import com.deppon.foss.framework.server.web.action.AbstractAction;
import com.deppon.foss.framework.server.web.upload.AttachementEntity;
import com.deppon.foss.framework.server.web.upload.IAttachementListener;

public class UploadAction extends AbstractAction {

	private static final long serialVersionUID = 7710743686516808232L;

	private FileManager fileManager;

	private IAttachementListener attachementListener;
	
	/** 接受前台上传的文件流 */
	private File file;
	
	/** 接收前台传过来的图片路径 */
	private String relativePath;
	
	/** 返回给前端的结果 */
	private AttachementEntity attachement;
	
	/** 返回给前端的图片流 */
	private InputStream inputStream;
	
	/** 文件上传 
	 * @throws IOException */
	public String uploadFile() throws IOException {
		try {
			FileInfo fileInfo = fileManager.create(file);
			if (this.attachement == null) {
				this.attachement = new AttachementEntity();
			}
			this.attachement.setActive("Y");
			this.attachement.setCreateDate(new Date());
			this.attachement.setCreateUser(UserContext.getCurrentUser().getUserName());
			this.attachement.setFileName(this.file.getName());
			this.attachement.setFileSize(Long.toString(fileInfo.getLength()));
			this.attachement.setId(fileInfo.getUuid());
			this.attachement.setRelativePath(fileInfo.getRelativePath());
			if(attachementListener != null) {
			    attachementListener.addAttachementInfo(attachement);
			}
		} catch(BusinessException e) {
			return returnError(e);
		}
    	return returnSuccess();
    }

	/**
	 * 图片预览
	 * @return
	 */
    public String reviewImg() {
    	if(StringUtils.isNotBlank(this.relativePath)) {
		    try {
		    	File f = fileManager.read(relativePath);
		    	inputStream = new FileInputStream(f);
		    	return returnSuccess();
			} catch (FileNotFoundException e) {
				return returnError("The file can not be found");
			}
    	} else {
    		return returnError("The relative path of the file is invalidate");
    	}
    }
	
	public void setFileManager(FileManager fileManager) {
		this.fileManager = fileManager;
	}

	public void setAttachementListener(IAttachementListener attachementListener) {
		this.attachementListener = attachementListener;
	}
	
	public void setFile(File file) {
		this.file = file;
	}

	public AttachementEntity getAttachement() {
		return attachement;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setRelativePath(String relativePath) {
		this.relativePath = relativePath;
	}

}
