package com.yimidida.ows.common.share.conts;

import java.text.SimpleDateFormat;

public interface CommonConstants {

	static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	// 新增操作标志
	static final Long OP_TYPE_CREATE = 0L;
	// 删除操作标志
	static final Long OP_TYPE_DELETE = 1L;
	// 修改操作标志
	static final Long OP_TYPE_UPDATE = 2L;
	// 审核操作
	static final Long OP_TYPE_AUDIT = 3L;
	// 红冲操作标志
	static final Long OP_TYPE_SUPPLEMENT = 4L;
	// 确认交款操作标志
	static final Long OP_TYPE_GATHER = 5L;
	// 取消交款操作标志
	static final Long OP_TYPE_CANCEL_GATHER = 6L;

	// 已审核标志
	static final String AUDITED_FLAG = "1";
	// 未审核标志
	static final String NO_AUDIT_FLAG = "0";
	// 没有清单的回单
	static final String NO_LIST_BILL_ACK_BILL = "2";
	//清单查询
	static final String QUERY_TYPE_LIST_BILL = "1";
	//回单查询
	static final String QUERY_TYPE_ACK_BILL = "2";
	//清单类型
	static final String LIST_BILL_TYPE_CODE = "1";
	//回单类型
	static final String ACK_BILL_TYPE_CODE = "2";
	//表格录入
	static final String INPUT_BY_TABLE = "1";
	//触摸屏录入
	static final String INPUT_BY_TOUCH_SCREEN = "2";
	//第三方录单
	static final String INPUT_BY_THIRD_PARTY = "4";
	//手机APP
	static final String INPUT_BY_MOBILE = "5";

	//触摸屏用户申请运单号的最大条数
	static final Long APPLY_WAYBILL_NO_MAX_COUNT = 1000L;
	//子单号开头
	static final String PREFIX_CHILD_BILL = "00";
	static final String SINGLE_PRE_BILLNO = "123";
	/**运单分派默认员工*/
	static final String WAYBILL_DISPATCH_DEFAULT_EMPCODE = "999999";

	/**派件状态:0-正在出仓派件*/
	static final int WAYBILL_DELVIER_STATE_OUT_DELVIER = 0;
	/**派件状态:1-已派件*/
	static final int WAYBILL_DELVIER_STATE_DELVIERED = 1;
	/**派件状态:2-滞留入仓*/
	static final int WAYBILL_DELVIER_STATE_STAY_IN = 2;
	/**派件分货 -1 */
	static final int WAYBILL_DISPATCH_SHELF_FLAG_DISPATCH = 1;
	/**自取件 -2 */
	static final int WAYBILL_DISPATCH_SHELF_FLAG_ISSHELF = 2;
	/**原返货 -3 */
	static final int WAYBILL_DISPATCH_SHELF_FLAG_RETURN = 3;
	/**收款状态 未收款*/
	static final int WAYBILL_DELVIER_ISNOT_GATHER= 0;
	/**收款状态 已收款*/
	static final int WAYBILL_DELVIER_IS_GATHER= 1;

	/**分货表操作类型 分派 1*/
	static final String DISPATCH_WAYBILL_DELVIER = "1";
	/**分货表操作类型 取消分派 2*/
	static final String DISPATCH_WAYBILL_CANCLE_DELVIER = "2";
	/**分货表操作类型 交款 3*/
	static final String DISPATCH_WAYBILL_ACCOUNT = "3";
	/**分货表操作类型 取消交款 4*/
	static final String DISPATCH_WAYBILL_CANCLE_ACCOUNT = "4";
	/**自提**/
	static final String DISPATCH_WAYBILL_SHELF_PICK = "5";

	/**欠款自提 -6*/
	static final String DISPATCH_WAYBILL_SHELF_DEBT = "6";
	/**取消欠款自提 -7*/
	static final String DISPATCH_WAYBILL_SHELF_CANCLE_DEBT = "7";
	/**欠款自提交款 -8*/
	static final String DISPATCH_WAYBILL_SHELF_DEBT_GATHER = "8";
	/**取消欠款自提交账 -9*/
	static final String DISPATCH_WAYBILL_SHELF_CANCLE_DEBT_GATHER = "9";
	/**原返货操作标记 10*/
	static final String DISPATCH_WAYBILL_RETURN_GOODS = "10";
	/**欠款分派 11*/
	static final String DISPATCH_WAYBILL_DELIVER_DEBT = "11";


	/**收派类型 收件：1 */
	static final String EMPLOYEE_COMMISSION_SEND_TYPE_RECEIVE = "1";
	/**收派类型 派件：2 */
	static final String EMPLOYEE_COMMISSION_SEND_TYPE_DELIVER = "2";

	/** 1:运费 */
	static final String WAYBILL_SERVICE_TYPE_EXPRESS_FEE = "SF01";
	/** 2:代收 */
	static final String WAYBILL_SERVICE_TYPE_GOODS_CHARGE_FEE = "SF02";
	/** 3:保价 */
	static final String WAYBILL_SERVICE_TYPE_INSURANCE_FEE = "SF03";
	/** 4:签回单 */
	static final String WAYBILL_SERVICE_TYPE_SIGN_BACK_FEE = "SF04";
	/** 5:包装费 */
	static final String WAYBILL_SERVICE_TYPE_PACKAGE_FEE = "SF05";
	/** 6:转寄费 */
	static final String WAYBILL_SERVICE_TYPE_FORWARD_FEE = "SF06";
	/** 7:等通知 */
	static final String WAYBILL_SERVICE_TYPE_WAIT_NOTIFY_FEE = "SF07";
	/** 8:派送费 */
	static final String WAYBILL_SERVICE_TYPE_DELIVER_FEE = "SF08";
	/** 9:垫付代收 */
	static final String WAYBILL_SERVICE_TYPE_DEBOURS_GOODS_FEE = "SF09";
	/** 10:垫付运费 */
	static final String WAYBILL_SERVICE_TYPE_DEBOURS_WAY_FEE = "SF10";
	/** 11:仓管费 */
	static final String WAYBILL_SERVICE_TYPE_STORE_FEE = "SF11";
	/** 12:其他费用 */
	static final String WAYBILL_SERVICE_TYPE_OTHER_FEE = "SF12";

	/**
	 * 回单付类型
	 */
	static final String SIGN_BACK_PAYMENT = "1";
	/**
	 * 签回单服务类型
	 */
	static final String SIGN_BACK_SERVICE_TYPE = "SF04";

	/**
	 * 结算方式: 1-现结, 2-代收扣运, 3-周期结算
	 */
	public final static String CASH_SETTLEMENT_TYPE = "1"; //$NON-NLS-1$
	public final static String DEDUCTION_SETTLEMENT_TYPE = "2"; //$NON-NLS-1$
	public final static String PERIOD_SETTLEMENT_TYPE = "3"; //$NON-NLS-1$

	/**
	 * 付款类型：1-寄付, 2-到付, 3-回单付
	 */
	public final static String CONSIGNOR_PAYMENT = "1"; //$NON-NLS-1$
	public final static String DELIVER_PAYMENT = "2"; //$NON-NLS-1$
	public final static String BACK_PAYMENT = "3";

	/**
	 * 签收类型:0-正常
	 */
	public final static String WAYBILL_SIGN_TYPE_NORMAL = "0";

	/**
	 * 签收类型:1-拒收
	 */
	public final static String WAYBILL_SIGN_TYPE_REFUSE = "1";

	/**
	 * 签收类型:2-损坏
	 */
	public final static String WAYBILL_SIGN_TYPE_DAMAGE = "2";

	/**
	 * 签收类型:3-作废
	 */
	public final static String WAYBILL_SIGN_TYPE_INVALID = "3";

	/**
	 * 签收类型:4-返回
	 */
	public final static String WAYBILL_SIGN_TYPE_RETURN = "4";

	/**
	 * 预制回单签收
	 */
//	public final static String WAYBILL_SIGN_INPUT_TYPE_PREPARE_BILL = "1";
	public final static String INPUT_TYPE_TABLE = "1"; //$NON-NLS-1$
	public final static String INPUT_TYPE_TOUCH = "2"; //$NON-NLS-1$
	public final static String INPUT_TYPE_PREPARE = "3"; //$NON-NLS-1$

	/**
	 * 打印普货票据签收单;
	 */
	public final static String WAYBILL_SIGN_INPUT_TYPE_PRINT_BILL = "4";


	/**欠款自提 -1 */
	public final static String DEBT_WAYBILL_SHELF_DESPATCH = "1";
	/**取消欠款自提 -2 */
	public final static String DEBT_WAYBILL_CANCAL_SHELF_DESPATCH = "2";
	/**欠款交账 -3 */
	public final static String DEBT_WAYBILL_GATHER = "3";
	/**取消欠款交账 -4 */
	public final static String DEBT_WAYBILL_CANCLE_GATHER = "4";

	/** 正常-1 */
	public final static String WAYBILL_FLAG_NORMAL = "1";
	/** 转网点 -2 */
	public final static String WAYBILL_FLAG_TURN_DEPT = "2";
	/** 原返货 -3 */
	public final static String WAYBILL_FLAG_RETURN = "3";
	/** 临时挂失 -4 */
	public final static String WAYBILL_FLAG_TEMP_LOST = "4";
	/** 正式挂失 -5 */
	public final static String WAYBILL_FLAG_FORMAL_LOST = "5";
	/** 作废 -7 */
	public final static String WAYBILL_FLAG_CANEL = "7";


	/** 21:派送费 */
	static final String FEE_TYPE_DELIVER_FEE = "SF21";

	/** 22:仓管费 */
	static final String FEE_TYPE_STORE_FEE = "SF22";

	/** 23:红冲费 */
	static final String FEE_TYPE_SUPPLY_FEE = "SF23";

	/** 24:挂失费 */
	static final String FEE_TYPE_LOST_FEE = "SF24";
	/**
	 * 第三方录单
	 */
	public final static String WAYBILL_OUT_THIRD_FLAG = "2";

	/**
	 * 运单保存类型:
	 * 1-表格录入
	 * 2-触摸屏录入
	 * 11-缓存上传表格录入
	 * 21-缓存上传触摸屏录入
	 * 12-存根上传表格录入
	 * 22-存根上传触摸屏录入
	 */
	public final static String TABLE_SAVE_TYPE = "1";
	public final static String TOUCH_SAVE_TYPE = "2";
	public final static String CACHE_TABLE_SAVE_TYPE = "11";
	public final static String CACHE_TOUCH_SAVE_TYPE = "21";
	public final static String STUB_TABLE_SAVE_TYPE = "12";
	public final static String STUB_TOUCH_SAVE_TYPE = "22";

	/** 其他费用未结算标记 -0 */
	static final String FEE_TYPE_NOT_GATHERED = "0";

	/** 其他费用已结算标记 -1 */
	static final String FEE_TYPE_GATHERED = "1";

	/** 交单状态 未交单 */
	static final String DEBT_NOT_TRANSFER_STATE = "0";

	/** 交单状态 已交单 */
	static final String DEBT_TRANSFER_STATE = "1";

	/**
	 * 支付类型:POS支付 - 1
	 */
	public final static String PAY_TYPE_POS = "1";

	/**
	 * 支付类型:营运系统支付 - 2
	 */
	public final static String PAY_TYPE_SYS = "2";

	/**
	 * 刷卡
	 */
	public final static String PAY_WAY_CARD = "0";

	/**
	 * 现金
	 */
	public final static String PAY_WAY_CASH = "1";

	/**
	 * 已经转回到总部
	 */
	public final static String REMIT_FLAG_1 = "1";

	/**
	 * 未回到总部
	 */
	public final static String REMIT_FLAG_0 = "0";


	public static String ADD_STORE_FEE_DEPTS[] = new String[]{
		"934A",
		"933A",
		"954A",
		"910C",
		"910B",
		"913A",
		"913B",
		"913C",
		"913D",
		"917A",
	};

	/**
	 * 快递
	 */
	static final String PRODUCT_EXPRESS= "B1";


	/**
	 * 业务类型:1-区域(内部价格),2-联运(外部价格)
	 */
	public final static String BIZ_TYPE_DISTINCT = "1";
	public final static String BIZ_TYPE_UNION = "2";
	/**
	 * 运单转网点 删除运单 操作记录日志 add YanLong.Dong 2016-01-12
	 */
	public final static String CHANGE_DEPT = "1";
	public final static String DEL_WAYBILL = "2";




	/**
	 * 1-正常上传
	 * 2-缓存上传
	 * 3-存根上传
	 */
	public final static String NORMAL_TRANSFER_TYPE = "1";
	public final static String CACHE_TRANSFER_TYPE = "2";
	public final static String STUB_TRANSFER_TYPE = "3";
}