package com.yimidida.ows.base.server.tag;

import java.util.Set;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.yimidida.ows.base.server.context.UserContext;

public class SecurityTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9022382600872914840L;
	private String code;
	
	private String roleCode;
	
	@Override
	public int doStartTag() throws JspException {
		boolean result = false;
		// EVAL_BODY_INCLUDE代表执行自定义标签中的内容，SKIP_BODY代表不执行自定义标签中的内容。
		Set<String> accessCodes =UserContext.getCurrentUser().accessCodes();
		Set<String> accessUrls =UserContext.getCurrentUser().queryAccessUris();
		Set<String> roleCodes =UserContext.getCurrentUser().getRoleCodes();
		if(roleCodes!=null && roleCodes.contains(roleCode)){
				result = true;
		}
		if(result==false && accessCodes!=null && accessCodes.size()>0){
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
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	
	
	
	

}
