package org.hbhk.aili.support.server.excel.config.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.hbhk.aili.core.share.util.FileLoadUtil;
import org.hbhk.aili.support.server.excel.config.ConfigConstant;
import org.hbhk.aili.support.server.excel.config.ExcelConfigManager;
import org.hbhk.aili.support.server.excel.config.ExcelConstants;
import org.hbhk.aili.support.server.excel.config.RuturnConfig;
import org.hbhk.aili.support.server.excel.entity.RuturnPropertyParam;

public class ExcelConfigManagerImpl implements ExcelConfigManager {

	private Log log = LogFactory.getLog(getClass());
	private String configName = "ExcelModeMappingl.xml";
	private SAXReader saxReader;
	private Document doc;
	private Element root;

	/****
	 * read ImportExcelToModel.xml
	 * 
	 * @throws IOException
	 */
	public ExcelConfigManagerImpl() throws IOException {
		InputStream in = FileLoadUtil.getResourceForServletpath(configName)
				.getInputStream();
		log.debug("configName:" + configName);
		saxReader = new SAXReader();
		try {
			doc = saxReader.read(in);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		root = doc.getRootElement();
	}

	/***
	 * get node through class name
	 */
	@SuppressWarnings("rawtypes")
	public Element getModelElement(String modelName) {
		log.debug("modelName = " + modelName + "-----------");
		List list = root.elements();
		Element model = null;
		Element returnModel = null;
		for (Iterator it = list.iterator(); it.hasNext();) {
			model = (Element) it.next();
			log.debug(model.attributeValue("id"));
			if (model.attributeValue("id").equals(modelName)) {
				returnModel = model;
				break;
			}
		}
		return returnModel;
	}

	public RuturnConfig getModel(String modelName) {
		Element model = this.getModelElement(modelName);
		RuturnConfig result = new RuturnConfig();
		if (model != null) {
			result.setClassName(model
					.attributeValue(ConfigConstant.MODEL_CLASS));
			result.setPropertyMap(this.getPropertyMap(model));
		}
		return result;
	}

	/****
	 * Analytic/parse properties of the class
	 * 
	 * @param model
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private Map<String, RuturnPropertyParam> getPropertyMap(Element model) {
		Map<String, RuturnPropertyParam> propertyMap = new HashMap<String, RuturnPropertyParam>();
		List list = model.elements();// An attribute of a class
		Element property = null;
		for (Iterator it = list.iterator(); it.hasNext();) {
			property = (Element) it.next();
			RuturnPropertyParam modelProperty = new RuturnPropertyParam();
			modelProperty.setName(property
					.attributeValue(ConfigConstant.PROPERTY_NAME));// property
																	// name in
																	// java bean

			modelProperty.setColumn(property
					.attributeValue(ConfigConstant.PROPERTY_CLOUMN));// sequeence
																		// in
																		// excel
			modelProperty.setExcelTitleName(property
					.attributeValue(ConfigConstant.PROPERTY_EXCEL_TITLE_NAME));// table
																				// title
																				// in
																				// excel

			modelProperty.setDataType(property
					.attributeValue(ConfigConstant.PROPERTY_DATA_TYPE));// data
																		// type:[String,Date]

			modelProperty.setMaxLength(property
					.attributeValue(ConfigConstant.PROPERTY_MAX_LENGTH));
			modelProperty.setColumn(property
					.attributeValue(ConfigConstant.PROPERTY_CLOUMN));

			// if data type is "Date"
			modelProperty.setDateFormat(property
					.attributeValue(ConfigConstant.PROPERTY_FORMAT));
			String isConvertableStr = property
					.attributeValue(ConfigConstant.PROPERTY_ISCONVERTABLE);
			boolean isConvertable = Boolean.parseBoolean(isConvertableStr);
			modelProperty.setConvertable(isConvertable);
			if (isConvertable) {
				List map_list = property.elements();
				Element property_tmp = null;
				for (Iterator it2 = map_list.iterator(); it2.hasNext();) {
					property_tmp = (Element) it2.next();
					if (property_tmp != null) {
						if (property_tmp.getName().equals("map")) {
							Map<String, String> map_tmp = new HashMap<String, String>();
							List entities = property_tmp.elements();
							for (int i = 0; i < entities.size(); i++) {
								Element entity = (Element) entities.get(i);
								String key2 = entity
										.attributeValue(ExcelConstants.MAP_KEY);
								String value2 = entity
										.attributeValue(ExcelConstants.MAP_VALUE);
								if (StringUtils.isNotEmpty(key2)) {
									map_tmp.put(key2, value2);
									log.debug("key:" + key2 + " ,value:"
											+ value2);
								}
							}
							modelProperty.setConvertMap(map_tmp);

						}
					}
				}
			}
			modelProperty.setDefaultValue(property
					.attributeValue(ConfigConstant.PROPERTY_DEFAULT));
			propertyMap.put(modelProperty.getExcelTitleName(), modelProperty);

		}
		return propertyMap;
	}

}
