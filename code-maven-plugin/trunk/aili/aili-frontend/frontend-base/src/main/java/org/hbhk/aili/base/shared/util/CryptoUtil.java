package org.hbhk.aili.base.shared.util;
//package com.deppon.foss.module.frameworkimpl.shared.util;
//
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//
//import com.deppon.foss.framework.shared.encypt.base64.BASE64Encoder;
//import com.deppon.foss.module.frameworkimpl.shared.exception.EncodeMd5Exception;
///**
// * OA MD5加密方式工具类
// * @author Administrator
// *
// */
//public class CryptoUtil {
//
//	private static final Log logger = LogFactory.getLog(CryptoUtil.class);
//
//	public CryptoUtil() {
//	}
//
//	public static String base64Encode(byte bytes[]) {
//		if (bytes == null){
//			return "";
//		}else{
//			return (new BASE64Encoder()).encode(bytes);
//		}
//	}
//
//	public static String digestByMD5(String text) {
//		if (text == null){
//			return null;
//		}
//		byte digest[] = new byte[0];
//		try {
//			digest = md5(text.getBytes());
//			return base64Encode(digest);
//		} catch (NoSuchAlgorithmException e) {
//			logger.error(e);
//			throw new EncodeMd5Exception(e);
//		}
//	}
//
//	private static byte[] md5(byte input[]) throws NoSuchAlgorithmException {
//		MessageDigest alg = MessageDigest.getInstance("MD5");
//		alg.update(input);
//		byte digest[] = alg.digest();
//		return digest;
//	}
//}
