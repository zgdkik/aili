package org.hbhk.maikkr.core.server.service;

import org.springframework.web.multipart.MultipartFile;

public interface IFileService {

   void saveFile(MultipartFile Filedata) throws Exception ;
}
