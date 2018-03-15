package org.hbhk.aili.support.server.util;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class FreemarkerUtil {
	/** 编码格式 UTF-8 */
	private static final String ENCODING = "UTF-8";

	/** FreeMarker配置 */
	private static Configuration config = new Configuration();

	public static String parseStr(String str, Map<String, Object> data) {
		StringTemplateLoader stringLoader = new StringTemplateLoader();
		stringLoader.putTemplate("strTemplate", str);
		config.setTemplateLoader(stringLoader);
		try {
			Template template = config.getTemplate("strTemplate", ENCODING);
			StringWriter writer = new StringWriter();
			template.process(data, writer);
			writer.flush();
			return writer.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("name", "hbhk");
		String templateContent = "欢迎：${name}";
		String str = parseStr(templateContent, data);
		System.out.println(str);
	}
}
