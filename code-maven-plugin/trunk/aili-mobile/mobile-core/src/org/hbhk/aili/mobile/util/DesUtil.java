package org.hbhk.aili.mobile.util;

import android.annotation.SuppressLint;
import java.security.Key;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

/**
 * 
 * @Description: aili框架核心处理和基本支持
 * @author 何波
 * @date 2015年3月11日 上午10:05:24
 * 
 */
public class DesUtil {

	private static Key key;
	private static String seckey = "seckey";

	@SuppressLint("TrulyRandom")
	public DesUtil(String seckey) {
		try {
			if (seckey == null || seckey == "") {
				seckey = DesUtil.seckey;
			}
			KeyGenerator generator = KeyGenerator.getInstance("DES");
			generator.init(new SecureRandom(seckey.getBytes()));
			key = generator.generateKey();
			generator = null;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public String encypt(String str) {
		try {
			byte[] strBytes = str.getBytes();
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] encyptStrBytes = cipher.doFinal(strBytes);
			return new String(encyptStrBytes, "UTF-8");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	public String decypt(String str) {
		try {
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] encyptStrBytes = cipher.doFinal(str.getBytes());
			return new String(encyptStrBytes, "UTF-8");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	public static void main(String[] args) throws Exception {
		DesUtil des1 = new DesUtil("hbhk");

		String str = "hbhk";
		String en = des1.encypt(str);
		System.out.println(en);

		DesUtil des2 = new DesUtil("hbhk");
		String de = des2.decypt(en);
		System.out.println(de);

		DesUtil des3 = new DesUtil("hbhk1");
		String de3 = des3.decypt("BGV3WAvtdZ4=");
		System.out.println(de3);
	}
	
}
