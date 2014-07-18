package org.hbhk.rss.core.server.cache;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class InputStreamCacher {
	private static final Log log = LogFactory.getLog(MemoryCacheTemplet.class);
	/**
	 * 将InputStream中的字节保存到ByteArrayOutputStream中。
	 */
	private ByteArrayOutputStream byteArrayOutputStream = null;
	
	public InputStreamCacher(InputStream inputStream) {
		if (inputStream==null){
			return;
		}
		byteArrayOutputStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];  
		int len;  
		try {
			while ((len = inputStream.read(buffer)) > -1 ) {  
				byteArrayOutputStream.write(buffer, 0, len);  
			}
			byteArrayOutputStream.flush();
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}  
	}
	
	public InputStream getInputStream() {
		if (byteArrayOutputStream==null){
			return null;
		}
		return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
	}
}
