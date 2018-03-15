package org.hbhk.aili.base.share.entity;

/**
 * 加盟账户数据接口
 * @author Thinkpad
 *
 */
public interface IDepartmentAccount {

	/**
	 * 获取网点代码(账号)
	 * @return
	 */
	public String getDeptCode();
	
	/**
	 * 网点账套
	 * @return
	 */
	public String getDeptAccount();

	/**
	 * 获取父网点代码
	 * @return
	 */
	public String getParentDeptCode();

	/**
	 * 获取预付金额
	 * @return
	 */
	public Double getFeeAmount();

	/**
	 * 获取代收款预付金额
	 * @return
	 */
	public Double getGoodsFeeAmount();

	/**
	 * 获取冻结预付金额
	 * @return
	 */
	public Double getFreezedFeeAmount();

	/**
	 * 获取冻结代收款预付金额
	 * @return
	 */
	public Double getFreezedGoodsFeeAmount();

	/**
	 * 获取警告金额
	 * @return
	 */
	public Double getWarningAmount();

	/**
	 * 获取关闭金额
	 * @return
	 */
	public Double getClosedAmount();

	/**
	 * 获取可用金额
	 * @return
	 */
	public Double getUsableFeeAmount();
	
	/**
	 * 冻结金额
	 * @param feeAmount
	 */
	public void freezedFeeAmount(Double feeAmount);

}
