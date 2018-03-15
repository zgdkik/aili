package org.hbhk.aili.core.server.context;

import java.util.List;

import org.hbhk.aili.core.share.entity.DynamicBean;

public interface IDynamicBeanReader {
	
   List<DynamicBean> loadBean();  

}
