package org.hbhk.aili.support.server.excel.file.impl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.hbhk.aili.support.server.excel.config.ExcelConfigFactory;
import org.hbhk.aili.support.server.excel.config.ExcelConfigManager;
import org.hbhk.aili.support.server.excel.config.RuturnConfig;
import org.hbhk.aili.support.server.excel.entity.RuturnPropertyParam;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

/***
 * convert java beans to excel
 * 
 */
public class ModelToExcelUtil {


	/***
	 * export to excel file from java benas convert java beans to excel file
	 * 
	 * @param excelFile
	 * @param modelName
	 * @param models
	 * @return
	 * @throws IOException
	 * @throws RowsExceededException
	 * @throws WriteException
	 */
	public static File model2Excel(File excelFile, String modelName,
			List<Object> models) throws IOException, RowsExceededException,
			WriteException {
		if (excelFile.exists()) {// delete excel file if this file has exist
			excelFile.delete();
		}
		if (!excelFile.exists()) {
			excelFile.createNewFile();// create a new excel file
		}
		// read "ImportExcelToModel.xml" file
		ExcelConfigManager configManager = ExcelConfigFactory
				.createExcelConfigManger();
		RuturnConfig returnConfig = configManager.getModel(modelName);
		WritableWorkbook wb = Workbook.createWorkbook(excelFile);
		WritableSheet wsheet = wb.createSheet("first page", 0);

		WritableFont font1 = new WritableFont(WritableFont.ARIAL, 10,
				WritableFont.BOLD);// Set the table header in bold
		WritableCellFormat format1 = new WritableCellFormat(font1);
		Map<String,RuturnPropertyParam> propertyMap = returnConfig.getPropertyMap();
		int columns_size = propertyMap.size();

		// Setting excel table header sequence according column in xml;
		// column start from one
		for (Iterator<String> it = propertyMap.keySet().iterator(); it.hasNext();) {
			String key = (String) it.next();
			RuturnPropertyParam modelProperty = (RuturnPropertyParam) propertyMap
					.get(key);
			int column = Integer.parseInt(modelProperty.getColumn());// sequence
																		// in
																		// excel
																		// file
			wsheet.addCell(new Label(column - 1, 0, key, format1));
		}

		// write real data to excel file from beans list
		for (int i = 0; i < models.size(); i++) {
			Object obj = models.get(i);
			BeanWrapper bw = new BeanWrapperImpl(obj);// use spring tools(java
														// reflection)
			for (int k = 0; k < columns_size; k++) {
				String excelTitleName = wsheet.getCell(k, 0).getContents()
						.trim();// title name in excel
				RuturnPropertyParam modelProperty = (RuturnPropertyParam) propertyMap
						.get(excelTitleName);
				String beanPropertyName = modelProperty.getName();// property
																	// name in
																	// java
																	// object
				Object propertyValue = bw.getPropertyValue(beanPropertyName);// property
																				// value
																				// in
																				// java
																				// object

				String dataType = modelProperty.getDataType();
				if (dataType.equalsIgnoreCase("Date")) {// convert date to
														// string
					SimpleDateFormat sdf = new SimpleDateFormat(
							modelProperty.getDateFormat());
					propertyValue = sdf.format((Date) propertyValue);
				}
				if (modelProperty.isConvertable()) {// whether is convertable
													// ,see xml file
					Map<String, String> convertMap = modelProperty
							.getConvertMap();
					propertyValue = (String) convertMap.get(propertyValue);
				}

				wsheet.addCell(new Label(Integer.parseInt(modelProperty
						.getColumn()) - 1, i + 1, (String) propertyValue));
			}
		}
		wb.write();// save excel
		wb.close();// close excel file
		return excelFile;
	}

	/***
	 * 
	 * @param excelFileStr
	 *            :path of excel file;type:String
	 * @param modelName
	 * @param models
	 * @return
	 * @throws IOException
	 * @throws RowsExceededException
	 * @throws WriteException
	 */
	public static File model2Excel(String excelFileStr, String modelName,
			List<Object> models) throws IOException, RowsExceededException,
			WriteException {
		File excelFile = new File(excelFileStr);
		return model2Excel(excelFile, modelName, models);
	}
}
