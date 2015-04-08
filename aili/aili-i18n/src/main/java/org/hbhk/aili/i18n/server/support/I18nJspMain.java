/**
 * 
 */
package org.hbhk.aili.i18n.server.support;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.aspectj.util.FileUtil;
import org.hbhk.aili.i18n.server.support.file.handler.change.ChangeChineseToSpringI18n;
import org.hbhk.aili.i18n.server.support.file.handler.extract.ExtractJspChineseStringToPropertyFile;
import org.hbhk.aili.i18n.server.support.file.through.ThroughFile;
import org.junit.Test;



public class I18nJspMain{
	
	private static I18nJspMain i18nJspMain = new I18nJspMain();
	private static  String	PROPER_FILE = "i18n/message";
	
	private  I18nJspMain() {
	}
	
	public static I18nJspMain getInstance(String messsageFile){
		I18nJspMain.PROPER_FILE = messsageFile;
		return i18nJspMain;
	}
 
	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception{
	
	}
	@Test
	public void collectChinese(String i18nFile,String path,String tempFile, String i18nPrefix) throws Exception{
		File f = new File(path+tempFile);
		f.delete();
		
		ExtractJspChineseStringToPropertyFile extract = new ExtractJspChineseStringToPropertyFile(i18nPrefix);
		extract.setToFile(path+tempFile);
		extract.setCheckMap(getCheckMap());
		ThroughFile through = new ThroughFile();
		through.setDefaultFileHandler(extract);
		through.throughFileAndHandle(new File(i18nFile));
	}
	@Test
	public void pushCodeTojsp(String i18nFile,String path,String tempFile,String bakDir) throws Exception{
		
		File iFile = new File(i18nFile);
		//替换后文件的输出路径
		String reslutPath = i18nFile;
		if(iFile.isDirectory()){
			//备份
			FileUtil.copyDir(iFile, new File(path+bakDir));
		}else{
			reslutPath = i18nFile.substring(0, i18nFile.lastIndexOf("/")+1);
			//备份
			FileUtil.copyFile(iFile, new File(path+bakDir+iFile.getName()));
		}
		
		ChangeChineseToSpringI18n change = new ChangeChineseToSpringI18n();
		change.setChangeMap(getPropertiesMap(path,tempFile));
		change.setResultPath(reslutPath);
		change.setBasePath(path+bakDir);
		
		ThroughFile through = new ThroughFile();
		through.setDefaultFileHandler(change);
		through.throughFileAndHandle(new File(path+bakDir));
	}
	private Map<String,String> getCheckMap(){
		ResourceBundle bundle = ResourceBundle.getBundle(PROPER_FILE,Locale.SIMPLIFIED_CHINESE);
		Map<String, String>	checkMap = new HashMap<String, String>();
		Enumeration<String> keys = bundle.getKeys();
		while(keys.hasMoreElements()){
			String key = keys.nextElement();
			checkMap.put(bundle.getString(key), key);
		}
		return checkMap;
	}
	
	private Map<String,String> getPropertiesMap(String wp,String tf) throws Exception{
		Map<String, String>	propertiesMap = getCheckMap();
		BufferedReader in = new BufferedReader(new FileReader(wp+tf));
		String line = null;
		while ((line = in.readLine()) != null){
			if(line.trim().length()>0){
				String[] split = line.split("=");
				propertiesMap.put(split[1], split[0]);
				
			}
		}
		return propertiesMap;
	}
}
