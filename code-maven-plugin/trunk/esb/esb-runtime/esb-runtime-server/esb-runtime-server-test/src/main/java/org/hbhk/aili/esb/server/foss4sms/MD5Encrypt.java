package org.hbhk.aili.esb.server.foss4sms;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.cxf.common.util.Base64Utility;
import org.apache.log4j.Logger;

/**
 * MD5加密.
 * 
 * @author qiancheng
 */
public class MD5Encrypt {
	
	/** The log. */
	private static Logger log = Logger.getLogger(MD5Encrypt.class);
	
	/** The Constant ALGORITHM. */
	private static final String ALGORITHM = "MD5";
	
	/**
	 * encrypt md5加密+base64编码<br/>
	 * <br/>
	 * <b>创建人：</b>wanghui<br/>
	 * <b>修改人：</b>wanghui<br/>
	 * .
	 * 
	 * @param param
	 *            the param
	 * @return String
	 * @date 2011-9-8
	 * @since 1.0.0
	 */
	public static String encrypt(String param){
		MessageDigest digest = null;
		String result = null;
		if(param == null){
			return null;
		}
		try {
			digest = MessageDigest.getInstance(ALGORITHM);
			result = Base64Utility.encode(digest.digest(param.getBytes("UTF-8")));
		} catch (NoSuchAlgorithmException e) {
			log.error("", e);
		} catch (UnsupportedEncodingException e) {
			log.error("", e);
		}	
		return result;
	}
}