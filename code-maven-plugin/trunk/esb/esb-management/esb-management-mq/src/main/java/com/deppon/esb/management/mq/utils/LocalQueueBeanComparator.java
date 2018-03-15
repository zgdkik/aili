/*
 * PROJECT NAME: esb-management-mq
 * PACKAGE NAME: com.deppon.esb.management.mq.utils
 * FILE    NAME: ChannelBeanComparator.java
 * COPYRIGHT: Copyright(c) 2012 Deppon All Rights Reserved.
 */
package com.deppon.esb.management.mq.utils;

import java.util.Comparator;

import com.deppon.esb.management.mq.domain.LocalQueueBean;

/**
 * The Class ChannelBeanComparator.
 * 
 * @author HuangHua
 * @date 2013-3-1 下午3:32:16
 */
public class LocalQueueBeanComparator implements Comparator<LocalQueueBean> {

	/**
	 * Compare.
	 * 
	 * @param q1
	 *            the chl1
	 * @param q2
	 *            the chl2
	 * @return the int
	 * @author HuangHua
	 * @date 2013-3-1 下午3:33:19
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(LocalQueueBean q1, LocalQueueBean q2) {
		int flag = q1.getQmgrName().compareTo(q2.getQmgrName());
		if (flag == 0) {
			return q1.getName().compareTo(q2.getName());
		} else {
			return flag;
		}
	}

}
