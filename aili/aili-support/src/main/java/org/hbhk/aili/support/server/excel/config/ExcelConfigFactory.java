package org.hbhk.aili.support.server.excel.config;

import java.io.IOException;

import org.hbhk.aili.support.server.excel.config.impl.ExcelConfigManagerImpl;

public class ExcelConfigFactory {
	private static ExcelConfigManager instance;

	public static ExcelConfigManager createExcelConfigManger()
			throws IOException {
		instance = new ExcelConfigManagerImpl();
		return instance;
	}
}
