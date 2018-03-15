package org.hbhk.aili.i18n.server.spring;

import java.io.Serializable;

public class I18nResource implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 7561052095252595140L;

	private long id;
    
    private String key;
    
    private String val;
    
    private String lang;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}
    
   
   

}
