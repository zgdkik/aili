package org.hbhk.maikkr.core.server.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface IFileService {

   String saveFile(MultipartFile Filedata) throws Exception ;
   String saveFile(MultipartFile Filedata ,int width ,int height) throws IOException;
}
