package org.hbhk.aili.client.core.widget.identify.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.aili.client.core.component.remote.IOUtils;

/**
 * Description: 客户机mac码生成器 一个客户机可能有多个mac码
 */
public class WindowsX86MacGenerator {
	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(WindowsX86MacGenerator.class);

	private static List<String> macList = new ArrayList<String>();

	/**
	 * 生成MAC地址，读取PC机中的所有MAC地址
	 */
	public List<String> generateMacList() {
		String mac = "";
		BufferedReader bufferedReader = null;
		try {
			String command = "ipconfig /all";
			Process process = Runtime.getRuntime().exec(command);
			bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream(),Charset.defaultCharset().name()));

			String line = bufferedReader.readLine();
			for (; line != null;) {
				if (line.indexOf("Physical Address") != -1) {
					int index = line.indexOf(":");
					mac = line.substring(index + "i".length());
					macList.add(mac.trim());
				}
				String nextLine = bufferedReader.readLine();
				line = nextLine;
			}

			process.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("生成MAC地址出错",e);
		} finally {
			IOUtils.close(bufferedReader);
		}
		return macList;
	}

	public static void main(String[] args) {
		List<String> macList2 = new WindowsX86MacGenerator().generateMacList();
		for (String mac : macList2 ) {
			System.out.println("mac : " + mac);
		}
		
		System.err.println(System.getProperty("file.encoding"));
	}

}
