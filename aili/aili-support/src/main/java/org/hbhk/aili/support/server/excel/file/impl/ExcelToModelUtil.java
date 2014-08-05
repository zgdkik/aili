package org.hbhk.aili.support.server.excel.file.impl;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.aili.support.server.excel.config.ExcelConfigFactory;
import org.hbhk.aili.support.server.excel.config.ExcelConfigManager;

/***
 * convert excel to java bean
 */
public class ExcelToModelUtil {
	private  static Log log = LogFactory.getLog(ExcelToModelUtil.class);
	/***
	 * 
	 * @param excelFile
	 * @param modelName   : id in excel config file
	 * @return
	 * @throws ParseException 
	 * @throws IOException 
	 */
	public static List<Object> getModelList(File excelFile,String modelName) throws ParseException, IOException{
		if(!excelFile.exists()){
			log.debug( ExcelToModelUtil.class.getSimpleName()+" [getModelList:]"+excelFile.getAbsolutePath()+" does not exist.");
			return null;
		}
		ExcelConfigManager configManager = ExcelConfigFactory.createExcelConfigManger();
		ExcelToModelImpl etm =  new ExcelToModelImpl(excelFile,configManager.getModel(modelName));
		List<Object> modelList = etm.getModelList();
		
		return modelList;
	}
	public static List<Object> getModelList(String excelFileStr,String modelName) throws ParseException, IOException{
		File excelFile=new File(excelFileStr);
		return getModelList(excelFile, modelName);
	}
}
