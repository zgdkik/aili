package org.hbhk.maikkr.core.server.service;

import org.springframework.web.multipart.MultipartFile;

public interface IFileService {

   String saveFile(MultipartFile Filedata) throws Exception ;
}
