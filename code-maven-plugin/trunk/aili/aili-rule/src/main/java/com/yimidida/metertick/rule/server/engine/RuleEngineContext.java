package com.yimidida.metertick.rule.server.engine;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class RuleEngineContext  implements InitializingBean{
	
	//启动时加载规则引擎
	@Override
	public void afterPropertiesSet() throws Exception {
		
	}

}
