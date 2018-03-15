package org.hbhk.aili.client.start.update;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.codec.digest.DigestUtils;


public class ScanFilesForUpdate {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			String basepath = "D:\\dpwork_local\\foss_trunk\\pkp\\pkp-gui\\appHome";
			String excludeItems = ",update,logs,print,src,.settings,.classpath,.project,pom,bin,";
			//String forceItems = "*.jar,";
				
			StringBuffer out = new StringBuffer();
			File pf = new File(basepath);
			ScanFilesForUpdate update = new ScanFilesForUpdate();
			update.scan(pf,out,"",excludeItems);
			System.out.println(out.toString());
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void scan(File pf,StringBuffer pout,String parentfolder,String excludeItems) throws Exception {
		
		File[] files = pf.listFiles();
		for(File file : files){
			String fname = file.getName();
			if(excludeItems.indexOf(","+fname+",")==-1){
				if(file.isFile()){
					String str = "";
					String str1 = "";
					if(parentfolder.indexOf("lib")!=-1 || parentfolder.indexOf("plugins")!=-1 || parentfolder.indexOf("script")!=-1){
						str = parentfolder+"/"+fname+"=F";
					}
					else {
						str = parentfolder+"/"+fname+"=1";
					}
					if(str.startsWith("/")){
						str = str.substring(1);
					}
					str1 = parentfolder+"/"+fname;
					if(str1.startsWith("/")){
						str1 = str1.substring(1);
					}
					pout.append(str1 + "=" + DigestUtils.md5Hex(new FileInputStream(file))+"\n");
				}
				else if(file.isDirectory()){
					String folder = file.getName();
					scan(file, pout, parentfolder+"/"+folder ,excludeItems);
				}	
			}
		}
	}
}