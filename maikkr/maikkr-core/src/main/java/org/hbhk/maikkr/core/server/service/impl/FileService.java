package org.hbhk.maikkr.core.server.service.impl;

import java.io.IOException;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.aili.core.share.util.SpringIOUtils;
import org.hbhk.aili.security.server.context.UserContext;
import org.hbhk.aili.security.share.util.UUIDUitl;
import org.hbhk.maikkr.core.server.dao.IFileDao;
import org.hbhk.maikkr.core.server.service.IFileService;
import org.hbhk.maikkr.user.share.pojo.FileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService implements IFileService {

	private Log log = LogFactory.getLog(getClass());

	@Value("${filepath}")
	private String path;
	@Autowired
	private IFileDao fileDao;

	public String saveFile(MultipartFile Filedata) throws IOException {
		String originalFilename = Filedata.getOriginalFilename();
		String user = UserContext.getCurrentContext().getCurrentUserName();
		FileInfo file = new FileInfo();
		String suffix = originalFilename.substring(
				originalFilename.indexOf("."), originalFilename.length());
		log.debug("originalFilename:" + originalFilename + ":" + suffix);
		file.setCreatUser(user);
		String fileName = "m"+String.valueOf(System.currentTimeMillis());
		String sep = System.getProperty("file.separator");
		String url = sep + user + sep + fileName + suffix;

		file.setId(UUIDUitl.getUuid());
		file.setOrigName(originalFilename);
		file.setName(fileName);
		file.setCreateTime(new Date());
		file.setUrl(url);
		SpringIOUtils.saveFile(Filedata.getInputStream(), path,
				user, fileName + suffix);
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
