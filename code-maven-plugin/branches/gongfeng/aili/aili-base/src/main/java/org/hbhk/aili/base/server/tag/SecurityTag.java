package org.hbhk.aili.base.server.tag;

import java.util.Set;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang3.StringUtils;
import org.hbhk.aili.base.server.context.UserContext;

public class SecurityTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9022382600872914840L;
	private String code;
	@Override
	public int doStartTag() throws JspException {
		boolean result = false;
		// EVAL_BODY_INCLUDE代表执行自定义标签中的内容，SKIP_BODY代表不执行自定义标签中的内容。
		Set<String> accessCodes =UserContext.getCurrentUser().accessCodes();
		Set<String> accessUrls =UserContext.getCurrentUser().queryAccessUris();
		String status = UserContext.getCurrentUser().getStatus();
		String userType = UserContext.getCurrentUser().getUserType();
		if(!"1".equals(userType)){
			if(StringUtils.isNotEmpty(status) &&  "4".equals(status)){
				return SKIP_BODY;
			}
		}
		
		if(accessCodes!=null && accessCodes.size()>0){
			if(code!=null && code!=""){
				String[] codes = code.split(",");
				for (String c : codes) {
					if(accessCodes.contains(c) || accessUrls.contains(c)){
						result = true;
					}
				}
			}
		}
		return result ? EVAL_BODY_INCLUDE : SKIP_BODY;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	

}
