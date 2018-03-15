package com.deppon.esb.management.web;

import java.security.MessageDigest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MD5Util {
	private static final Logger LOGGER = LoggerFactory.getLogger(MD5Util.class);
	public final static String md5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', '!', '@', '#' };
		try {
			byte[] strTemp = s.getBytes();
			// 使用MD5创建MessageDigest对象
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte b = md[i];
				// System.out.println((int)b);
				// 将每个数(int)b进行双字节加密
				str[k++] = hexDigits[b >> 6 & 0xf];
				str[k++] = hexDigits[b & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}

	// 测试
	public static void main(String[] args) {
		LOGGER.info("123的MD5加密后：" + MD5Util.md5("123"));
		LOGGER.info("huanghua的MD5加密后：" + MD5Util.md5("huanghua"));
		
		LOGGER.info(null, "fa02e3eafdefe4e908eff81e02f01101".length());
		
	}
}
