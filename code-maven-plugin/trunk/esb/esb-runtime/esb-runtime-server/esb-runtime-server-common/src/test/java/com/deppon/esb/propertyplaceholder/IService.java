package com.deppon.esb.propertyplaceholder;

import javax.jws.WebService;


/**
 * The Interface IService.
 */
@WebService
public interface IService {
	
	/**
	 * Say hi.
	 * 
	 * @param string
	 *            the string
	 */
	public void sayHi(String string);
}
