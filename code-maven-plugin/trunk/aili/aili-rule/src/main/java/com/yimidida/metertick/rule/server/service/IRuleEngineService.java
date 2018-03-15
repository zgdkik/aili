package com.yimidida.metertick.rule.server.service;

public interface IRuleEngineService {

	/**
	 * 初始化规则引擎
	 */
	void initEngine();

	/**
	 * 刷新规则引擎中的规则
	 */
	void refreshEnginRule();

	/**
	 * 执行规则引擎
	 * 
	 * @param pointDomain
	 *            积分Fact
	 */
	void executeEnginRule(Object obj);
}
