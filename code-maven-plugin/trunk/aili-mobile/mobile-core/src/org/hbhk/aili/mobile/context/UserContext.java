package org.hbhk.aili.mobile.context;

public class UserContext {

	public static final String USER_CODE = "userCode";

	public static UserContext userContext = new UserContext();
	
	private UserContext() {
	}
	 
	private String userCode;

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

}
