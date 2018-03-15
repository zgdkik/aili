package com.deppon.esb.management.common.entity.jms.header;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class StatusList.
 */
public class StatusList implements Serializable {

	/** 常量定义 serialVersionUID. */
	private final static long serialVersionUID = 11082011L;
	
	/** The status info. */
	protected List<StatusInfo> statusInfo;

	/**
	 * Gets the value of the statusInfo property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the statusInfo property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getStatusInfo().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * 
	 * @return the status info {@link StatusInfo }
	 */
	public List<StatusInfo> getStatusInfo() {
		if (statusInfo == null) {
			statusInfo = new ArrayList<StatusInfo>();
		}
		return this.statusInfo;
	}

}
