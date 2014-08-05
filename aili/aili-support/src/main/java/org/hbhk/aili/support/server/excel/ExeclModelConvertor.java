package org.hbhk.aili.support.server.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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
import org.hbhk.aili.support.server.excel.cache.ExcelConfigCache;
import org.hbhk.aili.support.server.excel.model.Entry;
import org.hbhk.aili.support.server.excel.model.Model;
import org.hbhk.aili.support.server.excel.model.Property;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class ExeclModelConvertor {
	private Log log = LogFactory.getLog(getClass());

	public <T> T getModel(File file, String modelId) {
		InputStream stream = null;
		try {
			stream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			log.error("getModel", e);
			throw new RuntimeException(e);
		}
		return getModel(stream, modelId);
	}

	@SuppressWarnings({ "unchecked" })
	public <T> T getModel(InputStream stream, String modelId) {
		if (stream == null) {
			log.error("getModel  stream 为空");
			throw new RuntimeException("stream 为空");
		}
		if (modelId == null || modelId.equals("")) {
			log.error("getModel  modelId 为空");
			throw new RuntimeException("modelId 为空");
		}
		Model model = ExcelConfigCache.cache.get(modelId);
		if (model == null) {
			log.error("getModel" + modelId + "对应的excel配置没有找到");
			throw new RuntimeException(modelId + "对应的excel配置没有找到");
		}

		Workbook book = null;
		try {
			book = Workbook.getWorkbook(stream);
		} catch (BiffException e) {
			log.error("getModel", e);
			throw new RuntimeException(e);
		} catch (IOException e) {
			log.error("getModel", e);
			throw new RuntimeException(e);
		}
		Sheet sheet = book.getSheet(0);
		List<Object> results = new ArrayList<Object>();
		for (int i = 1; i < sheet.getRows(); i++) {
			Object t = getModelInstance(model.getClazz().trim());
			BeanWrapper bw = new BeanWrapperImpl(t);
			for (int j = 0; j < sheet.getColumns(); j++) {
				String excelTitleName = sheet.getCell(j, 0).getContents()
						.trim();
				Property property = getPropertyName(model, excelTitleName);
				if (property != null) {
					String value = sheet.getCell(j, i).getContents().trim();
					if (value == null || value.length() < 1) {
						value = property.getDefault();
					}
					if (property.getIsConvertable() != null
							&& property.getIsConvertable().equals("true")) {
						value = getPropertyName(property.getMap().getEntry(),
								value);
					}
					String dateType = property.getDataType();
					Date date = null;
					if (dateType != null && dateType.equalsIgnoreCase("Date")) {
						String dateFormat = property.getFormat();
						if (StringUtils.isEmpty(dateFormat)) {
							dateFormat = "yyyy-MM-dd HH:mm:ss";
						}
						SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
						try {
							date = sdf.parse(value);
						} catch (ParseException e) {
							log.error("getModel", e);
							throw new RuntimeException(e);
						}
					}
					if (date != null) {
						bw.setPropertyValue(property.getName(), date);
					} else {
						bw.setPropertyValue(property.getName(), value);
					}
				}

			}
			results.add(t);
		}
		return (T) results;

	}

	private String getPropertyName(List<Entry> entries, String value) {
		for (Entry entry : entries) {
			if (value.equals(entry.getExcelKey().trim())) {
				return entry.getBeanValue();
			}
		}
		return null;

	}

	private Property getPropertyName(Model model, String excelTitleName) {
		List<Property> properties = model.getProperty();
		for (Property property : properties) {
			if (excelTitleName.equals(property.getExcelTitleName().trim())) {
				return property;
			}
		}
		return null;

	}

	private Object getModelInstance(String className) {
		Object obj = null;
		try {
			obj = Class.forName(className).newInstance();
			log.debug("init Class = " + className);
		} catch (ClassNotFoundException e) {
			log.error("getModelInstance", e);
			throw new RuntimeException(e);
		} catch (InstantiationException e) {
			log.error("getModelInstance", e);
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			log.error("getModelInstance", e);
			throw new RuntimeException(e);
		}
		return obj;
	}
}
