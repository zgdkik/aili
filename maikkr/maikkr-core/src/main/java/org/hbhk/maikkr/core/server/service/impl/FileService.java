package org.hbhk.maikkr.core.server.service.impl;

import java.io.IOException;
import java.util.Date;

import org.hbhk.aili.core.share.util.SpringIOUtils;
import org.hbhk.aili.security.server.context.UserContext;
import org.hbhk.aili.security.share.util.UUIDUitl;
import org.hbhk.maikkr.core.server.dao.IFileDao;
import org.hbhk.maikkr.core.server.service.IFileService;
import org.hbhk.maikkr.user.share.pojo.FileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService implements IFileService {

	private String path = "userImages";
	@Autowired
	private IFileDao fileDao;

	public String saveFile(MultipartFile Filedata) throws IOException {
		String originalFilename = Filedata.getOriginalFilename();
		String user = UserContext.getCurrentContext().getCurrentUserName();
		user = user.substring(0, user.indexOf("."));
		String fileName = String.valueOf(System.currentTimeMillis());
		String sep = System.getProperty("file.separator");
		String url = path
				+ sep
				+ user
				+ sep
				+ fileName
				+ originalFilename.substring(originalFilename.indexOf("."),
						originalFilename.length());
		FileInfo file = new FileInfo();
		file.setId(UUIDUitl.getUuid());
		file.setOrigName(originalFilename);
		file.setName(fileName);
		file.setCreatUser(user);
		file.setCreateTime(new Date());
		file.setUrl(url);
		SpringIOUtils.saveFile(Filedata.getInputStream(), path,
				file.getCreatUser(), file.getName());
		fileDao.save(file);
		return url;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
