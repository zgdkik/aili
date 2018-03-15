package com.deppon.esb.management.web.deploy.struts.result.json;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.Arrays;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.StrutsConstants;
import org.apache.struts2.json.JSONException;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.Result;
import com.opensymphony.xwork2.inject.Inject;

public class JSONTreeResult implements Result {

	private static final long serialVersionUID = 8470874076465433284L;
	/** 共用一个静态变量，缓存解析过的类型 */
	protected static final ObjectMapper MAPPER = new ObjectMapper();

	private static final Logger LOGGER = LoggerFactory.getLogger(JSONTreeResult.class);

	private String defaultEncoding = "UTF-8";

	@Inject(StrutsConstants.STRUTS_I18N_ENCODING)
	public void setDefaultEncoding(String val) {
		this.defaultEncoding = val;
	}

	protected String getEncoding() {
		String encoding = this.defaultEncoding;

		if (encoding == null) {
			encoding = System.getProperty("file.encoding");
		}

		if (encoding == null) {
			encoding = "UTF-8";
		}

		return encoding;
	}

	private String include;

	public void setIncludeProperties(String includeProperties)
			throws JSONException {
		if (includeProperties == null)
			return;
		this.include = includeProperties;
		String[] properties = this.include.split(",");
		if (properties.length > 1) {
			throw new JSONException(
					"Struts configuration folder configuration multiple attributes:" + Arrays.toString(properties));
		}
	}

	public String getInclude() {
		return include;
	}

	public void setInclude(String include) {
		this.include = include;
	}

	/**
	 * 序列化对象成JSON
	 * 
	 * @param val
	 * @param writer
	 * @param sb
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	private String serialize(Object val) throws JsonGenerationException,
			JsonMappingException, IOException {
		StringWriter writer = new StringWriter();
		MAPPER.writeValue(writer, val);
		return writer.getBuffer().toString();
	}

	@Override
	public void execute(ActionInvocation invocation) throws Exception {
		LOGGER.debug("begin JSONTreeResult");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json");
		response.setCharacterEncoding(this.getEncoding());
		PrintWriter out = response.getWriter();
		Object obj = invocation.getAction();
		BeanInfo ip = Introspector.getBeanInfo(obj.getClass());
		PropertyDescriptor[] pros = ip.getPropertyDescriptors();
		String str = null;
		for (PropertyDescriptor pd : pros) {
			if (pd.getDisplayName().equals(include)) {
				Method method = pd.getReadMethod();
				if (method != null) {
					Object[] objs = null;
					Object val = method.invoke(obj, objs);
					str = serialize(val);
				}
			}
		}
		out.print(str);
		out.flush();
		out.close();
	}

}
