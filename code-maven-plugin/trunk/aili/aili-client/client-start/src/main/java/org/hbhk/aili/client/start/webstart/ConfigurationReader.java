package org.hbhk.aili.client.start.webstart;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ConfigurationReader {
	private transient Map configuration = new HashMap();

	public Map getConfiguration(){
		return this.configuration;
	}

	public ConfigurationReader(String filename) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		read(reader);
		reader.close();
	}

	protected void read(BufferedReader reader) throws IOException {
		String line;
		while ((line = reader.readLine()) != null) {
			parseLine(line);
		}
	}

	protected void parseLine(String line) {
		String newline = line.trim();
		if (newline.indexOf('=') >= 0) {
			int i = newline.indexOf('=');
			String name = newline.substring(0, i).trim();
			String value = newline.substring(i + 1).trim();
			configuration.put(name, value);
		}
	}

	/**
	 * 读取相关配置
	 * @param section段落
	 * @param name键值
	 * @return
	 */
	public String getValue(String name) {
		if (configuration == null) {
			return null;
		}
		String value = (String) configuration.get(name.trim());
		return value;
	}
}