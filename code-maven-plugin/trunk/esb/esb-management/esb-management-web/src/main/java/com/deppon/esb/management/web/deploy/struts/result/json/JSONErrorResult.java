package com.deppon.esb.management.web.deploy.struts.result.json;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.codehaus.jackson.map.ObjectMapper;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.Result;
/**
 * @Description JSON错误结果处理
 * @author HuangHua
 * @Date 2012-4-12
 * 
 */
public class JSONErrorResult implements Result {

	private static final long serialVersionUID = -6724704054805634357L;

	/**
	 * 序列化及缓存类
	 */
	protected static final ObjectMapper MAPPER = new ObjectMapper();

	/**
	 * 错误信息
	 */
	private final static String ERROR = "ERROR";

	/**
	 * 把执行过程中存在的错误信息，以JSON的形式写出
	 * 
	 * @see com.deppon.foss.framework.server.web.result.AbstractResult#execute(com.opensymphony.xwork2.ActionInvocation)
	 *      execute
	 * @param invocation
	 * @throws Exception
	 * @since:
	 */
	@Override
	public void execute(ActionInvocation invocation) throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		StringWriter writer = new StringWriter();
		Object object = request.getAttribute(ERROR);
		if (object != null) {
			MAPPER.writeValue(writer, object);
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			StringBuffer sb = writer.getBuffer();
			// MAPPER序列化对象后，自动加上双引号，对象序列化后直接返回前台，需要过滤掉引号
			if (sb.toString().startsWith("\"")) {
				sb = new StringBuffer(sb.substring(1, sb.length() - 1));
			}
			out.print(sb);
			out.flush();
			out.close();
		}

	}

}
