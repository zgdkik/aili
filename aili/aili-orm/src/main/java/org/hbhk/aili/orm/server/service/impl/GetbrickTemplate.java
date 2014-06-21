package org.hbhk.aili.orm.server.service.impl;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import jetbrick.template.JetEngine;
import jetbrick.template.JetTemplate;
import jetbrick.template.ResourceNotFoundException;

import org.hbhk.aili.core.server.web.WebApplicationContextHolder;
import org.hbhk.aili.orm.server.service.IGetbrickTemplate;
import org.hbhk.aili.orm.server.surpport.OrmConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service(value = "getbrickTemplate")
public class GetbrickTemplate implements IGetbrickTemplate {

	@Override
	public String setContextData(Map<String, Object> context, String beanId) {
		JetTemplate template = getTemplate(beanId);
		// 渲染模板
		StringWriter writer = new StringWriter();
		template.render(context, writer);
		return writer.toString();
	}

	@Override
	public JetTemplate getTemplate(String beanId)
			throws ResourceNotFoundException {
		JetEngine engine = JetEngine.create();
		ApplicationContext context = WebApplicationContextHolder
				.getApplicationContext();
		OrmConfig orm = context.getBean(beanId, OrmConfig.class);
		String sql = orm.getSql();
		sql = sql.replace("[", "{");
		sql = sql.replace("]", "}");
		// 获取一个模板对象
		JetTemplate template = engine.createTemplate(sql);
		return template;
	}

	public static void main(String[] args) {

		JetEngine engine = JetEngine.create();

		// 获取一个模板对象
		JetTemplate template = engine.createTemplate("${name}");

		// 创建 context 对象
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("name", "hbhk");
		// 渲染模板
		StringWriter writer = new StringWriter();
		template.render(context, writer);

		// 打印结果
		System.out.println(writer.toString());
	}

}
