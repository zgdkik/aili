package org.hbhk.aili.base.share.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class InputSteamUtil {

	public static InputStream getInputStreamFromStr(String str) {
		InputStream in = new ByteArrayInputStream(str.getBytes());
		return in;
	}

	public static String getStrFromInputSteam(InputStream in) {
		BufferedReader bf;
		try {
			bf = new BufferedReader(new InputStreamReader(in, "UTF-8"));
			// 最好在将字节流转换为字符流的时候 进行转码
			StringBuffer buffer = new StringBuffer();
			String line = "";
			while ((line = bf.readLine()) != null) {
				buffer.append(line);
			}
			return buffer.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
}
