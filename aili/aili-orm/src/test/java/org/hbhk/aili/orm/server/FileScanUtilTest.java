package org.hbhk.aili.orm.server;

import java.util.List;

import org.hbhk.aili.orm.share.util.FileScanUtil;

public class FileScanUtilTest {
	
	public static void main(String[] args) throws Exception {
		FileScanUtil f = new FileScanUtil();
		// List<String> paths = f.getFilePath("org/hbhk/");
		// System.out.println(paths.size());
		String dirPath = "org/hbhk/aili";
		List<String> xmls = f.scanBeansXml(dirPath, "orm.xml");
		for (String string : xmls) {
			System.out.println(string);
		}

	}

}
