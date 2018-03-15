/*
 * PROJECT NAME: esb-management-mq
 * PACKAGE NAME: com.deppon.esb.management.mq.utils
 * FILE    NAME: ChannelBeanComparator.java
 * COPYRIGHT: Copyright(c) 2012 Deppon All Rights Reserved.
 */ 
package com.deppon.esb.management.mq.utils;

import java.util.Comparator;

import com.deppon.esb.management.mq.domain.ChannelBean;

/**
 * The Class ChannelBeanComparator.
 * 
 * @author HuangHua
 * @date 2013-3-1 下午3:32:16
 */
public class ChannelBeanComparator  implements Comparator<ChannelBean>{

	/**
	 * Compare.
	 * 
	 * @param chl1
	 *            the chl1
	 * @param chl2
	 *            the chl2
	 * @return the int
	 * @author HuangHua
	 * @date 2013-3-1 下午3:33:19
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(ChannelBean chl1, ChannelBean chl2) {
		 int flag=chl1.getQmgrName().compareTo(chl2.getQmgrName());
		  if(flag==0){
		   return chl1.getName().compareTo(chl2.getName());
		  }else{
		   return flag;
		  }  
	}


}
