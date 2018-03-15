package org.hbhk.aili.route.jms.common.spring.dynamic;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;

/**
 * 动态资源.
 * 
 * @author HuangHua
 */
public class DynamicResource implements Resource {
	
	/** The logger. */
	private static Logger logger = LoggerFactory.getLogger(DynamicResource.class);
	
	/** The dynamic bean. */
	private DynamicBean dynamicBean;

	/**
	 * Instantiates a new dynamic resource.
	 * 
	 * @param dynamicBean
	 *            the dynamic bean
	 */
	public DynamicResource(DynamicBean dynamicBean) {
		this.dynamicBean = dynamicBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.core.io.InputStreamSource#getInputStream()
	 */
	/** 
	 * TODO（方法详细描述说明、方法参数的具体涵义）
	 * @author HuangHua
	 * @date 2012-12-25 下午5:59:20
	 * @see org.springframework.core.io.InputStreamSource#getInputStream()
	 */
	@Override
	public InputStream getInputStream() throws IOException {
		logger.debug(dynamicBean.getXml());
		return new ByteArrayInputStream(dynamicBean.getXml().getBytes("UTF-8"));
	}

	/** 
	 * TODO（方法详细描述说明、方法参数的具体涵义）
	 * @author HuangHua
	 * @date 2012-12-25 下午5:59:20
	 * @see org.springframework.core.io.Resource#exists()
	 */
	@Override
	public boolean exists() {
		return false;
	}

	/** 
	 * TODO（方法详细描述说明、方法参数的具体涵义）
	 * @author HuangHua
	 * @date 2012-12-25 下午5:59:20
	 * @see org.springframework.core.io.Resource#isReadable()
	 */
	@Override
	public boolean isReadable() {
		return false;
	}

	/** 
	 * TODO（方法详细描述说明、方法参数的具体涵义）
	 * @author HuangHua
	 * @date 2012-12-25 下午5:59:20
	 * @see org.springframework.core.io.Resource#isOpen()
	 */
	@Override
	public boolean isOpen() {
		return false;
	}

	/** 
	 * TODO（方法详细描述说明、方法参数的具体涵义）
	 * @author HuangHua
	 * @date 2012-12-25 下午5:59:20
	 * @see org.springframework.core.io.Resource#getURL()
	 */
	@Override
	public URL getURL() throws IOException {
		return null;
	}

	/** 
	 * TODO（方法详细描述说明、方法参数的具体涵义）
	 * @author HuangHua
	 * @date 2012-12-25 下午5:59:20
	 * @see org.springframework.core.io.Resource#getURI()
	 */
	@Override
	public URI getURI() throws IOException {
		return null;
	}

	/** 
	 * TODO（方法详细描述说明、方法参数的具体涵义）
	 * @author HuangHua
	 * @date 2012-12-25 下午5:59:20
	 * @see org.springframework.core.io.Resource#getFile()
	 */
	@Override
	public File getFile() throws IOException {
		return null;
	}

	/** 
	 * TODO（方法详细描述说明、方法参数的具体涵义）
	 * @author HuangHua
	 * @date 2012-12-25 下午5:59:20
	 * @see org.springframework.core.io.Resource#contentLength()
	 */
	@Override
	public long contentLength() throws IOException {
		return 0;
	}

	/** 
	 * TODO（方法详细描述说明、方法参数的具体涵义）
	 * @author HuangHua
	 * @date 2012-12-25 下午5:59:20
	 * @see org.springframework.core.io.Resource#lastModified()
	 */
	@Override
	public long lastModified() throws IOException {
		return 0;
	}

	/** 
	 * TODO（方法详细描述说明、方法参数的具体涵义）
	 * @author HuangHua
	 * @date 2012-12-25 下午5:59:20
	 * @see org.springframework.core.io.Resource#createRelative(java.lang.String)
	 */
	@Override
	public Resource createRelative(String relativePath) throws IOException {
		return null;
	}

	/** 
	 * TODO（方法详细描述说明、方法参数的具体涵义）
	 * @author HuangHua
	 * @date 2012-12-25 下午5:59:20
	 * @see org.springframework.core.io.Resource#getFilename()
	 */
	@Override
	public String getFilename() {
		return null;
	}

	/** 
	 * TODO（方法详细描述说明、方法参数的具体涵义）
	 * @author HuangHua
	 * @date 2012-12-25 下午5:59:20
	 * @see org.springframework.core.io.Resource#getDescription()
	 */
	@Override
	public String getDescription() {
		return null;
	}
}
