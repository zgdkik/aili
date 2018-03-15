package org.hbhk.aili.client.core.core.context;

import java.util.Collection;

public interface IRole {

	/**
	 * 
	 *  Created on 2014-12-9 
	 * @return String .
	 */
	String getId();
	
	/**
	 * 
	 *  Created on 2014-12-9 
	 * @return void .
	 */
	void setId(String id);
	
	/**
	 * 
	 *  Created on 2014-12-9 
	 * <p>Description:[获取Function Id]</p>
	 * @return Collection<String> .
	 */
	Collection<String> getFunctionIds();
}
