package org.hbhk.aili.base.share.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

public class MD5Util {

	public static String encode(String src) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] d = md5.digest(src.getBytes());
			BASE64Encoder base64en = new BASE64Encoder();
			String result = base64en.encode(d);
			return result.toString();
		} catch (NoSuchAlgorithmException e) {//此异常通常不会抛出
			throw new java.lang.RuntimeException(e);
		}
	}

}
