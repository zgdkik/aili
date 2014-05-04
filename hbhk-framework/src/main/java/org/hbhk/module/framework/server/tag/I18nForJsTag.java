package org.hbhk.module.framework.server.tag;

import java.io.IOException;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.commons.lang.StringUtils;
import org.hbhk.module.framework.server.context.RequestContext;
import org.hbhk.module.framework.server.message.MessageBundle;
import org.hbhk.module.framework.server.message.MessageCache;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


public class I18nForJsTag extends SimpleTagSupport {

	private MessageBundle messageBundle;
	private String subModule;
	private String groups;
	@Override
	public void setJspContext(JspContext pc) {
		super.setJspContext(pc);
		PageContext pageContext = (PageContext) pc;
		ApplicationContext ctx = WebApplicationContextUtils
				.getWebApplicationContext(pageContext.getServletContext());
		messageBundle = (MessageBundle) ctx.getBean("messageBundle");
	}

	/**
	 * SimpleTagSupport标签执行，调用的主方法
	 * 调用国际化资源接口，遍历传入的国际化键的字符串，查找出每一个键对应的国际化信息，将键和国际化信息封装成一个javascript对象
	 * ，放入到script 标签中
	 * 
	 * @see javax.servlet.jsp.tagext.SimpleTagSupport#doTag() doTag
	 * @throws JspException
	 * @throws IOException
	 * @since JDK1.6
	 */
	@Override
	public void doTag() throws JspException, IOException {
		String moduleName = RequestContext.getCurrentContext().getModuleName();
		//String[] groupArray = parseStringToArray(groups);

		String keys = messageBundle.getI18nKeys(moduleName+MessageCache.I18NKEYS);
		getJspContext().getOut().write(createModuleScript(moduleName));
		getJspContext().getOut().write(createI18nScript(moduleName,keys));
//		if(groupArray.length!=0){
//			for(String group : groupArray){
//				getJspContext().getOut().write(createWroResourceUrl(moduleName, group));
//			}
//		}
	}


	/**
	 * 
	 * <p>
	 * 生成模块javascript代码格式
	 * </p>
	 * @author 平台开发小组
	 * @date 2013-4-1 上午10:51:56
	 * @param moduleName
	 * @return
	 * @see
	 */
	private String createModuleScript(String moduleName) {
		String appcontext = ((PageContext)this.getJspContext()).getServletContext().getContextPath();
		StringBuilder msgObject = new StringBuilder("");
		msgObject.append("<script type='text/javascript'> \n");
		msgObject.append("if(typeof ").append(moduleName)
				.append(" == 'undefined'){\n").append(moduleName)
				.append("={};\n").append("}");
		msgObject.append("\n").append(moduleName)
				.append(".realPath = function (path) { \n");
		msgObject.append("return '")
				.append(appcontext)
				.append("/");
		msgObject.append(moduleName).append("/' + ").append("path;\n");
		msgObject.append("};\n");
		if (subModule != null) {
			msgObject.append("\n").append(moduleName).append(".")
					.append(subModule).append("={}");
		}
		msgObject.append("\n</script>\n");

		return msgObject.toString();
	}

	/**
	 * 
	 * <p>
	 * 生成国际化javascript代码格式
	 * </p>
	 * 
	 * @author 平台开发小组
	 * @date 2013-4-1 上午10:53:36
	 * @param moduleName
	 * @param keys
	 * @return
	 * @see
	 */
	private String createI18nScript(String moduleName, String keys) {
		if (StringUtils.isBlank(keys)) {
			return keys;
		}
		StringBuilder msgObject = new StringBuilder("");
		msgObject.append("<script type='text/javascript'> \n");
		// 声明一个function，用于取message
		msgObject.append(moduleName);
		if (subModule != null) {
			msgObject.append(".");
			msgObject.append(subModule);
		}
		msgObject.append(".i18n = function(key, args) { \n");
		// 声明一个对象，存放message信息
		msgObject.append("msg = {");
		String[] keyArray = parseStringToArray(keys);
		for (String key : keyArray) {
			String message = messageBundle.getMessage(key);
			if (message != null && !"".equals(message)) {
				msgObject.append("'" + key + "'" + ":'" + message + "',");
			}
		}
		// 如果keys为空,下面这句截取字符串会把方法参数中的逗号去掉
		msgObject.deleteCharAt(msgObject.lastIndexOf(","));
		msgObject.append("};");

		msgObject.append("\n");
		msgObject.append("var message = msg[ key] ; \n");
		msgObject.append("if(args != null){  \n");
		msgObject.append("for ( var i = 0; i < args.length; i++) { \n");
		msgObject.append("var reg ='{'+i+'}'; \n");
		msgObject
				.append("message = message.toString().replace(reg, args[i]); \n");
		msgObject.append("} \n");
		msgObject.append("} \n");
		msgObject.append("return message; \n");
		msgObject.append("} \n");
		msgObject.append("</script>");
		return msgObject.toString();
	}

	/**
	 * 
	 * <p>
	 * 将字符串转成字符数组
	 * </p>
	 * 
	 * @author 平台开发小组
	 * @date 2013-4-1 上午10:54:27
	 * @param str
	 * @return
	 * @see
	 */
	private String[] parseStringToArray(String str) {
		if (str == null) {
			return new String[0];
		}
		str = str.replaceAll("\n", "");
		str = str.replaceAll("\r", "");
		String[] keyArray = str.split(",");
		for (int i = 0; i < keyArray.length; i++) {
			keyArray[i] = keyArray[i].trim();
		}
		return keyArray;
	}

	public String getSubModule() {
		return subModule;
	}

	public void setSubModule(String subModule) {
		this.subModule = subModule;
	}

	public String getGroups() {
		return groups;
	}

	public void setGroups(String groups) {
		this.groups = groups;
	}
	
	

}
