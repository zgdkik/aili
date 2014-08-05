package org.hbhk.aili.support.server.excel.config;

import org.dom4j.Element;

public interface ExcelConfigManager {
	public Element getModelElement(String modelName);
	public RuturnConfig getModel(String modelName);
}
