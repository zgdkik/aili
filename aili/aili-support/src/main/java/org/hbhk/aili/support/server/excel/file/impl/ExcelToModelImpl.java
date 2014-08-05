package org.hbhk.aili.support.server.excel.file.impl;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.aili.support.server.excel.config.RuturnConfig;
import org.hbhk.aili.support.server.excel.entity.RuturnPropertyParam;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class ExcelToModelImpl {

	private Log log = LogFactory.getLog(getClass());
	private File excelFile = null;

	private RuturnConfig excelConfig = null;

	// private Map valueMap = null;

	public ExcelToModelImpl(File excelFile, RuturnConfig excelConfig) {
		this.excelConfig = excelConfig;
		this.excelFile = excelFile;
	}

	// private void setFixity() {
	// List fixityList = new ArrayList();
	// Map pmap = this.excelConfig.getPropertyMap();
	// for (Iterator it = pmap.values().iterator(); it.hasNext();) {
	// RuturnPropertyParam propertyBean = (RuturnPropertyParam) it.next();
	// if (propertyBean.getFixity().toUpperCase().trim().equals("YES")) {
	// fixityList.add(propertyBean);
	// }
	// }
	// this.fixityList = fixityList;
	// }

	public List<Object> getModelList() throws ParseException {
		List<Object> modelList = new ArrayList<Object>();
		try {
			Workbook book;

			book = Workbook.getWorkbook(this.excelFile);
			Sheet sheet = book.getSheet(0);

			// editBook = Workbook.createWorkbook(file, book);

			// System.out.println("rows = " + sheet.getRows() + "  cols ="+
			// sheet.getColumns());
			for (int i = 1; i < sheet.getRows(); i++) {
				Object obj = this.getModelInstance(excelConfig.getClassName());// new
																				// a
																				// object
				BeanWrapper bw = new BeanWrapperImpl(obj);
				// ��excelÿһ�е�ֵ����ȡֵ.
				for (int j = 0; j < sheet.getColumns(); j++) {

					// System.out.println("i = " + i + " j =" + j);
					// Excel title name
					String excelTitleName = sheet.getCell(j, 0).getContents()
							.trim();
					// ȡ��Excelֵ
					String value = sheet.getCell(j, i).getContents().trim();
					//
					RuturnPropertyParam propertyBean = (RuturnPropertyParam) excelConfig
							.getPropertyMap().get(excelTitleName);

					// System.out.println("i = " + i + "  j =" + j + " value ="+
					// value + " title = " + excelTitleName);

					if (propertyBean != null) {// is null when is in excel ,but
												// not in bean
						log.debug("propertyName = " + propertyBean.getName());
						// should be in front of convert map
						if (value == null || value.length() < 1) {
							value = propertyBean.getDefaultValue();
						}

						if (propertyBean.isConvertable()) {
							value = propertyBean.getConvertMap().get(value);
						}

						String dateType = propertyBean.getDataType();
						Date date2 = null;
						// convert string to Date
						if (dateType.equalsIgnoreCase("Date")) {
							String dateFormat = propertyBean.getDateFormat();
							if (StringUtils.isNotEmpty(dateFormat)) {
								SimpleDateFormat sdf = new SimpleDateFormat(
										dateFormat);
								date2 = sdf.parse(value);
							}
						}
						if (date2 == null) {
							bw.setPropertyValue(propertyBean.getName(), value);
						} else {// property type is Date
							bw.setPropertyValue(propertyBean.getName(), date2);
						}

					}

				}
				modelList.add(obj);
			}
			book.close();

		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return modelList;
	}

	/***
	 * Instantiate object
	 * 
	 * @param className
	 * @return
	 */
	private Object getModelInstance(String className) {
		Object obj = null;
		try {
			obj = Class.forName(className).newInstance();
			log.debug("init Class = " + className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return obj;
	}
}
