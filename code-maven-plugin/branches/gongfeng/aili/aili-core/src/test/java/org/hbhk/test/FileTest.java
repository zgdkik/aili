package org.hbhk.test;

import java.io.File;

import org.apache.commons.io.FileUtils;

public class FileTest {

	
	public static void main(String[] args) throws Exception {
		StringBuilder data = new StringBuilder();
		for (int i = 0; i <10000; i++) {
//			for (int j = 0; j <10; j++) {
//				
//			}
			data.append("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
			data.append("\r");
		}
		File file = new File("F:/java-dev/workspaces/hbhkws00/aili/aili-core/src/test/resources/file/data.txt");
		System.out.println(data.toString().getBytes().length/1024/1024);
		FileUtils.writeStringToFile(file, data.toString());
	}
}
